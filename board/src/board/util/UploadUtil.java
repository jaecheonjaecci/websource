package board.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadUtil {
	//Map은 key와 value를 담을 수 있는 구조
	public Map<String, String> map = new HashMap<>();

	public Map<String, String> requestParse(HttpServletRequest request) {

		// 사용자가 요청한 것이 enctype="multipart/form-data"인지 확인하는 작업
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		// true라면
		if (isMultipart) {
			// 전송된 파일을 디스크에 저장하기위한 객체
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			// 업로드 가능 사이즈를 지정
			upload.setSizeMax(2000 * 1024);

			// 사용자의 request를 분석 => 리스트에 담기 : fileitem이라는 구조를 이용해서
			// 리스트에 담음
			List<FileItem> fileItems = null;
			try {
				fileItems = upload.parseRequest(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}

			// 리스트에 담긴 데이터를 iterator에 담음
			Iterator<FileItem> iter = fileItems.iterator();

			String fieldName = null, fileName = null, value = null;
			while (iter.hasNext()) {
				// 가지고 올 내용이 있으면이라는 뜻
				FileItem item = iter.next();

				// type이 formfieid(text,password,checkbox....)로 넘어오는 경우
				if (item.isFormField()) {
					// uploadform에서 #name 가져오기
					fieldName = item.getFieldName();

					// 요소안에 들어있는 value 값 가져오기
					try {
						value = item.getString("utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}

					map.put(fieldName, value);

					// type이 file로 넘어오는 경우
				} else {
					// uploadform에서 #name 가져오기
					fieldName = item.getFieldName();
					// 파일명 가져오기
					fileName = item.getName();

					File file = null;

					if (!fileName.isEmpty() && item.getSize() > 0) {
						
						// 파일 저장
						String path = "e:\\upload\\";

						// 파일 저장 전 중복되지 않는 고유한 키 값 생성
						// : 중복된 파일명을 업로드 할 경우 오류 발생
						UUID uuid = UUID.randomUUID();
						file = new File(path + uuid.toString() + "_" + fileName);// 업로드 파일명
						try {
							item.write(file);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						map.put(fieldName, file.getName());

					}
				}

			}

		}

		return map;
	}
}

package member.action;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

// 목적 : 이동경로, 이동방식을 저장하기 위함
public class ActionForward {
	private String path;
	private boolean redirect;
}

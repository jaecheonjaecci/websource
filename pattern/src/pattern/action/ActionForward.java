package pattern.action;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter


public class ActionForward {
	//이동 경로와 이동방식 저장
	private String path;
	private boolean redirect; 
	
	//true로 저장시(sendRedirect)로 보내고, false로 저장시(forward) 방식으로 보냄
}

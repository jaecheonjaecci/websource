package board.action;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

//이동경로와 이동방식 저장 클래스

public class BoardActionForward {
	private String path;
	private boolean redirect;
}

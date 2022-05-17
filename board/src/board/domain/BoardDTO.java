package board.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

//@Data

public class BoardDTO {
	private int bno;
	private String title;
	private String content;
	private String password;
	private String attach;
	private int readcount;
	private String name;
	private Date regdate;
	private int re_ref;
	private int re_lev;
	private int re_seq;
}

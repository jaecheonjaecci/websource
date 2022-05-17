package member.controller;

import member.action.Action;
import member.action.JoinAction;
import member.action.LeaveAction;
import member.action.LoginAction;
import member.action.LogoutAction;
import member.action.ModifyAction;

public class MemberActionFactory {
	//singleton 형태의 객체 생성 : 객체 생성을 한번만 할 수 있도록 설정
	private static MemberActionFactory maf;
	private Action action;
	
	private MemberActionFactory() {}
		public static MemberActionFactory getInstance() {
			if(maf==null) {
				maf = new MemberActionFactory();
			}
			return maf;
		}
		public Action action(String cmd) {
			//액션이 성공한 후 가야할 페이지
			if(cmd.equals("/login.do")) {
				action = new LoginAction("/view/loginForm.jsp");
			}else if(cmd.equals("/logout.do")){
				action = new LogoutAction("/index.jsp");
			}else if(cmd.equals("/leave.do")) {
				action = new LeaveAction("/index.jsp");
			}else if(cmd.equals("/modify.do")) {
				action = new ModifyAction("/view/loginForm.jsp");
			}else if(cmd.equals("/join.do")) {
				action = new JoinAction("/view/loginForm.jsp");
			}
			return action;
		}
	}


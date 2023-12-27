package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class MemberInfo implements MenuCommand  {
	private MallController cont;
	private MemberDAO member;
	@Override
	public void init() {
		cont = MallController.getInstance();
		member= MemberDAO.getInstance();
		System.out.println("======== [ 내정보 ] =======");
	}

	@Override
	public boolean update() {
		System.out.println("[1] 비밀번호 변경");
		System.out.println("[2] 뒤로가기");
		System.out.println("[0] 종료");
		member.PrintInfo(cont.getLoginId());
		int sel = Util.getValue("► 메뉴 입력 [0 - 2]", 0, 2);
		if(sel == 0) {
			cont.setNext(null);
		}else if(sel == 1) {
			String pw = Util.getValue("► 패스 워드 ");
			if(!member.Login(cont.getLoginId(), pw)) {
				System.out.println("패스워드를 확인해주세요");
				return false;
			}
			String changepw = Util.getValue("► 신규 패스 워드 ");
			if(pw.equals(changepw)) {
				System.out.println("다른 비밀번호를 입력해주세요");
				return false;
			}
			member.UpdatePw(cont.getLoginId(), changepw);
		}else if( sel == 2) {
			cont.setNext("MemberMain");
		}
		return false;
	}

}

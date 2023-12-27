package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class AdminMember  implements MenuCommand {
	private MallController cont;
	private MemberDAO member;
	@Override
	public void init() {
		cont = MallController.getInstance();
		member = MemberDAO.getInstance();
		System.out.println("============== [관리자 회원 목록 ] ==============");
	}

	@Override
	public boolean update() {
		System.out.println("[1] 회원 목록");
		System.out.println("[2] 회원 삭제");
		System.out.println("[3] 뒤로가기");
		System.out.println("[0] 종료");
		int sel = Util.getValue("► 메뉴 선택[0 - 3] ", 0, 3);
		if( sel == 0) {
			cont.setNext(null);
		}else if( sel == 1) {
			member.PrintInfo();
		}
		else if( sel == 2) {
			System.out.println("회원 삭제시 구매 내역 및 게시판 글이 사라집니다");
			String id = Util.getValue("► 삭제할 회원 아이디");
			boolean b = false;
			if(id.equals("admin")) {
				System.out.println("관리자 회원 삭제 불가능");
				b= true;
			}else if(member.getMemberById(id) == null) {
				System.out.println("없는 아이디 입니다.");
				b = true;
			}
			if(b) {
				System.out.println("삭제 실패");
				return false;
			}
			cont.DeleteUser(id);
		}
		else if( sel == 3) {
			cont.setNext("AdminMain");
		}
		return false;
	}

}

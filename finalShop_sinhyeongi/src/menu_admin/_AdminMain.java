package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class _AdminMain  implements MenuCommand {
	private MallController cont;
	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.println("===========[ 관리자 회원 목록 ] ==========");
	}

	@Override
	public boolean update() {
		System.out.println("[1] 회원관리");
		System.out.println("[2] 상품관리");
		System.out.println("[3] 게시판관리");
		System.out.println("[4] 로그아웃");
		System.out.println("[5] 파일저장");
		System.out.println("[0] 종료");
		int sel = Util.getValue("► 메뉴 선택 [ 0 - 5]", 0, 5);
		if(sel == 0) {
			cont.setNext(null);
		}else if( sel == 1) {
			cont.setNext("AdminMember");
		}else if( sel == 2) {
			cont.setNext("AdminItem");
		}
		else if( sel == 3) {
			cont.setNext("AdminBoard");
		}
		else if( sel == 4) {
			cont.setLoginId(null);
			cont.setNext("MallMain");
		}
		else if( sel == 5) {
			cont.Save();
		}
		return false;
	}

}

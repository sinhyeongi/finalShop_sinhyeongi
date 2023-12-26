package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class _MemberMain  implements MenuCommand {
	private MallController cont;
	private MemberDAO member;
	@Override
	public void init() {
		cont = MallController.getInstance();
		member = MemberDAO.getInstance();
		System.out.println("=====[ 회원 "+member.getMemberName(cont.getLoginId())+"님 ]=====");
		System.out.println("[1] 상품구매");
		System.out.println("[2] 구매내역");
		System.out.println("[3] 게시판");
		System.out.println("[4] 나의 정보");
		System.out.println("[5] 회원 탈퇴");
		System.out.println("[6] 로그아웃");
		System.out.println("[0] 종료");
	}

	@Override
	public boolean update() {
		int sel = Util.getValue("메뉴 입력 [0 - 6] ",0,6);
		if(sel == 0) {
			cont.setNext(null);
		}else if( sel == 1) {
			
		}else if(sel == 2) {
			
		}
		else if(sel == 3) {
			cont.setNext("MemberBoard");
		}
		else if(sel == 4) {}
		else if(sel == 5) {}
		else if(sel == 6) {}
		return false;
	}
	
}

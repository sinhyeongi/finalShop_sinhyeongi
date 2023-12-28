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
			cont.setNext("MemberShopping");
		}else if(sel == 2) {
			cont.setNext("MemberCart");
		}
		else if(sel == 3) {
			cont.setNext("MemberBoard");
		}
		else if(sel == 4) {
			cont.setNext("MemberInfo");
		}
		else if(sel == 5) {
			System.out.println("회원 탈퇴시 구매 내역이 사라집니다");
			sel = Util.getValue("정말 탈퇴 하시겠습니까?", 0, 2);
			System.out.println("[1] 예");
			System.out.println("[2] 아니오");
			System.out.println("[0] 종료");
			if(sel == 0) {
				cont.setNext(null);
			}
			else if(sel == 1) {
				cont.DeleteUser();
			}
		}
		else if(sel == 6) {
			cont.DeleteUser();
		}
		return false;
	}
	
}

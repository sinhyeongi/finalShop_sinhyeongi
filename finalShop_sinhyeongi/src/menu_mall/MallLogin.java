package menu_mall;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class MallLogin implements MenuCommand  {
	private MallController cont;
	private MemberDAO member;
	public MallLogin() {
		member= MemberDAO.getInstance();
	}

	@Override
	public void init() {
		cont = MallController.getInstance();
		System.out.println("=====[ 로그인]=====");
	}

	@Override
	public boolean update() {
		if(cont.getLoginId() != null) {
			cont.setNext("MemberMain");
		}
		String id = Util.getValue("► 아이디 입력 : ");
		String pw = Util.getValue("► 패스워드  입력 : ");
		if(id.equals("admin") && pw.equals("admin")) {
			cont.setLoginId(id);
			cont.setNext("AdminMain");
		}
		else if(member.Login(id, pw)) {
			cont.setLoginId(id);
			cont.setNext("MemberMain");
		}else {
			System.out.println("아이디 또는 패스워드를 확인 하세요");
			System.out.println("===로그인 실패===");
			cont.setNext("MallMain");
			return false;
		}
		System.out.println("[ 로그인 성공 ]");
		return false;
	}
}

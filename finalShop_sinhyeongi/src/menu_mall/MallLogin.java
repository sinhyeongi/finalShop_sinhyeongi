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
		String id = Util.getValue("► 아이디 입력 : ");
		String pw = Util.getValue("► 패스워드  입력 : ");
		if(member.Login(id, pw)) {
			cont.setLoginId(id);
			cont.setNext("MemberMain");
		}else {
			cont.setNext("MallMain");
		}
		return false;
	}
}

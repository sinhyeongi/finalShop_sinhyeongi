package _mall;

import controller.MallController;

public class _testMain {
	static MallController cont = MallController.getInstance();
	public static void main(String[] args) {
//		"MallMain","MallJoin","MallLogin",
//		"AdminBoard","AdminItem","AdminMain",
//		"AdminMember","MemberBoard","MemberCart",
//		"MemberInfo","MemberMain","MemberShopping","MemberQuit"
		String page = "AdminMain";
		cont.init();
		cont.setNext(page);
		cont.update();
	}
}

package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import util.Util;

public class MemberCart implements MenuCommand  {
	private MallController cont;
	private CartDAO cart;
	private ItemDAO item;
	@Override
	public void init() {
		cont = MallController.getInstance();
		cart = CartDAO.getInstance();
		item = ItemDAO.getInstance();
		System.out.println("============ [ 구매 내역 ] ============");
	}

	@Override
	public boolean update() {
		System.out.println("[1] 쇼핑하기");
		System.out.println("[2] 뒤로가기");
		System.out.println("[0] 종료");
		item.PrintCartData(cart.PrintCart(cont.getLoginId()));
		int i = Util.getValue("► 메뉴 선택 [ 0 - 2]", 0, 2);
		if(i == 0) {
			cont.setNext(null);
		}else if(i == 1) {
			cont.setNext("MemberShopping");
		}else if( i == 2) {
			cont.setNext("MemberMain");
		}
		return false;
	}

}

package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import util.Util;

public class MemberShopping  implements MenuCommand {
	private MallController cont;
	private ItemDAO item;
	private CartDAO cart;
	@Override
	public void init() {
		cont = MallController.getInstance();
		item = ItemDAO.getInstance();
		cart = CartDAO.getInstance();
		System.out.println("============ 쇼핑몰에 오신것을 환영합니다 ============");
//		item.Test();
	}

	@Override
	public boolean update() {
		if(item.getCategorySize() == 0) {
			System.out.println("아이템이 없습니다!");
			cont.setNext("MemberMain");
			return false;
		}
		item.PrintCategory();
		System.out.println("[0] 뒤로가기");
		int sel = Util.getValue("메뉴 입력 [0 - "+(item.getCategorySize())+"]", 0, item.getCategorySize());
		if(sel == 0) {
			cont.setNext("MemberMain");
			return false;
		}
		if(sel < 1 || sel > item.getCategorySize()) {
			return false;
		}
		item.PrintCategoryItem(sel);
		while(true) {
			String itemname = Util.getValue("► 구매 아이템 이름");
			if(item.CheckItemname(itemname) == false) {
				System.out.println("아이템 이름 오류 다시 입력 해주세요");
				continue;
			}
			int count = Util.getValue("► 아이템 구매 수량 [1 - 100]", 1, 100);
			if(count < 0 || count > 100) {
				return false;
			}
			cart.BuyItem(cont.getLoginId(), item.getItemNo(itemname), count);
			System.out.println("[  %s %d개 구매 완료 ]".formatted(itemname,count));
			cont.setNext("MemberCart");
			break;
		}
		return false;
	}

}

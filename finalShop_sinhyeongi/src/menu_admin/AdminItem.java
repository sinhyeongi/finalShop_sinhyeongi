package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;
import util.Util;

public class AdminItem implements MenuCommand  {
	private MallController cont;
	private ItemDAO item;
	private CartDAO cart;
	@Override
	public void init() {
		cont = MallController.getInstance();
		item = ItemDAO.getInstance();
		cart = CartDAO.getInstance();
		System.out.println("=============[ 관리자 쇼핑몰 관리 ]=============");
	}

	@Override
	public boolean update() {
		System.out.println("[1] 아이템 추가");
		System.out.println("[2] 아이템 삭제");
		System.out.println("[3] 총 매출 아이템 갯수 출력(판매량 높은순으로)");
		System.out.println("[4] 뒤로가기");
		System.out.println("[0] 종료");
		item.PrintAdminItem();
		int sel = Util.getValue("► 메뉴 선택 [0-4] ", 0, 4);
		if(sel == 0) {
			cont.setNext(null);
		}else if( sel == 1) {
			String item = Util.getValue("► 아이템");
			if(this.item.CheckItem(item)) {
				System.out.println("이미 있는 카테고리/아이템 이름 입니다.");
				return false;
			}
			String category = Util.getValue("► 카테고리");
			int price = Util.getValue("► 가격[100 - 1000000]", 100, 1000000);
			System.out.println("아이템 추가 완료");
			this.item.NewItem(item, category, price);
		}
		else if( sel == 2) {
			System.out.println(item.GetLastItemNum());
			int No = Util.getValue("► 삭제할 아이템 번호 입력"+item.GetLastItemNum()+"]", 1, item.GetLastItemNum());
			cont.DeleteItem(No);
		}
		else if( sel == 3) {
			item.PrintAdminCartData(cart.PrintCart());
		}
		else if( sel == 4) {
			cont.setNext("AdminMain");
		}
		return false;
	}

}

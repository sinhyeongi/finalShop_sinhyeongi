package dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import dto.Cart;
import dto.Item;

public class CartDAO {
	private static CartDAO instance;
	List<Cart> cart;

	private CartDAO() {
		cart = new ArrayList<Cart>();
	}

	public static CartDAO getInstance() {
		if (instance == null)
			instance = new CartDAO();
		return instance;
	}
	/**
	 * 새로운 아이템 구매
	 * 현재 카트에 유저가 구입한 아이템 이있다면 갯수만 증가
	 * @param id 유저 아이디
	 * @param itemNo 아이템 넘버
	 * @param itemCount 아이템 갯수
	 */
	public void BuyItem(String id, int itemNo, int itemCount) {
		if(itemNo == -1) {
			return;
		}
		for (int i = 0; i < cart.size(); i++) {
			if (cart.get(i).getId().equals(id) && cart.get(i).getItemNum() == itemNo) {
				cart.get(i).setItemCnt(cart.get(i).getItemCnt() + itemCount);
				return;
			}
		}
		cart.add(new Cart(id, itemNo, itemCount));

	}
	/**
	 * 	카트 정보 로드
	 * @param data 카트 정보
	 * 
	 */
	public void LoadData(String data) {
		if (data == null || data.length() < 1)
			return;

		if (cart.size() != 0)
			cart.clear();
		String[] data2 = data.split("\n");
		for (int i = 0; i < data2.length; i++) {
			String[] t = data2[i].split("/");
			cart.add(new Cart(Integer.parseInt(t[0]), t[1], Integer.parseInt(t[2]), Integer.parseInt(t[3])));
		}

	}
	/**
	 * 
	 * @return 세이브 데이터 리턴
	 */
	public String SaveData() {
		String data="";
		for(int i = 0 ; i < cart.size(); i++) {
			data += cart.get(i).Save();
		}
		if(data.length() > 1)
			data = data.substring(0,data.length()-1);
		return data;
	}
	/**
	 * 유저가 구매한 아이템 String type 리턴 
	 * @param id 유저 아이디
	 * @return 아이템 넘버/ 아이템 갯수\n...아이템 넘버/ 아이템 갯수\n
	 */
	public String PrintCart(String id) {
		String data ="";
		for(int i = 0 ; i < cart.size(); i++) {
			if(cart.get(i).getId().equals(id)) {
				data += cart.get(i).getItemNum()+"/"+cart.get(i).getItemCnt()+"\n";
			}
		}
		if(data.length() > 1)
			data = data.substring(0,data.length() -1);
		return data;
	}
	/**
	 * 구매 아이템 전체 중 아이템 넘버/ 아이템 갯수\n .... String type 리턴
	 * @return 아이템 넘버/ 아이템 갯수\n...아이템 넘버/ 아이템 갯수\n 
	 */
	public String PrintCart() {
		String data ="";
		List<Cart> copy = cart;
		copy.sort(Comparator.comparing(Cart::getItemCnt).reversed());
		for(int i = 0 ; i < copy.size(); i++) {
				data += copy.get(i).getItemNum()+"/"+copy.get(i).getItemCnt()+"\n";
		}
		if(data.length() > 1)
			data = data.substring(0,data.length() -1);
		return data;
	}
	/**
	 * 유저가 구매한 아이템 전체 삭제
	 * @param id 유저 아이디
	 */
	public void DeleteUser(String id) {
		for(int i = 0 ; i < cart.size(); i++) {
			if(cart.get(i).getId().equals(id)) {
				if(cart.size() == 1) {
					cart.clear();
					break;
				}
				cart.remove(i);
			}
		}
		System.out.println("회원 구매 내역 삭제 완료");
	}
	/**
	 * 아이템 넘버에 해당 하는 아이템 삭제
	 * @param No 아이템 넘버
	 */
	public void DeleteItem(int No) {
		for(int i = 0 ; i < cart.size(); i++) {
			if( No == cart.get(i).getItemNum()) {
				if(cart.size() == 1) {
					cart.clear();
					break;
				}
				cart.remove(i);
			}
		}
	}
}

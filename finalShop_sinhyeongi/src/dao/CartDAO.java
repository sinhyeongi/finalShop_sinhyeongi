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
	public String SaveData() {
		String data="";
		for(int i = 0 ; i < cart.size(); i++) {
			data += cart.get(i).Save();
		}
		if(data.length() > 1)
			data = data.substring(0,data.length()-1);
		return data;
	}
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
	public void DeleteUser(String id) {
		for(int i = 0 ; i < cart.size(); i++) {
			if(cart.get(i).getId().equals(id)) {
				if(cart.size() == 1) {
					cart.clear();
				}
				cart.remove(i);
			}
		}
	}
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

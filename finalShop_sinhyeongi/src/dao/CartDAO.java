package dao;

import java.util.ArrayList;
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
		if(instance == null)
			instance = new CartDAO();
		return instance;
	}
	public void LoadData(String data) {
		if(data == null || data.length() < 1)return;

		if(cart.size() != 0)
			cart.clear();
		String[] data2 = data.split("\n");
		for(int i = 0 ; i < data2.length ;i++) {
			String[] t = data2[i].split("/");
			cart.add(new Cart(Integer.parseInt(t[0]),t[1],Integer.parseInt(t[2]),Integer.parseInt(t[3])));
		}

		
	}
	
}

package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Item;
import dto.Member;

public class ItemDAO {
	private static ItemDAO instance;
	private List<Item> item;
	private ItemDAO() {
		item = new ArrayList<Item>();
	}
	public static ItemDAO getInstance() {
		if(instance == null) {
			instance = new ItemDAO();
		}
		return instance;
	}
	
	public void LoadData(String data) {
		if(data == null || data.length() == 0) return;
		
		if(item.size() != 0)
			item.clear();
		String[] data2 = data.split("\n");
		for(int i = 0 ; i < data2.length ;i++) {
			String[] t = data2[i].split("/");
			item.add(new Item(Integer.parseInt(t[0]),t[1],t[2],Integer.parseInt(t[3])));
		}
	}
}

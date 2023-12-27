package dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import dto.Item;
import dto.Member;

public class ItemDAO {
	private static ItemDAO instance;
	private List<Item> item;
	private List<String>category;
	private int select;
	private ItemDAO() {
		item = new ArrayList<Item>();
		category = new ArrayList<String>();
	}
	public static ItemDAO getInstance() {
		if(instance == null) {
			instance = new ItemDAO();
		}
		return instance;
	}
	public boolean CheckItem(String name) {
		for(int i = 0 ; i < item.size(); i++) {
			if(name.equals(item.get(i).getItemName()))
				return true;
		}
		return false;
	}
	private void UpdateCategory() {
		category = item.stream().map(Item::getCategoryName).distinct().toList();
	}
	/**
	 * 카테고리만 출력
	 */
	public void PrintCategory() {
		List<String> cat =item.stream().map(Item::getCategoryName).distinct().toList(); 
		for(int i = 0; i < cat.size(); i++) {
			System.out.println("["+(i+1)+"]"+cat.get(i));
		}
	}
	
	/**
	 * 해당 카테고리 아이템 출력
	*/
	public void PrintCategoryItem(int idx) {
		if(item.size() == 0) return;
		List<String> cat =item.stream().map(Item::getCategoryName).distinct().toList(); 
		int count = 1;
		for(int i = 0 ; i < item.size(); i++) {
			if(cat.get(idx-1).equals(item.get(i).getCategoryName())) {
				System.out.println("["+(count++)+"]"+item.get(i).toString());
			}
		}
		select = idx-1;
	}
	
	/**
	 * 
	 * @return 카테고리 사이즈
	 */
	public int getCategorySize() {
		return category.size();
	}
	/**
	 * 카테고리를 입력 받은 후 아이템 이름을 입력 받아
	 * 선택한 카테고리와 아이템 이름이 있는지 확인
	 * @param itemname -> 아이템 이름
	 * @return true ->존재
	 */
	public boolean CheckItemname(String itemname) {
		for(int i = 0 ; i < item.size(); i++) {
			if(category.get(select).equals(item.get(i).getCategoryName()) &&
					item.get(i).getItemName().equals(itemname)) {
				return true;
			}
		}
		return false;
	}
	
	
	public void NewItem(String name, String category, int price) {
		item.add(new Item(category, name, price));
		UpdateCategory();
	}
	
	
	public int getItemNo(String itemname) {
		for(Item i : item) {
			if(i.getItemName().equals(itemname)) {
				return i.getItemNum();
			}
		}
		return -1;
	}
	/**
	 * 데이터 로드
	 */
	public void LoadData(String data) {
		if(data == null || data.length() == 0) return;
		if(item.size() != 0)
			item.clear();
		String[] data2 = data.split("\n");
		for(int i = 0 ; i < data2.length ;i++) {
			String[] t = data2[i].split("/");
			item.add(new Item(Integer.parseInt(t[0]),t[1],t[2],Integer.parseInt(t[3])));
		}
		UpdateCategory();
	}
	public void PrintCartData(String data) {
		if(data == null || data.length()< 1) return;
		String[] t = data.split("\n");
		System.out.println("======================");
		int count = 0;
		int sum = 0;
		for(int i = 0 ; i < t.length; i++) {
			String item2[] = t[i].split("/");
			for(int i2 = 0 ; i2 < item.size(); i2++) {
				if(Integer.parseInt(item2[0]) == item.get(i2).getItemNum()) {
					System.out.println("[%3d]%12s(%12s원)\t%s개 총 %d원"
							.formatted((i+1),item.get(i2).getItemName(),item.get(i2).getPrice(),item2[1],(item.get(i2).getPrice() * Integer.parseInt(item2[1]))));
					count += Integer.parseInt(item2[1]);
					sum += Integer.parseInt(item2[1]) * item.get(i2).getPrice();
					break;
				}
			}
		}
		System.out.println("======================");
		System.out.println("총 %3d개 (%10d원)".formatted(count,sum));
	}
	public void PrintAdminCartData(String data) {
		if(data == null || data.length()< 1) return;
		String[] t = data.split("\n");
		System.out.println("========판매된 아이템 목록==========");
		int count = 0;
		int sum = 0;
		for(int i = 0 ; i < t.length; i++) {
			String item2[] = t[i].split("/");
			for(int i2 = 0 ; i2 < item.size(); i2++) {
				if(Integer.parseInt(item2[0]) == item.get(i2).getItemNum()) {
				System.out.println("[%3d] [%5s] [%6s] [%12s원] %s개"
						.formatted(item.get(i2).getItemNum(),item.get(i2).getCategoryName(),
								item.get(i2).getItemName(),item.get(i2).getPrice(),item2[1]));	
					count += Integer.parseInt(item2[1]);
					sum += Integer.parseInt(item2[1]) * item.get(i2).getPrice();
					break;
				}
			}
		}
		System.out.println("======================");
		System.out.println("총 %3d개 (%10d원)".formatted(count,sum));
	}
	public String SaveData() {
		String data ="";
		for(int i = 0 ; i < item.size(); i++) {
			data += item.get(i).Save();
		}
		if(data.length() > 1)
			data = data.substring(0,data.length()-1);
		return data;
	}
	public void PrintAdminItem() {
		List<Item> copy = new ArrayList<Item>(item);
		copy.sort(Comparator.comparing(Item::getCategoryName).reversed().thenComparing(Item::getItemName));
		
		copy.forEach(Item::Info);
	}
	public void DeleteItem(int No) {
		for(int i = 0 ; i <item.size(); i++ ) {
			if( No == item.get(i).getItemNum()) {
				if(item.size() == 1) {
					item.clear();
					return;
				}
				item.remove(i);
				return;
			}
		}
		System.out.println("해당 아이템 번호의 아이템이 존재 하지 않습니다.");
	}
	public int GetLastItemNum() {
		return item.get(item.size()-1).getItemNum();
	}
	public int GetFistItemNum() {
		return item.get(0).getItemNum();
	}
	public void PrintCartItem(String data) {
		
	}
}

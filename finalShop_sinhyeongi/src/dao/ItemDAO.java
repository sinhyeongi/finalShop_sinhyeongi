package dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import dto.Item;

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
	/**
	 * 아이템 이름을 받아서 해당 아이템의 존재를 boolean 으로 리턴
	 * @param name -> 아이템 이름
	 * @return 해당 아이템 이름이 존재 하면 true
	 */
	public boolean CheckItem(String name) {
		for(int i = 0 ; i < item.size(); i++) {
			if(name.equals(item.get(i).getItemName()))
				return true;
		}
		return false;
	}
	/**
	 * 아이템 추가 및 삭제시 카테고리 업데이트
	 */
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
	
	/**
	 * 아이템 추가
	 * @param name -> 아이템 이름
	 * @param category -> 아이템 카테고리
	 * @param price -> 가격
	 */
	public void NewItem(String name, String category, int price) {
		item.add(new Item(category, name, price));
		UpdateCategory();
	}
	
	/**
	 * 아이템 이름에 해당 하는 아이템 번호 리턴
	 * @param itemname -> 아이템 이름
	 * @return 아이템넘버 리턴 / 존재 하지 않는 다면 -1
	 */
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
	/**
	 * 유저 구매내역 출력
	 * cartdao 에서 유저가 구입한 아이템넘버 / 아이템 갯수 를 String 타입으로 받는다 
	 * @param data -> n/n\n .... n/n으로 해당하는 스트링 데이터
	 */
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
	/**
	 * 관리자에서 판매된 아이템 목록 과 갯수, 합산 금액 출력
	 * cartdao 에서 내림차순으로 된 (아이템넘버 / 아이템 갯수) data를 받아서출력 
	 * @param data -> n/n\n .... n/n으로 해당하는 스트링 데이터
	 */
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
	/**
	 * 세이브 관련
	 * @return data -> 전체 회원정보 리턴
	 */
	public String SaveData() {
		String data ="";
		for(int i = 0 ; i < item.size(); i++) {
			data += item.get(i).Save();
		}
		if(data.length() > 1)
			data = data.substring(0,data.length()-1);
		return data;
	}
	/**
	 * 관리자 메뉴(아이템)
	 * 아이템 출력시 카테고리와 아이템 이름으로 정렬 후 출력
	 */
	public void PrintAdminItem() {
		List<Item> copy = new ArrayList<Item>(item);
		copy.sort(Comparator.comparing(Item::getCategoryName).reversed().thenComparing(Item::getItemName));
		
		copy.forEach(Item::Info);
	}
	/**
	 *  아이템 번호를 입력 받아 해당 아이템 삭제
	 * @param No -> 아이템 번호
	 */
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
	/**
	 * 
	 * @return 마지막 아이템 번호 리턴
	 */
	public int GetLastItemNum() {
		return item.get(item.size()-1).getItemNum();
	}
	/**
	 * 
	 * @return 첫번째 아이템 번호 리턴
	 */
	public int GetFistItemNum() {
		return item.get(0).getItemNum();
	}
}

package dto;

import java.util.Objects;

public class Item{
	private static int num;
	private int itemNum;
	private String categoryName;
	private String itemName;
	private int price;
	
	
	@Override
	public int hashCode() {
		return Objects.hash(categoryName);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return Objects.equals(categoryName, other.categoryName);
	}
	
	public static int getNum() {
		return num;
	}
	public static void setNum(int num) {
		Item.num = num;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Item(int itemNum, String categoryName, String itemName, int price) {
		this.itemNum = itemNum;
		this.categoryName = categoryName;
		this.itemName = itemName;
		this.price = price;
		num++;
	}
	public Item(String categoryName, String itemName, int price) {
		this.itemNum = ++num;
		this.categoryName = categoryName;
		this.itemName = itemName;
		this.price = price;
	}
	@Override
	public String toString() {
		return itemName+"\t"+price;
	}
	public String Save() {
		return "%d/%s/%s/%d\n".formatted(itemNum,categoryName,itemName,price);
	}
	public void Info() {
		System.out.println("[%3d] [%5s] [%5s] [%12d원]".formatted(itemNum,categoryName,itemName,price));
	}
	
	
}

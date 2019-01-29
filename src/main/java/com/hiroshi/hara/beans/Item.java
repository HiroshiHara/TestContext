package com.hiroshi.hara.beans;

public class Item {
	
	private String itemId;
	private String itemName;
	private int price;
	
	public Item(String itemId, String itemName, int price) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		final String LINE_SEPARATOR = System.getProperty("line.separator");
		sb.append("商品ID：" + itemId);
		sb.append(LINE_SEPARATOR);
		sb.append("商品名：" + itemName);
		sb.append(LINE_SEPARATOR);
		sb.append("単価：" + price + "円");
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Item)) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		Item other = (Item) obj;
		if (itemId.equals(other.getItemId())) {
			return true;
		}
		return false;
	}

	public String getItemId() {
		return itemId;
	}
	
	public void setItemId(String itemId) {
		this.itemId = itemId;
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
	
}

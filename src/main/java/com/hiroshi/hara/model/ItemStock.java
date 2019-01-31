package com.hiroshi.hara.model;

import java.util.HashMap;

public class ItemStock {
	
	private HashMap<String, Integer> itemStock;
	
	public ItemStock() {
		itemStock = new HashMap<String, Integer>();
	}
	
	/**
	 * 引数に渡された文字列がnullまたは空文字の場合trueを返します。
	 * @param itemId 商品ID
	 * @return 引数がnullまたは空文字の場合true
	 */
	private boolean isItemIdNullOrEmpty(String itemId) {
		if (itemId == null || itemId.equals("")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 引数に渡された商品IDが在庫に登録されている場合trueを返します。
	 * @param itemId 商品ID
	 * @return 在庫が登録済みの場合true
	 * @exception IllegalArgumentException
	 */
	public boolean hasItem(String itemId) {
		if (isItemIdNullOrEmpty(itemId)) {
			throw new IllegalArgumentException("商品IDに無効な値が渡されました");
		}
		if (itemStock.containsKey(itemId)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 引数に渡された商品IDの在庫が残っている場合trueを返します。
	 * @param itemId 商品ID
	 * @return 在庫残がある場合true
	 * @exception IllegalArgumentException
	 * @exception IllegalStateException
	 */
	public boolean hasStock(String itemId) {
		if (isItemIdNullOrEmpty(itemId)) {
			throw new IllegalArgumentException("商品IDに無効な値が渡されました");
		}
		if (!hasItem(itemId)) {
			throw new IllegalStateException("商品IDが登録されていません");
		}
		if (itemStock.get(itemId) > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 商品IDをキー値に在庫数を指定します。
	 * 既に同じ商品IDが登録されている場合は第二引数の分だけ在庫数を加算します。
	 * @param itemId 商品ID
	 * @param num 在庫数
	 * @exception IllegalArgumentException
	 */
	public void incItemStock(String itemId, int num) {
		if (isItemIdNullOrEmpty(itemId)) {
			throw new IllegalArgumentException("商品IDに無効な値が渡されました");
		}
		if (num < 0) {
			throw new IllegalArgumentException("数量に無効な値が渡されました");
		}
		if (hasItem(itemId)) {
			int nowCount = itemStock.get(itemId);
			nowCount += num;
			itemStock.put(itemId, nowCount);
		} else {
			itemStock.put(itemId, num);
		}
	}
	
	/**
	 * 商品IDをキー値に在庫数を減算します。
	 * 指定した商品が登録されていない、または在庫数が負数になるときIllegalStateExceptionが送出されます。
	 * @param itemId 商品ID
	 * @param num 在庫数
	 * @exception IllegalArgumentException
	 * @exception IllegalStateException
	 */
	public void decItemStock(String itemId, int num) {
		if (isItemIdNullOrEmpty(itemId)) {
			throw new IllegalArgumentException("商品IDに無効な値が渡されました");
		}
		if (num < 0) {
			throw new IllegalArgumentException("数量に無効な値が渡されました");
		}
		if (!hasItem(itemId)) {
			throw new IllegalStateException("商品IDが登録されていません");
		}
		int nowCount = itemStock.get(itemId);
		nowCount -= num;
		if (nowCount < 0) {
			throw new IllegalStateException("在庫数がマイナスになります");
		}
		itemStock.put(itemId, nowCount);
	}
		
	/**
	 * 商品IDをキーに在庫数を取得します。
	 * 指定された商品IDが登録されていない場合、0を返します。
	 * @param itemId 商品ID
	 * @return 在庫数
	 * @exception IllegalArgumentException
	 */
	public int acquireItemStock(String itemId) {
		if (isItemIdNullOrEmpty(itemId)) {
			throw new IllegalArgumentException("商品IDに無効な値が渡されました");
		}
		if (hasItem(itemId)) {
			return itemStock.get(itemId);
		}
		return 0;
	}
	
	
}

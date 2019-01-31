package com.hiroshi.hara.model;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/*
 * ある商品
 * (商品ID：0001, 商品名：商品A, 単価：100円)
 * (商品ID：0002, 商品名：商品B, 単価：400円)
 * (商品ID：0003, 商品名：商品C, 単価：2000円)
 * が存在することを想定して以下のテストを行う。
 */
@RunWith(Enclosed.class)
public class ItemStockTest {

	public static class itemStockが空の場合 {
		ItemStock sut;
		
		@Before
		public void setUp() {
			sut = new ItemStock();
		}
		
		@Test
		public void hasItemがfalseを返す() throws Exception {
			// Exercise
			boolean actual = sut.hasItem("0001");
			// Verify
			assertThat(actual, is(false));
		}
		
		@Test(expected = IllegalStateException.class)
		public void hasStockがIllegalStateExceptionを送出する() throws Exception {
			// Exercise
			sut.hasStock("0001");
		}
		
		@Test
		public void incItemStockで商品と在庫数が登録できる() throws Exception {
			// Exercise
			sut.incItemStock("0001", 3);
			boolean actualHasItem = sut.hasItem("0001");
			int actualAcquireItemStock = sut.acquireItemStock("0001");
			// Verify
			assertThat(actualHasItem, is(true));
			assertThat(actualAcquireItemStock, is(3));
		}
				
		@Test
		public void acquireItemStockで0が取得できる() throws Exception {
			// Exercise
			int actual = sut.acquireItemStock("0001");
			// Verify
			assertThat(actual, is(0));
		}
	}
	
	public static class itemStockが商品Aを5個・商品Bを0個を含む場合 {
		ItemStock sut;
		
		@Before
		public void setUp() {
			sut = new ItemStock();
			sut.incItemStock("0001", 5);
			sut.incItemStock("0002", 0);
		}
		
		@Test
		public void hasItemがtrueを返す() throws Exception {
			// Exercise
			boolean actual = sut.hasItem("0001");
			// Verify
			assertThat(actual, is(true));
		}
		
		@Test
		public void hasStockがtrueを返す() throws Exception {
			// Exercise
			boolean actual = sut.hasStock("0001");
			// Verify
			assertThat(actual, is(true));
		}
		
		@Test
		public void hasStockがfalseを返す() throws Exception {
			// Exercise
			boolean actual = sut.hasStock("0002");
			// Verify
			assertThat(actual, is(false));
		}
		
		@Test
		public void incItemStockで商品Aの在庫を増やす() throws Exception {
			// Exercise
			sut.incItemStock("0001", 3);
			int actual = sut.acquireItemStock("0001");
			// Verify
			assertThat(actual, is(8));
		}
		
		@Test
		public void decItemStockで商品Aの在庫を減らす() throws Exception {
			// Exercise
			sut.decItemStock("0001", 2);
			int actual = sut.acquireItemStock("0001");
			// Verify
			assertThat(actual, is(3));
		}
		
		@Test(expected = IllegalStateException.class)
		public void decItemStockで商品Bの在庫をマイナスにしてIllegalStateExceptionを送出する() throws Exception {
			// Exercise
			sut.decItemStock("0002", 2);
		}
	}
	
	public static class itemStockの状態を考慮しない場合 {
		ItemStock sut;
		
		@Before
		public void setUp() {
			sut = new ItemStock();
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void hasItemがnullを渡されたときIllegalArgumentExceptionを送出する() throws Exception {
			// Exercise
			sut.hasItem(null);
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void hasItemが空文字を渡されたときIllegalArgumentExceptionを送出する() throws Exception {
			// Exercise
			sut.hasItem("");
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void incItemStockに負数を渡してIllegalArgumentExceptionを送出する() throws Exception {
			// Exercise
			sut.incItemStock("0001", -1);
		}
		
		@Test(expected = IllegalArgumentException.class)
		public void decItemStockに負数を渡してIllegalArgumentExceptionを送出する() throws Exception {
			// Exercise
			sut.decItemStock("0001", -1);
		}
		
		@Test(expected = IllegalStateException.class)
		public void decItemStockに登録されていない商品IDを渡してIllegalStateExceptionを送出する() throws Exception {
			// Exercise
			sut.decItemStock("0001", 1);
		}
	}
}

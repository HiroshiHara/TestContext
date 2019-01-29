package com.hiroshi.hara.beans;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {

	private Item sut;
	
	@Before
	public void setUp() {
		sut = new Item("testId", "testName", 100);
	}
	
	@Test
	public void toStringTest() throws Exception {
		// SetUp
		final String LINE_SEPARATOR = System.getProperty("line.separator");
		String expected = "商品ID：testId"       + LINE_SEPARATOR
                                  + "商品名：testName" + LINE_SEPARATOR
                                  + "単価：100円";
		// Exercise
		String actual = sut.toString();
		// Verify
		assertThat(actual, is(expected));
	}
	
	@Test
	public void hashCodeで同じ商品IDを持つインスタンスは戻り値が同じになるか() throws Exception {
		// SetUp
		Item item = new Item("testId", "testName", 100);
		int expected = item.hashCode();
		// Exercise
		int actual = sut.hashCode();
		// Verify
		assertThat(actual, is(expected));
	}
	
	@Test
	public void hashCodeで異なる商品IDを持つインスタンスは戻り値も異なるか() throws Exception {
		// SetUp
		Item item = new Item("diffId", "testName", 100);
		int expected = item.hashCode();
		// Exercise
		int actual = sut.hashCode();
		// Verify
		assertThat(actual, is(not(expected)));
	}
	
	@Test
	public void equalsで同じ商品IDを持つインスタンスを比較したときtrueを返すか() throws Exception {
		// SetUp
		Item sameIdItem = new Item("testId", "testName", 100);
		// Exercise
		boolean actual = sut.equals(sameIdItem);
		// Verify
		assertThat(actual, is(true));
	}
	
	@Test
	public void equalsで異なる商品IDを持つインスタンスを比較したときfalseを返すか() throws Exception {
		// SetUp
		Item diffIdItem = new Item("diffId", "testName", 100);
		// Exercise
		boolean actual = sut.equals(diffIdItem);
		// Verify
		assertThat(actual, is(false));
	}
	
	@Test
	public void equalsでnullを渡したときにfalseを返すか() throws Exception {
		// SetUp
		Item nullItem = null;
		// Exercise
		boolean actual = sut.equals(nullItem);
		// Verify
		assertThat(actual, is(false));
	}
	
	@Test
	public void equalsでItemクラスと互換性のない型を比較したときfalseを返すか() throws Exception {
		// SetUp
		String str = new String("test");
		// Exercise
		boolean actual = sut.equals(str);
		// Verify
		assertThat(actual, is(false));
	}
	
	@Test
	public void getItemIdTest() throws Exception {
		// Verify
		assertThat(sut.getItemId(), is("testId"));
	}
	
	@Test
	public void setItemIdTest() throws Exception {
		// Exercise
		sut.setItemId("set");
		// Verify
		assertThat(sut.getItemId(), is("set"));
	}
	
	@Test
	public void getItemNameTest() throws Exception {
		// Verify
		assertThat(sut.getItemName(), is("testName"));
	}
	
	@Test
	public void setItemNameTest() throws Exception {
		// Exercise
		sut.setItemName("set");
		// Verify
		assertThat(sut.getItemName(), is("set"));
	}
	
	@Test
	public void getPriceTest() throws Exception {
		// Verify
		assertThat(sut.getPrice(), is(100));
	}
	
	@Test
	public void setPriceTest() throws Exception {
		// Exercise
		sut.setPrice(200);
		// Verify
		assertThat(sut.getPrice(), is(200));
	}

}

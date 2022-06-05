package com.poly.utils;

import java.util.HashMap;
import java.util.Map;

import com.poly.bean.Item;

public class DB {
	public static Map<Integer, Item> items = new HashMap<>();
	static {
		//1. danh sách có 5 món hàng
		items.put(1, new Item(1, "Samsung", null, 10.0, 0));
		items.put(2, new Item(2, "Nokia 2021", null, 20.50, 0));
		items.put(3, new Item(3, "iPhone 20", null, 90.49, 0));
		items.put(4, new Item(4, "Motorola", null, 15.55, 0));
		items.put(5, new Item(5, "Seamen", null, 8.99, 0));
	}
}
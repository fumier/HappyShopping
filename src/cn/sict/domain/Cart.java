package cn.sict.domain;

import java.util.LinkedHashMap;
import java.util.Map;

//代表用户的购物车
public class Cart
{
	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();
	private double price;
	public Map<String, CartItem> getMap()
	{
		return map;
	}

	public void setMap(Map<String, CartItem> map)
	{
		this.map = map;
	}

	public double getPrice()
	{
		double totalPrice = 0;
		for (Map.Entry<String, CartItem> entry : map.entrySet())
		{
			CartItem item = entry.getValue();
			totalPrice += item.getBuyPrice();
		}
		return totalPrice;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}
}

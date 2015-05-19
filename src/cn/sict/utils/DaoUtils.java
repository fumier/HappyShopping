package cn.sict.utils;

import org.dom4j.Element;

import cn.sict.domain.Address;
import cn.sict.domain.Book;
import cn.sict.domain.CartItem;
import cn.sict.domain.Info;
import cn.sict.domain.Trade;

public class DaoUtils
{
	public static CartItem ElementToCartItem(Element e)
	{
		Book book = new Book();
		CartItem item=new CartItem();
		String bookid = e.attributeValue("bookid");
		String name = e.attributeValue("name");
		String author = e.attributeValue("author");
		double price = Double.parseDouble(e.attributeValue("price"));
		int quantity = Integer.parseInt(e.attributeValue("quantity"));
		String describe = e.attributeValue("describe");
		String imagepath = e.attributeValue("imagepath");
		String classify=e.attributeValue("classify");
		int buynum=Integer.parseInt(e.attributeValue("buynum"));
		double buyprice=buynum*price;
		book.setBookID(bookid);
		book.setBookName(name);
		book.setBookAuthor(author);
		book.setBookPrice(price);
		book.setBookQuantity(quantity);
		book.setBookDescribe(describe);
		book.setImagePath(imagepath);
		book.setBookClassify(classify);
		item.setBook(book);
		item.setBuyQuantity(buynum);
		item.setBuyPrice(buyprice);
		return item;
	}
	
	public static Trade ElementToTrade(Element e)
	{
		Trade trade=new Trade();
		CartItem cartItem=ElementToCartItem(e);
		trade.setCartItem(cartItem);
		trade.setTradeID(WebUtils.generateID());
		return trade;
	}
	
	public static Info ElementToInfo(Element e)
	{
		Info info=new Info();
		Address address=new Address();
		String infoID=e.attributeValue("infoid");
		String personName=e.attributeValue("personname");
		String province=e.attributeValue("province");
		String city=e.attributeValue("city");
		String area=e.attributeValue("area");
		String details=e.attributeValue("details");
		String zipcode=e.attributeValue("zipcode");
		String telephone=e.attributeValue("telephone");
		String defaultAddress=e.attributeValue("default");
		address.setProvince(province);
		address.setCity(city);
		address.setArea(area);
		address.setDetails(details);
		address.setZipcode(zipcode);
		info.setAddress(address);
		info.setInfoID(infoID);
		info.setPersonName(personName);
		info.setTelephone(telephone);
		info.setDefaultAddress(defaultAddress.equals("1")?true:false);
		return info;
	}

}

package cn.sict.dao.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import cn.sict.dao.CartDao;
import cn.sict.domain.Book;
import cn.sict.domain.Cart;
import cn.sict.domain.CartItem;
import cn.sict.utils.DaoUtils;
import cn.sict.utils.XMLUtils;

public class CartDaoImpl implements CartDao
{
	private String userID;
	private Document document;
	private XMLUtils xmlUtils;
	Map<String,CartItem> cartMap;
	public CartDaoImpl(String userID) throws Exception
	{
		this.userID=userID;
		xmlUtils = new XMLUtils("carts.xml");
		document=xmlUtils.getDocument();
		cartMap=new LinkedHashMap<String, CartItem>();
	}
	//XMLUtils xmlUtils = new XMLUtils("carts.xml");
	
	
	@Override
	public Cart findCartByUserID() throws Exception
	{
		
		Cart cart=new Cart();
		double totalPrice =0.00;
		Node node=document.selectSingleNode("//cart[@userid='"+userID+"']");
		if(node==null)
		{
			createCartByUserID();
			return null;
		}
		@SuppressWarnings("unchecked")
		List<Element> list=(List<Element>)node.selectNodes("cartitem");
		for(Iterator<Element> iter=list.iterator();iter.hasNext();)
		{
			Element e=iter.next();
			CartItem item=DaoUtils.ElementToCartItem(e);
			String bookid=item.getBook().getBookID();
			cartMap.put(bookid, item);
			totalPrice+=item.getBuyPrice();
		}
		cart.setMap(cartMap);
		cart.setPrice(totalPrice);
		return cart;
	}
	
	
	@Override
	public Element findCartItem(String bookID) throws Exception
	{
		Element element=(Element)document.selectSingleNode("//cart[@userid='"+userID+"']/cartitem[@bookid='"+bookID+"']");
		return element;
	}

	@Override
	public Boolean deleteCartByID(String id) throws Exception
	{
	 Element node=(Element)document.selectSingleNode("//cart[@userid='"+userID+"']");//Cart节点
     Element element= findCartItem(id);//Cart的子节点CartItem
     Boolean flag=node.remove(element); 
     if(flag==true)
     {
     xmlUtils.writeToXml(document);
     return true;
     }
     return false;
	}

	@Override
	public void addToCart(Book book, int buyBookNum) throws Exception
	{
		// TODO Auto-generated method stub
		//先判断购物车中有没有相应的商品，查找carts.xml，将查找的结果存入cartMap中
		String bookID=book.getBookID();
		Cart cart=findCartByUserID();
		CartItem item=cartMap.get(book.getBookID());
		if(item==null)
		{
			item=new CartItem();
			item.setBook(book);
			item.setBuyQuantity(buyBookNum);
			cartMap.put(book.getBookID(), item);
			ADDToXML(item);
		}
		else
		{
			
			item.setBuyQuantity(item.getBuyQuantity()+buyBookNum);
			//将增加的数量写到xml文件中
			//先查找到该用户存在cartitem,然后再修改buynum的属性值
			Element element=findCartItem(bookID);
			int oldNum=Integer.parseInt(element.attribute("buynum").getValue());
			int newNum=oldNum+buyBookNum;
			element.addAttribute("buynum", newNum+"");
			xmlUtils.writeToXml(document);
		}
	}

	public void ADDToXML(CartItem item) throws Exception
	{
		Element element=(Element)document.selectSingleNode("//cart[@userid='"+userID+"']");
		Element cartitem_tag=element.addElement("cartitem");
		cartitem_tag.addAttribute("bookid",item.getBook().getBookID());
		cartitem_tag.addAttribute("name",item.getBook().getBookName());
		cartitem_tag.addAttribute("author",item.getBook().getBookAuthor());
		cartitem_tag.addAttribute("price",String.valueOf(item.getBook().getBookPrice()));
		cartitem_tag.addAttribute("quantity",String.valueOf(item.getBook().getBookQuantity()));
		cartitem_tag.addAttribute("buynum",String.valueOf(item.getBuyQuantity()));
		cartitem_tag.addAttribute("imagepath",item.getBook().getImagePath());
		xmlUtils.writeToXml(document);
	}


	@Override
	public Boolean deleteCartByUserID() throws Exception
	{
		Element cartElement=(Element)document.selectSingleNode("//cart[@userid='"+userID+"']");
		for(@SuppressWarnings("unchecked")
		Iterator<Element> iter=cartElement.elementIterator();iter.hasNext();)
		{
			cartElement.remove(iter.next());
		}
			xmlUtils.writeToXml(document);
			return true;

	}


	@Override
	public void createCartByUserID() throws Exception
	{
		// TODO Auto-generated method stub
		Element root=document.getRootElement();
	    root.addElement("cart").addAttribute("userid",userID);
	    xmlUtils.writeToXml(document);
	}


	
}

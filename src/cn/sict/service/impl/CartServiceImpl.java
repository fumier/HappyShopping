package cn.sict.service.impl;

import org.dom4j.Element;

import cn.sict.dao.CartDao;
import cn.sict.dao.impl.CartDaoImpl;
import cn.sict.domain.Book;
import cn.sict.domain.Cart;
import cn.sict.domain.CartItem;
import cn.sict.service.CartService;
import cn.sict.utils.DaoUtils;

public class CartServiceImpl implements CartService
{
	CartDao cartDao=null;
	public CartServiceImpl(String userID) throws Exception
	{
		cartDao=new CartDaoImpl(userID);
	}
	
	@Override
	public Cart findCartByUserID() throws Exception
	{
		
		return cartDao.findCartByUserID();
	}

	@Override
	public Element findCartItem(String bookID) throws Exception
	{
		// TODO Auto-generated method stub
		
		return cartDao.findCartItem(bookID);
	}

	@Override
	public Boolean addToCart(Book book, int buyBookNum) throws Exception
	{
		// TODO Auto-generated method stub
		int bookQuantity=book.getBookQuantity();
		if(buyBookNum>bookQuantity)
		{
			return false;
		}
		cartDao.addToCart(book,buyBookNum);
		return true;
		
	}

	@Override
	public Boolean deleteCartItemByID(String bookID) throws Exception
	{
		cartDao.deleteCartByID(bookID);
		Element element=cartDao.findCartItem(bookID);
		if(element==null)
		{
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteCartByUserID() throws Exception
	{
		
		return cartDao.deleteCartByUserID();
	}

	@Override
	public CartItem findCartItem1(String bookID) throws Exception
	{
		// TODO Auto-generated method stub
		Element element=findCartItem(bookID);
		if(element!=null)
		return DaoUtils.ElementToCartItem(element);
		else return null;
	}

}

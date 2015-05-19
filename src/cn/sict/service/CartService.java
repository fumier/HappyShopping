package cn.sict.service;

import org.dom4j.Element;

import cn.sict.domain.Book;
import cn.sict.domain.Cart;
import cn.sict.domain.CartItem;

public interface CartService
{
 Cart findCartByUserID() throws Exception;
 Element findCartItem(String bookID) throws Exception;
 CartItem findCartItem1(String bookID)throws Exception;
 Boolean addToCart(Book book,int buyBookNum) throws Exception;
 Boolean deleteCartItemByID(String bookID) throws Exception;
 Boolean deleteCartByUserID()throws Exception;
}

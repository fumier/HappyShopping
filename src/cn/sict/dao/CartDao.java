package cn.sict.dao;

import org.dom4j.Element;

import cn.sict.domain.Book;
import cn.sict.domain.Cart;
import cn.sict.domain.CartItem;

public interface CartDao
{
  /**
 * @param id 根据给定的id查找carts.xml中是否有对应的数据
 * @return Cart
 * @throws Exception
 */
Cart findCartByUserID() throws Exception;
Element findCartItem(String str1) throws Exception;
Boolean deleteCartByID(String id) throws Exception;
void addToCart(Book book,int buyBookNum) throws Exception;
Boolean deleteCartByUserID() throws Exception;
public void createCartByUserID() throws Exception;
}

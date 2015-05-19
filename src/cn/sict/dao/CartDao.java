package cn.sict.dao;

import org.dom4j.Element;

import cn.sict.domain.Book;
import cn.sict.domain.Cart;
import cn.sict.domain.CartItem;

public interface CartDao
{
  /**
 * @param id ���ݸ�����id����carts.xml���Ƿ��ж�Ӧ������
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

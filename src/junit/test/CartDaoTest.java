package junit.test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import cn.sict.dao.BookDao;
import cn.sict.dao.CartDao;
import cn.sict.dao.impl.BookDaoImpl;
import cn.sict.dao.impl.CartDaoImpl;
import cn.sict.domain.Book;
import cn.sict.domain.Cart;
import cn.sict.domain.CartItem;


public class CartDaoTest
{
@Test
public void testFindCart() throws Exception
{
	CartDao cartDao=new CartDaoImpl("3143254235");
	Cart cart=cartDao.findCartByUserID();
	double totalPrice=cart.getPrice();
	Map<String,CartItem> map=cart.getMap();
	Set<Map.Entry<String,CartItem>> set=map.entrySet();
    for(Iterator<Map.Entry<String,CartItem>> iter=set.iterator();iter.hasNext();)
    {
    	Map.Entry<String,CartItem> entry=iter.next();
    	CartItem item=entry.getValue();
    	String bookID=entry.getKey();
    	System.out.println("bookID"+bookID);
    	System.out.println("bookName"+item.getBook().getBookName());
    }
	System.out.println(totalPrice);
}

@Test
public void testAddToCart() throws Exception
{
	CartDao cartDao=new CartDaoImpl("3143254235");
	//BookDao bookDao=new BookDaoImpl();
	cartDao.findCartByUserID();
	Book book=new Book();
	book.setBookID("3");
	book.setBookName("JSP");
	book.setBookAuthor("ÀîËÄ");
	book.setBookPrice(23);
	book.setBookQuantity(20);
	book.setImagePath("/bookpic/sjjg.jpg");
	cartDao.addToCart(book,2);
}

@Test
public void testDeleteCartItemByID() throws Exception
{
	CartDao cartDao=new CartDaoImpl("3143254235");
	cartDao.deleteCartByID("3");
}
}

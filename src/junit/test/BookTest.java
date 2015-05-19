package junit.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import cn.sict.dao.BookDao;
import cn.sict.dao.impl.BookDaoImpl;
import cn.sict.domain.Book;

public class BookTest
{

     @Test
	public void testFindBook() throws Exception
	{
		BookDao bookDao = new BookDaoImpl();
		Map<String,Book> map = bookDao.findAllBooks();
		Book book = new Book();
		Set<Map.Entry<String, Book>> set=map.entrySet();
		 for(Iterator<Map.Entry<String, Book>> iter=set.iterator();iter.hasNext();)
		 {
		 Map.Entry<String, Book> entry=iter.next();
		 book=entry.getValue();
		 System.out.println(book.getBookID()+","+book.getBookName()+","+book.getBookAuthor()+","+
		 
				 book.getBookPrice()+","+book.getBookQuantity()+","+book.getBookDescribe()+","+book.getImagePath());
		 }
	}
	
	@Test
	public void testGetPageData() throws Exception
	{
		BookDao bookDao=new BookDaoImpl();
		List<Book> list=bookDao.getPageData(0, 2);
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i).getBookName());
		}
	}
	
	@Test
	public void testTotalBookNum() throws Exception
	{
		BookDao bookDao=new BookDaoImpl();
		int num=bookDao.getTotalBookNum();
		System.out.println(num);
	}

}

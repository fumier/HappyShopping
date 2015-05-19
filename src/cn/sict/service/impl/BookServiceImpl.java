package cn.sict.service.impl;

import java.util.List;
import java.util.Map;

import cn.sict.dao.BookDao;
import cn.sict.dao.impl.BookDaoImpl;
import cn.sict.domain.Book;
import cn.sict.service.BookService;

public class BookServiceImpl implements BookService
{
private BookDao dao=new BookDaoImpl();
String message=null;

/* (non-Javadoc)
 * @see cn.sict.service.impl.BookService#showAllBook()
 */
@Override
public Map<String,Book> showAllBook() throws Exception
{
	return dao.findAllBooks();
}

/* (non-Javadoc)
 * @see cn.sict.service.BookService#pageData(int, int)
 */
public List<Book> pageData(int startIndex,int pageSize) throws Exception
{
	return dao.getPageData(startIndex, pageSize);
}

@Override
public int totalBookNum() throws Exception
{
	// TODO Auto-generated method stub
	return dao.getTotalBookNum();
}

/**
 * @param bookID 根据书的编号查找书籍
 * @return
 * @throws Exception 
 */
public Book findBookByID(String bookID) throws Exception
{
	return dao.findBookByID(bookID);
}
 
}

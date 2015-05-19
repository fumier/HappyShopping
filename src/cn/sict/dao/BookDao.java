package cn.sict.dao;

import java.util.List;
import java.util.Map;

import cn.sict.domain.Book;

public interface BookDao
{

	Map<String,Book> findAllBooks() throws Exception;


	/**
	 * @return 书籍的总数目
	 * @throws Exception
	 */
	int getTotalBookNum() throws Exception;

	/**
	 * @param startIndex 每页记录显示的第一条索引
	 * @param pageSize  每页显示的记录数
	 * @return 每页需要显示记录的列表
	 * @throws Exception 
	 */
	List<Book> getPageData(int startIndex, int pageSize) throws Exception;

    
	Book findBookByID(String id) throws Exception;
}
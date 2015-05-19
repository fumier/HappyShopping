package cn.sict.service;

import java.util.List;
import java.util.Map;

import cn.sict.domain.Book;

public interface BookService
{
    //对web层提供显示所有书籍的服务
	Map<String, Book> showAllBook() throws Exception;
    List<Book> pageData(int startIndex,int pageSize) throws Exception;
    int totalBookNum() throws Exception;
    Book findBookByID(String bookID) throws Exception;
}
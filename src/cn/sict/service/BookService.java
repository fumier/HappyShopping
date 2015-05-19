package cn.sict.service;

import java.util.List;
import java.util.Map;

import cn.sict.domain.Book;

public interface BookService
{
    //��web���ṩ��ʾ�����鼮�ķ���
	Map<String, Book> showAllBook() throws Exception;
    List<Book> pageData(int startIndex,int pageSize) throws Exception;
    int totalBookNum() throws Exception;
    Book findBookByID(String bookID) throws Exception;
}
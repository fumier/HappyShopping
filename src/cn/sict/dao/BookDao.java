package cn.sict.dao;

import java.util.List;
import java.util.Map;

import cn.sict.domain.Book;

public interface BookDao
{

	Map<String,Book> findAllBooks() throws Exception;


	/**
	 * @return �鼮������Ŀ
	 * @throws Exception
	 */
	int getTotalBookNum() throws Exception;

	/**
	 * @param startIndex ÿҳ��¼��ʾ�ĵ�һ������
	 * @param pageSize  ÿҳ��ʾ�ļ�¼��
	 * @return ÿҳ��Ҫ��ʾ��¼���б�
	 * @throws Exception 
	 */
	List<Book> getPageData(int startIndex, int pageSize) throws Exception;

    
	Book findBookByID(String id) throws Exception;
}
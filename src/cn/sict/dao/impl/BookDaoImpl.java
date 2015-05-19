package cn.sict.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.sict.dao.BookDao;
import cn.sict.domain.Book;
import cn.sict.utils.XMLUtils;

public class BookDaoImpl implements BookDao
{
	XMLUtils xmlUtils = new XMLUtils("books.xml");

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.sict.dao.impl.BookDao#findAllBooks()
	 */
	@Override
	public Map<String, Book> findAllBooks() throws Exception
	{
		Map<String, Book> map = new TreeMap<String, Book>();
		Document document = xmlUtils.getDocument();
		// ��XML�ļ��е�ÿһ��������book����װ��Ȼ�����List���У�Ȼ�󷵻�list����ҳ������ʾ��
		// �Ȼ�ȡroot�ڵ�
		Element root = document.getRootElement();
		// ö�����е��ӽڵ�
		for (Iterator iter = root.elementIterator(); iter.hasNext();)
		{
			Element book_tag = (Element) iter.next();
			// �����������õ���ֵ��װ��book��;
			String id = book_tag.attributeValue("id");
			Book book = addBook(book_tag);
			map.put(id, book);
		}
		return map;
	}

	// ���ڵ������ֵ��װ��book��
	public static Book addBook(Element e)
	{
		Book book = new Book();
		String id = e.attributeValue("id");
		String name = e.attributeValue("name");
		String author = e.attributeValue("author");
		double price = Double.parseDouble(e.attributeValue("price"));
		int quantity = Integer.parseInt(e.attributeValue("quantity"));
		String describe = e.attributeValue("describe");
		String imagepath = e.attributeValue("imagepath");
		String classify=e.attributeValue("classify");
		book.setBookID(id);
		book.setBookName(name);
		book.setBookAuthor(author);
		book.setBookPrice(price);
		book.setBookQuantity(quantity);
		book.setBookDescribe(describe);
		book.setImagePath(imagepath);
		book.setBookClassify(classify);
		return book;
	}

	/* (non-Javadoc)
	 * @see cn.sict.dao.impl.BookDao#getTotalBookNum()
	 */
	@Override
	public int getTotalBookNum() throws Exception
	{
		int count = 0;
		Document document = xmlUtils.getDocument();
		Element root = document.getRootElement();
		count=root.elements().size();
		return count;
	}
	
	/* (non-Javadoc)
	 * @see cn.sict.dao.impl.BookDao#getPageData(int, int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Book> getPageData(int startIndex,int pageSize) throws Exception
	{
		
		
		List<Book> list = new ArrayList<Book>();
		Document document = xmlUtils.getDocument();
		// ��XML�ļ��е�ÿһ��������book����װ��Ȼ�����List���У�Ȼ�󷵻�list����ҳ������ʾ��
		// �Ȼ�ȡroot�ڵ�
		Element root = document.getRootElement();
		for (Iterator iter = root.elementIterator(); iter.hasNext();)
		{
			Element book_tag = (Element) iter.next();
			// �����������õ���ֵ��װ��book��;
			Book book = addBook(book_tag);
			list.add(book);
		}
		int endIndex=startIndex+pageSize;
		if(endIndex>list.size())
		{
			endIndex=list.size();
		}
		if(startIndex>endIndex)
		{
			return null;
		}
		list=list.subList(startIndex, endIndex);//subList��ȡ�ķ�Χ������ҿ�
		return list;
		
	}

	public Book findBookByID(String id) throws Exception
	{
		try{
			Document document=xmlUtils.getDocument();
			Element e=(Element)document.selectSingleNode("//book[@id='"+id+"']");
			if(e==null)
			{
			return null;
			}
			Book book=addBook(e);
			return book;
		}catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	

}
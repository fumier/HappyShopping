package junit.test;

import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.sict.utils.XMLUtils;

public class BookAddAttribute
{
public static void main(String[] args) throws Exception
{
	XMLUtils xmlUtils = new XMLUtils("books.xml");
	Document document = xmlUtils.getDocument();
	Element root=document.getRootElement();
	// 枚举所有的子节点
	for (Iterator iter = root.elementIterator(); iter.hasNext();)
	{
		Element book_tag = (Element) iter.next();
		book_tag.addAttribute("classify", "计算机类");
		System.out.println("添加成功");
	}
}
}

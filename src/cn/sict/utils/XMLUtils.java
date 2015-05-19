package cn.sict.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XMLUtils
{
	private String filepath;
	public XMLUtils(String filename)
	{
//		filepath=XMLUtils.class.getClassLoader().getResource(filename).getPath();
	      filepath="D://JavaWeb/Happyshopping/src/"+filename;//绝对的物理路径
	}
//	static
//	{
//		filepath = XMLUtils.class.getClassLoader().getResource("users.xml")
//				.getPath();
//	}
    //获得文件
	public Document getDocument() throws Exception
	{
		SAXReader reader=new SAXReader();
		Document document=reader.read(new File(filepath));
		return document;

	}
    //写入文件
	public  void writeToXml(Document document) throws Exception
	{
       OutputFormat format=OutputFormat.createPrettyPrint();
       format.setEncoding("utf-8");
       XMLWriter writer=new XMLWriter(new FileOutputStream(filepath),format);
       writer.write(document);
       writer.close();
	}	
}

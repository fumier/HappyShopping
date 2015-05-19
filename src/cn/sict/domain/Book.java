package cn.sict.domain;

public class Book
{
private String bookID;
private String bookName;
private String bookAuthor;
private double bookPrice;
private int bookQuantity;
private String bookDescribe;
private String imagePath;
private String bookClassify;


public String getBookClassify()
{
	return bookClassify;
}
public void setBookClassify(String bookClassify)
{
	this.bookClassify = bookClassify;
}
public String getBookID()
{
	return bookID;
}
public void setBookID(String bookID)
{
	this.bookID = bookID;
}
public String getBookName()
{
	return bookName;
}
public void setBookName(String bookName)
{
	this.bookName = bookName;
}
public double getBookPrice()
{
	return bookPrice;
}
public void setBookPrice(double bookPrice)
{
	this.bookPrice = bookPrice;
}
public String getBookAuthor()
{
	return bookAuthor;
}
public void setBookAuthor(String bookAuthor)
{
	this.bookAuthor = bookAuthor;
}
public String getBookDescribe()
{
	return bookDescribe;
}
public void setBookDescribe(String bookDescribe)
{
	this.bookDescribe = bookDescribe;
}
public void setBookQuantity(int bookQuantity)
{
	this.bookQuantity = bookQuantity;
}
public int getBookQuantity()
{
	return bookQuantity;
}
public String getImagePath()
{
	return imagePath;
}
public void setImagePath(String imagePath)
{
	this.imagePath = imagePath;
}
}

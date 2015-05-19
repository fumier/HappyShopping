package cn.sict.domain;

public class CartItem
{
private Book book;
private int buyQuantity;
private double buyPrice;
public double getBuyPrice()
{
	return buyPrice;
}

public void setBuyPrice(double buyPrice)
{
	this.buyPrice = buyPrice;
}

public int getBuyQuantity()
{
	return buyQuantity;
}

public void setBuyQuantity(int buyQuantity)
{
	this.buyQuantity = buyQuantity;
}

public Book getBook()
{
	return book;
}

public void setBook(Book book)
{
	this.book = book;
}
}

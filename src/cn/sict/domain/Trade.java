package cn.sict.domain;

public class Trade
{
	private String tradeID;
	private CartItem cartItem;
	private Info info;

	public Info getInfo()
	{
		return info;
	}

	public void setInfo(Info info)
	{
		this.info = info;
	}

	public String getTradeID()
	{
		return tradeID;
	}

	public void setTradeID(String tradeID)
	{
		this.tradeID = tradeID;
	}

	public CartItem getCartItem()
	{
		return cartItem;
	}

	public void setCartItem(CartItem cartItem)
	{
		this.cartItem = cartItem;
	}
}

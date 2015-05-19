package cn.sict.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import cn.sict.dao.CartDao;
import cn.sict.dao.TradeDao;
import cn.sict.domain.Trade;
import cn.sict.utils.DaoUtils;
import cn.sict.utils.XMLUtils;

public class TradeDaoImpl implements TradeDao
{
	private String userID;
	private Document document;
	private XMLUtils xmlUtils;
	private CartDao cartDao;
	Map<String, Trade> tradeMap;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.sict.dao.TradeDao#addToTrade(int)
	 */
	public TradeDaoImpl(String userID) throws Exception
	{
		this.userID = userID;
		xmlUtils = new XMLUtils("trades.xml");
		document = xmlUtils.getDocument();
		cartDao = new CartDaoImpl(userID);
		tradeMap = new TreeMap<String, Trade>();
	}

	//从购物车中结算
	@Override
	public void addToTrade(String[] bookIDs, String tradeID) throws Exception
	{
		// TODO Auto-generated method stub
		// 通过bookid读取购物车中被选中的项
		Trade trade=new Trade();
		if (bookIDs.length != 0)
		{
			String personName=trade.getInfo().getPersonName();
			String Country=trade.getInfo().getAddress().getCountry();
			String city=trade.getInfo().getAddress().getCity();
			String area=trade.getInfo().getAddress().getArea();
			String details=trade.getInfo().getAddress().getDetails();
			String zipcode=trade.getInfo().getAddress().getZipcode();
			String telephone=trade.getInfo().getTelephone();
			String address=Country+","+city+","+area+","+details+","+zipcode;
			String personalInfo=personName+","+address+","+telephone;
			Element trade_tag = (Element) document
					.selectSingleNode("//trade[@userid='" + userID + "']");
			Element tradeitems_tag = trade_tag.addElement("tradeitems");
			tradeitems_tag.addAttribute("tradeid", trade.getTradeID());
			tradeitems_tag.addAttribute("finished", "false");
			tradeitems_tag.addAttribute("personalinfo", personalInfo);
			for (String bookID : bookIDs)
			{
				// 从购物车结算
				Element element = cartDao.findCartItem(bookID);
				trade = DaoUtils.ElementToTrade(element);
				addToXML(tradeitems_tag, trade);
			}
		}
	}
	
	public void addToXML(Element tradeitems_tag, Trade trade) throws Exception
	{
		Element item_tag = tradeitems_tag.addElement("item");
		item_tag.addAttribute("bookid", trade.getCartItem().getBook()
				.getBookID());
		item_tag.addAttribute("name", trade.getCartItem().getBook()
				.getBookAuthor());
		item_tag.addAttribute("author", trade.getCartItem().getBook()
				.getBookAuthor());
		item_tag.addAttribute("price",
				String.valueOf(trade.getCartItem().getBuyPrice()));
		item_tag.addAttribute("buynum",
				String.valueOf(trade.getCartItem().getBuyQuantity()));
		item_tag.addAttribute("imagepath", trade.getCartItem().getBook()
				.getImagePath());
		xmlUtils.writeToXml(document);
	}

	@Override
	public void createTradeByUserID() throws Exception
	{
		Element root = document.getRootElement();
		root.addAttribute("userid", userID);
		xmlUtils.writeToXml(document);
	}

	@Override
	public Boolean deleteTrade(String[] tradeID)
	{
		// TODO Auto-generated method stub
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Trade> findTradeItemsByUserID() throws Exception
	{
		// TODO Auto-generated method stub
		Trade trade = new Trade();
		Node node = document.selectSingleNode("//trade[@userid='" + userID
				+ "']");
		if (node == null)
		{
			createTradeByUserID();
			return null;
		}
		List<Element> list = (List<Element>) node.selectNodes("tradeitems");
		if (list != null)
		{
			for (Iterator<Element> iter = list.iterator(); iter.hasNext();)
			{
				Element e = iter.next();
				trade = DaoUtils.ElementToTrade(e);
				String tradeID = trade.getTradeID();
				tradeMap.put(tradeID, trade);
			}
		}
		return tradeMap;
	}

	

}

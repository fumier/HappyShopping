package junit.test;

import org.dom4j.Element;
import org.junit.Test;

import cn.sict.dao.CartDao;
import cn.sict.dao.TradeDao;
import cn.sict.dao.impl.CartDaoImpl;
import cn.sict.dao.impl.TradeDaoImpl;
import cn.sict.domain.Address;
import cn.sict.domain.Book;
import cn.sict.domain.CartItem;
import cn.sict.domain.Info;
import cn.sict.domain.Trade;
import cn.sict.utils.DaoUtils;

public class TradeDaoImplTest
{
	@Test
	public void testAddToTradeXML() throws Exception
	{
     TradeDao tradeDao=new TradeDaoImpl("3143254235");
     CartDao cartDao=new CartDaoImpl("3143254235");
     Element element=cartDao.findCartItem("1");
     CartItem cartItem=DaoUtils.ElementToCartItem(element);
     Trade trade=new Trade();
     Info info=new Info();
     Address address=new Address();
     address.setCity("aaa");
     address.setArea("bbb");
     address.setDetails("123");
     address.setZipcode("456");
     info.setAddress(address);
     String personName=new String("cc");
     String telephone=new String("13664189894");
     info.setPersonName(personName);
     info.setTelephone(telephone);
     trade.setTradeID("123");
     trade.setInfo(info);
     trade.setCartItem(cartItem);
     tradeDao.addToTrade(new String[]{"1","2"},"123");
     
	}
}

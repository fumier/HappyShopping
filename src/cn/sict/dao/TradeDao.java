package cn.sict.dao;

import java.util.Map;

import cn.sict.domain.Trade;
public interface TradeDao
{
Map<String,Trade> findTradeItemsByUserID() throws Exception;
void addToTrade(String[] bookIDs,String tradeID) throws Exception;
Boolean deleteTrade(String[] bookID);
void createTradeByUserID() throws Exception;
}

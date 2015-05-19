package cn.sict.dao;

import java.util.List;

import org.dom4j.Element;

import cn.sict.domain.Info;

public interface InfoDao
{
		List<Info> findInfoByUserID() throws Exception;
		Info findInfoByInfoID(String infoID)throws Exception;
		List<Element> findInfoElementByUSerID()throws Exception;
		void addInfoToXML(Info info) throws Exception;
		boolean deleteInfo(String infoID) throws Exception;
		void updateInfoDefaultAddress(String defaultAddress) throws Exception;
		void updateInfoByInfoID(Info info) throws Exception;
}

package cn.sict.service;

import java.util.List;

import org.dom4j.Element;

import cn.sict.domain.Info;

public interface InfoService
{
	List<Info> findInfoByUserID() throws Exception;
	Info findInfoByInfoID(String infoID)throws Exception;
	List<Element> findInfoElementByUserID() throws Exception;
	boolean addInfoToXML(Info info) throws Exception;
	boolean deleteInfo(String infoID) throws Exception;
	boolean updateInfoDefaultAddress(String defaultAddress) throws Exception;
	boolean updateInfoByInfoID(String infoID) throws Exception;
	boolean updateInfoByInfo(Info info)throws Exception;
}

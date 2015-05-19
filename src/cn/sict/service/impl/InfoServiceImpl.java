package cn.sict.service.impl;

import java.util.List;

import org.dom4j.Element;

import cn.sict.dao.InfoDao;
import cn.sict.dao.impl.InfoDaoImpl;
import cn.sict.domain.Info;
import cn.sict.service.InfoService;

public class InfoServiceImpl implements InfoService
{
	private InfoDao infoDao;

	public InfoServiceImpl(String userID) throws Exception
	{
		infoDao = new InfoDaoImpl(userID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.sict.service.InfoService#findInfoByUserID()
	 */
	@Override
	public List<Info> findInfoByUserID() throws Exception
	{
		// TODO Auto-generated method stub
		List<Info> listInfo = infoDao.findInfoByUserID();
		if (listInfo.size() == 0 || listInfo == null)
			return null;
		for (int i = 0; i < listInfo.size(); i++)
		{
			if (listInfo.get(0).isDefaultAddress())
				break;
			if (i != 0 && listInfo.get(i).isDefaultAddress())
			{
				Info temp = listInfo.get(i);
				listInfo.add(i, listInfo.get(0));
				listInfo.add(0, temp);
			}
		}
		return listInfo;
	}

	@Override
	public List<Element> findInfoElementByUserID() throws Exception
	{
		// TODO Auto-generated method stub
		return infoDao.findInfoElementByUSerID();
	}

	@Override
	public boolean addInfoToXML(Info info) throws Exception
	{
		// TODO Auto-generated method stub
		List<Info> list = infoDao.findInfoByUserID();
		int itemNum = list.size();
		if (itemNum < 20)
		{
			if (info.isDefaultAddress())
			{
				for (Info in : list)
				{
					if (in.isDefaultAddress())
					{
						String defaultAddress = "1";
						infoDao.updateInfoDefaultAddress(defaultAddress);
					}
				}
			}
			infoDao.addInfoToXML(info);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteInfo(String infoID) throws Exception
	{
		// TODO Auto-generated method stub
		infoDao.deleteInfo(infoID);
		return true;
	}

	@Override
	public boolean updateInfoDefaultAddress(String defaultAddress)
			throws Exception
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateInfoByInfoID(String infoID) throws Exception
	{
		// TODO Auto-generated method stub
		//根据infoID获得整个info
		Info info=infoDao.findInfoByInfoID(infoID);
		//删除该条地址信息
		deleteInfo(infoID);
		//添加信息
		infoDao.addInfoToXML(info);
		return true;
	}

	@Override
	public Info findInfoByInfoID(String infoID) throws Exception
	{
		// TODO Auto-generated method stub
		return infoDao.findInfoByInfoID(infoID);
	}

	@Override
	public boolean updateInfoByInfo(Info info) throws Exception
	{
		// TODO Auto-generated method stub
		if(info==null)return false;
		infoDao.updateInfoByInfoID(info);
		return true;
	}

}

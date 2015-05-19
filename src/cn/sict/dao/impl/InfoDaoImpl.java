package cn.sict.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;

import cn.sict.dao.InfoDao;
import cn.sict.domain.Info;
import cn.sict.utils.DaoUtils;
import cn.sict.utils.XMLUtils;

public class InfoDaoImpl implements InfoDao
{
	private String userID;
	private Document document;
	private XMLUtils xmlUtils;
	private List<Info> listInfo=new ArrayList<Info>();
    public InfoDaoImpl(String userID) throws Exception
    {
    	this.userID=userID;
    	xmlUtils = new XMLUtils("infos.xml");
		document=xmlUtils.getDocument();
    }
    @Override
	public List<Element> findInfoElementByUSerID() throws Exception
	{
		// TODO Auto-generated method stub
		Node node=document.selectSingleNode("//info[@userid='"+userID+"']");
		if(node==null)
		{
			createInfoByUserID();
			return null;
		}
		Element info_element=(Element)node;
		Attribute itemNum_attribute=info_element.attribute("itemNum");
		int itemNum=Integer.parseInt(itemNum_attribute.getStringValue());
		if(itemNum<=0)return null;
		@SuppressWarnings("unchecked")
		List<Element> infoitem_list=(List<Element>)node.selectNodes("infoitem");
		return infoitem_list;
	}

	@Override
	public List<Info> findInfoByUserID() throws Exception
	{
		// TODO Auto-generated method stub
		Info info=new Info();
		List<Element> infoitem_list=this.findInfoElementByUSerID();
		for(Element infoitem_element:infoitem_list)
		{
			info=DaoUtils.ElementToInfo(infoitem_element);
			listInfo.add(info);
		}
//		for(int i=0;i<listInfo.size();i++)
//		{
//			if(listInfo.get(0).isDefaultAddress())break;
//			if(i!=0&&listInfo.get(i).isDefaultAddress())
//			{
//				Info temp=listInfo.get(i);
//				listInfo.add(i, listInfo.get(0));
//				listInfo.add(0, temp);
//			}
//		}
		return listInfo;
	}

	private void createInfoByUserID() throws Exception
	{
		// TODO Auto-generated method stub
		Element root=document.getRootElement();
	    root.addElement("info").addAttribute("userid",userID).addAttribute("itemNum", "0");                      
	    xmlUtils.writeToXml(document);
		
	}

	@Override
	public void addInfoToXML(Info info) throws Exception
	{
		// TODO Auto-generated method stub
		Element root=(Element)document.selectSingleNode("//info[@userid='"+userID+"']");
		String infoid=info.getInfoID();
		String personname=info.getPersonName();
		String country=info.getAddress().getCountry();
		String province=info.getAddress().getProvince();
		String city=info.getAddress().getCity();
		String area=info.getAddress().getArea();
		String details=info.getAddress().getDetails();
		String zipcode=info.getAddress().getZipcode();
		String telephone=info.getTelephone();
		String defaultAddress=(info.isDefaultAddress()==true)?"1":"0";
		Element infoitem_tag=(Element)root.addElement("infoitem");
		infoitem_tag.addAttribute("infoid", infoid);
		infoitem_tag.addAttribute("personname", personname);
		infoitem_tag.addAttribute("country", country);
		infoitem_tag.addAttribute("province", province);
		infoitem_tag.addAttribute("city", city);
		infoitem_tag.addAttribute("area", area);
		infoitem_tag.addAttribute("details", details);
		infoitem_tag.addAttribute("zipcode", zipcode);
		infoitem_tag.addAttribute("telephone", telephone);
		infoitem_tag.addAttribute("default", defaultAddress);
		int itemNum=Integer.parseInt(root.attributeValue("itemNum"));
		itemNum=itemNum+1;
		root.addAttribute("itemNum", itemNum+"");
		xmlUtils.writeToXml(document);
	}
	@Override
	public void updateInfoDefaultAddress(String defaultAddress) throws Exception
	{
		// TODO Auto-generated method stub
		Element element=(Element)document.selectSingleNode("//info[@userid='"+userID+"']/infoitem[@default='"+defaultAddress+"']");
		if(defaultAddress.equals("1"))
		{
		   element.addAttribute("default", "0");
		}else
		{
			element.addAttribute("default", "1");
		}
		xmlUtils.writeToXml(document);
	}
	@Override
	public void updateInfoByInfoID(Info info) throws Exception
	{
		// TODO Auto-generated method stub
		String infoID=info.getInfoID();
		Element element=(Element)document.selectSingleNode("//info[@userid='"+userID+"']/infoitem[@infoid='"+infoID+"']");
		element.addAttribute("personname", info.getPersonName());
		element.addAttribute("country", info.getAddress().getCountry());
		element.addAttribute("province", info.getAddress().getProvince());
		element.addAttribute("city", info.getAddress().getCity());
		element.addAttribute("area", info.getAddress().getArea());
		element.addAttribute("details", info.getAddress().getDetails());
		element.addAttribute("zipcode", info.getAddress().getZipcode());
		element.addAttribute("telephone", info.getTelephone());
		element.addAttribute("default", info.isDefaultAddress()==true?"1":"0");
		xmlUtils.writeToXml(document);
	}
	@Override
	public boolean deleteInfo(String infoID) throws Exception
	{
		// TODO Auto-generated method stub
		Element root_tag=(Element)document.selectSingleNode("//info[@userid='"+userID+"']");
		Element info_tag=(Element)root_tag.selectSingleNode("//infoitem[@infoid='"+infoID+"']");
		Boolean flag=root_tag.remove(info_tag);
		xmlUtils.writeToXml(document);
		return flag;
	}
	@Override
	public Info findInfoByInfoID(String infoID) throws Exception
	{
		// TODO Auto-generated method stub
		Element element=(Element)document.selectSingleNode("//info[@userid='"+userID+"']/infoitem[@infoid='"+infoID+"']");
		Info info=DaoUtils.ElementToInfo(element);
		return info;
	}
}

package cn.sict.dao.impl;

import java.text.SimpleDateFormat;

import org.dom4j.Document;
import org.dom4j.Element;

import cn.sict.dao.UserDao;
import cn.sict.domain.User;
import cn.sict.utils.XMLUtils;

public class UserDaoImpl implements UserDao
{
	XMLUtils xmlUtils=new XMLUtils("users.xml");
/* (non-Javadoc)
 * @see cn.sict.dao.impl.UserDao#add(cn.sict.domain.User)
 */
@Override
@SuppressWarnings("deprecation")
public void add(User user)
{
	try
	{
		
		Document document=xmlUtils.getDocument();
		Element root=document.getRootElement();
		Element user_tag=root.addElement("user");
		user_tag.addAttribute("id", user.getId());
		user_tag.addAttribute("username", user.getUsername());
		user_tag.addAttribute("password", user.getPassword());
		user_tag.addAttribute("email", user.getEmail());
		user_tag.addAttribute("birthday", user.getBirthday()==null?"":user.getBirthday().toLocaleString());
		user_tag.addAttribute("nickname", user.getNickname());
		xmlUtils.writeToXml(document);
	} catch (Exception e)
	{
		throw new RuntimeException(e);
	}
}
/* (non-Javadoc)
 * @see cn.sict.dao.impl.UserDao#find(java.lang.String, java.lang.String)
 */
@Override
public User find(String username,String password)
{
	try
	{
		Document document=xmlUtils.getDocument();
		Element e=(Element)document.selectSingleNode("//user[@username='"+username+"' and @password='"+password+"']");
	    if(e==null)
	    {
	    	return null;
	    }
	    User user=new User();
	    String date=e.attributeValue("birthday");
	    if(date==null ||date.equals(""))
	    {
	    	user.setBirthday(null);
	    }
	    else
	    {
	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	        user.setBirthday(sdf.parse(date));	
	    }
	    
	    user.setEmail(e.attributeValue("email"));
	    user.setId(e.attributeValue("id"));
	    user.setNickname(e.attributeValue("nickname"));
	    user.setPassword(e.attributeValue("password"));
	    user.setUsername(e.attributeValue("username"));
	    return user;
	} catch (Exception e)
	{
    throw new RuntimeException(e);
	}
	
}
//查找注册的用户是否在数据库中存在
/* (non-Javadoc)
 * @see cn.sict.dao.impl.UserDao#find(java.lang.String)
 */
@Override
public boolean find(String username)
{
	try{
		Document document=xmlUtils.getDocument();
		Element e=(Element)document.selectSingleNode("//user[@username='"+username+"']");
		if(e==null)
		{
			return false;
		}
		return true;
		
	}catch(Exception e)
	{
		throw new RuntimeException(e);
	}
}

}

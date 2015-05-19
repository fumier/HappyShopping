package cn.sict.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class WebUtils
{
	public static <T> T request2Bean(HttpServletRequest request,
			Class<T> beanClass)
	{

		try
		{
			// 1.创建要封装数据的bean
			T bean = beanClass.newInstance();
			// 2.把request中的数据整到bean中
             Enumeration e=request.getParameterNames();
             int i=0;
             while(e.hasMoreElements())
             {
            	 String name=(String)e.nextElement();//username password email birthday
            	 String value=request.getParameter(name);
            	 BeanUtils.setProperty(bean, name, value);
             }
             return bean;
		} catch (Exception e)
		{
           throw new RuntimeException(e);
		}
	}
	public static void copyBean(Object src,Object dest)
	{
		//将formbean拷贝到userbean中
		//注册日期转换器
		ConvertUtils.register(new Converter()
		{
			public Object convert(Class type,Object value)
			{
				if(value==null)
				{
					return null;
				}
				String str=(String)value;
				if(str.trim().equals(""))
				{
					return null;
				}
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
				try{
					return df.parse(str);
				}catch(Exception e)
				{
					throw new RuntimeException(e);
				}
			}
		}, Date.class);
		try
		{
			BeanUtils.copyProperties(dest, src);
		}catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
//产生全球唯一的ID
public static String generateID()
{
	return UUID.randomUUID().toString();
}
}
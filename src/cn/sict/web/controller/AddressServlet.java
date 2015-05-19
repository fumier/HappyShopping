package cn.sict.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Stack;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import sun.org.mozilla.javascript.internal.json.JsonParser;
import cn.sict.domain.Address;
import cn.sict.domain.Info;
import cn.sict.domain.User;
import cn.sict.service.InfoService;
import cn.sict.service.impl.InfoServiceImpl;
import cn.sict.utils.WebUtils;

/**
 * Servlet implementation class AddressServlet
 */
public class AddressServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddressServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		    User user = (User) request.getSession().getAttribute("user");
		    String userID = user.getId();
			String infoID = request.getParameter("infoid");
			infoID = WebUtils.generateID();
			String personName = request.getParameter("name");
			String province;
			String city;
			String area;
			if (request.getParameter("area").equals(""))
			{
				province = request.getParameter("province");
				city = request.getParameter("province");
				area = request.getParameter("city");
			} else
			{
				province = request.getParameter("province");
				city = request.getParameter("city");
				area = request.getParameter("area");
			}
			String details = request.getParameter("details");
			String zipcode = request.getParameter("zipcode");
			String telephone = request.getParameter("telephone");
			String defaultAddress = request.getParameter("defaultAddress");
			Address address = new Address();
			address.setCountry("中国");
			address.setProvince(province);
			address.setCity(city);
			address.setArea(area);
			address.setDetails(details);
			address.setZipcode(zipcode);
			Info info = new Info();
			info.setInfoID(infoID);
			info.setPersonName(personName);
			info.setAddress(address);
			info.setTelephone(telephone);
			info.setDefaultAddress(defaultAddress.equals("1") ? true : false);
			try
			{
			InfoService service = new InfoServiceImpl(userID);
			service.addInfoToXML(info);
			@SuppressWarnings("unchecked")
			List<Info> infoList = (List<Info>) request.getSession()
					.getAttribute("infoList");
			int i = 0;
			for (i = infoList.size(); i >= 1; i--)
			{
				infoList.add(i, infoList.get(i - 1));
			}
			infoList.add(i, info);
			request.getSession().removeAttribute("infoList");
			request.getSession().removeAttribute("infoLength");
			request.getSession().setAttribute("infoList", infoList);
			request.getSession()
					.setAttribute("infoLength", infoList.size()+1);

			// 以xml的格式将新生成的infoid传递到前端页面
			Document document = DocumentHelper.createDocument();
			Element infoID_Element = document.addElement("infoid");
			infoID_Element.setText(infoID);
			response.setContentType("text/xml;charset=utf-8");
			response.setHeader("pragma", "no-cache");
			response.setHeader("cache-control", "no-cache");
			PrintWriter out = response.getWriter();
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("utf-8");

			XMLWriter xmlWriter = new XMLWriter(out, format);
			xmlWriter.write(document);
			out.flush();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

package cn.sict.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import cn.sict.domain.Address;
import cn.sict.domain.Info;
import cn.sict.domain.User;
import cn.sict.service.InfoService;
import cn.sict.service.impl.InfoServiceImpl;

/**
 * Servlet implementation class ShowAllAddressServlet
 */
public class ShowAllAddressServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowAllAddressServlet()
	{
		super();
		// TODO Auto-generated constructor stub
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
		List<Info> infoList = new ArrayList<Info>();
		try
		{
			InfoService infoService = new InfoServiceImpl(userID);
			infoList = infoService.findInfoByUserID();
			Document document = DocumentHelper.createDocument();
			Element rootElement = document.addElement("result");
            for(Info info:infoList)
            {
            	Element infoitem_tag=rootElement.addElement("infoitem");
            	infoitem_tag.addAttribute("infoid", info.getInfoID());
            	infoitem_tag.addAttribute("personname", info.getPersonName());
            	infoitem_tag.addAttribute("country", info.getAddress().getCountry());
            	infoitem_tag.addAttribute("city", info.getAddress().getCity());
            	infoitem_tag.addAttribute("area", info.getAddress().getArea());
            	infoitem_tag.addAttribute("details", info.getAddress().getDetails());
            	infoitem_tag.addAttribute("zipcode", info.getAddress().getZipcode());
            	infoitem_tag.addAttribute("telephone", info.getTelephone());
            }
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

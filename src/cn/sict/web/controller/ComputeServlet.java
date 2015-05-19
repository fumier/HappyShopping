package cn.sict.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * Servlet implementation class ComputeServlet
 */
public class ComputeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComputeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String buynum=request.getParameter("buynum");
		String price=request.getParameter("price");
		
		String[] buyNumString=buynum.split(",");
		String[] priceString=price.split(",");
		int buyBookNum=0;
		double totalPrice=0.00;
		for(int i=0;i<buyNumString.length;i++)
		{
			buyBookNum+=Integer.parseInt(buyNumString[i]);
			totalPrice+=(Double.parseDouble(priceString[i]))*(Integer.parseInt(buyNumString[i]));
		}
		
		Document document=DocumentHelper.createDocument();
		Element rootElement=document.addElement("result");
		Element buyNumElement=rootElement.addElement("buyNum");
		Element totalPriceElement=rootElement.addElement("totalPrice");
		buyNumElement.setText(buyBookNum+"");
        totalPriceElement.setText(totalPrice+"");
        response.setContentType("text/xml;charset=utf-8");
		response.setHeader("pragma","no-cache");
		response.setHeader("cache-control", "no-cache");
		PrintWriter out=response.getWriter();
		OutputFormat format=OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		
		XMLWriter xmlWriter=new XMLWriter(out,format);
		xmlWriter.write(document);
		out.flush();
	}

}

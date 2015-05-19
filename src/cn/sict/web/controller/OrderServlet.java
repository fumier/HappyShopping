package cn.sict.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sict.domain.CartItem;
import cn.sict.domain.Info;
import cn.sict.domain.User;
import cn.sict.service.CartService;
import cn.sict.service.InfoService;
import cn.sict.service.impl.CartServiceImpl;
import cn.sict.service.impl.InfoServiceImpl;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		String bookID_Choose = request.getParameter("bookid");
		String tmprice = request.getParameter("totalprice");
		double totalPrice = Double.parseDouble(tmprice);
		User user = (User) request.getSession().getAttribute("user");
		String userID = user.getId();
		String[] bookIDs = bookID_Choose.split(",");
		List<CartItem> list = new ArrayList<CartItem>();
		List<Info> infoList=new ArrayList<Info>();
		CartService service;
		CartItem cartItem;
		try
		{
			for (String bookID : bookIDs)
			{
				service = new CartServiceImpl(userID);
				cartItem = service.findCartItem1(bookID);
				list.add(cartItem);
			}
			InfoService infoService = new InfoServiceImpl(userID);
			infoList=infoService.findInfoByUserID();
			int infoLength=infoList.size();
			request.getSession().setAttribute("infoLength",infoLength);
			request.getSession().setAttribute("infoList", infoList);
			request.setAttribute("ChoosedCartItems", list);
			request.setAttribute("totalPrices", totalPrice);
			request.setCharacterEncoding("utf-8");
			request.getRequestDispatcher("order.jsp")
					.forward(request, response);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package cn.sict.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sict.domain.Cart;
import cn.sict.domain.User;
import cn.sict.service.CartService;
import cn.sict.service.impl.CartServiceImpl;

/**
 * Servlet implementation class AddToCartServlet
 */
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//点击购物车时，显示所有的购物车的信息
		//根据userid查找对应的购物车的信息
		User user=(User)request.getSession().getAttribute("user");
		if(user==null)
		{
			request.getRequestDispatcher("/LoginUIServlet").forward(request, response);
			return;
		}
		String userID=user.getId();
		try
		{
			CartService cartService=new CartServiceImpl(userID);
			Cart cart=cartService.findCartByUserID();
			request.getSession().setAttribute("cart", cart);
			response.sendRedirect(request.getContextPath()+"/listcart.jsp");
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}

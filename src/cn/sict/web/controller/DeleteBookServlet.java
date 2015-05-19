package cn.sict.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sict.domain.User;
import cn.sict.service.CartService;
import cn.sict.service.impl.CartServiceImpl;

/**
 * Servlet implementation class DeleteBookServlet
 */
public class DeleteBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bookID=request.getParameter("bookid");
		String[] bookIDArray=bookID.split(",");
		User user=(User)request.getSession().getAttribute("user");
		String userID=user.getId();
		try
		{
			CartService cartService=new CartServiceImpl(userID);	
			if(bookIDArray.length!=0)
			{
				for(int i=0;i<bookIDArray.length;i++)
				{
					cartService.deleteCartItemByID(bookIDArray[i]);
				}
			}
			response.sendRedirect(request.getContextPath()+"/CartServlet");
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
		// TODO Auto-generated method stub
	}

}

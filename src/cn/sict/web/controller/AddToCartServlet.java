package cn.sict.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import cn.sict.domain.Book;
import cn.sict.domain.Cart;
import cn.sict.domain.User;
import cn.sict.service.BookService;
import cn.sict.service.CartService;
import cn.sict.service.impl.BookServiceImpl;
import cn.sict.service.impl.CartServiceImpl;

/**
 * Servlet implementation class AddToCartServlet
 */
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bookID=request.getParameter("bookid");
		String buyNum=request.getParameter("buynum");
		BookService service=new BookServiceImpl();
		try
		{
			Book book=(Book)service.findBookByID(bookID);
			int buyBookNum=Integer.parseInt(buyNum);
			User user=(User)request.getSession().getAttribute("user");
			String userID=user.getId();
			CartService cartService=new CartServiceImpl(userID);
			Boolean addFlag=cartService.addToCart(book, buyBookNum);
			if(addFlag)
			{
				response.sendRedirect(request.getContextPath()+"/CartServlet");
			}
			else
			{
				System.out.println("ÃÌº” ß∞‹");
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}

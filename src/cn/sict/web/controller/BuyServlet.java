package cn.sict.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sict.domain.Book;
import cn.sict.service.BookService;
import cn.sict.service.impl.BookServiceImpl;

/**
 * Servlet implementation class BuyServlet
 */
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookID=request.getParameter("id");
		BookService service=new BookServiceImpl();
		String message="没有查到相关的书籍信息";
		try
		{
			Book book=service.findBookByID(bookID);
			if(book!=null)
			{
			request.setAttribute("book", book);
			request.getRequestDispatcher("buy.jsp").forward(request, response);
			}
			else
			{
			request.setAttribute("message", message);
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			}
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

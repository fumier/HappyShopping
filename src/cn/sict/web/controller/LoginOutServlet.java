package cn.sict.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginOutServlet
 */
public class LoginOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session=request.getSession(false);//若存在会话则返回该会话，否则返回NULL
      if(session!=null)
      {
    	  session.removeAttribute("user");
      }
      //注销成功，跳转到全局消息页面;并过3秒后跳转到首页
      request.setAttribute("message", "注销成功 ,3秒后返回首页...<meta http-equiv='refresh' content='3;url="+request.getContextPath()+"/happyshopping.jsp'>");
      request.getRequestDispatcher("/message.jsp").forward(request, response);
     
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

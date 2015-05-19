package cn.sict.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sict.domain.User;
import cn.sict.service.UserService;
import cn.sict.service.impl.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String username=request.getParameter("username");
    String password=request.getParameter("password");
    UserService service=new UserServiceImpl();
    User user=service.login(username, password);
    if(user!=null)
    {
    	request.getSession().setAttribute("user", user);
    	//让用户登陆成功后，跳转页面
    	response.sendRedirect(request.getContextPath()+"/happyshopping.jsp");
    	return;
    }
    Boolean userExist=service.UserExist(username);
    //判断用户名是否存在，存在，输入的密码错误，不存在，提示该用户不存在
    if(userExist)
    {
    	request.setAttribute("message2", "密码错误");
    }
    else
    {
    	request.setAttribute("message1", "该用户不存在");
    }
    request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	}

}

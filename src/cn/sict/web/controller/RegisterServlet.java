package cn.sict.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sict.domain.User;
import cn.sict.exception.UserExistException;
import cn.sict.service.UserService;
import cn.sict.service.impl.UserServiceImpl;
import cn.sict.utils.WebUtils;
import cn.sict.web.formbean.RegisterForm;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// 1.对表单字段进行合法性校验(把表单数据封装到formbean中)
		request.setCharacterEncoding("utf-8");
		RegisterForm form = WebUtils.request2Bean(request, RegisterForm.class);
		boolean b = form.validate();
		// 2.如果校验失败，跳回到表单页面，回显校验失败信息
		if (!b)
		{
			request.setAttribute("form", form);
			request.getRequestDispatcher("WEB-INF/jsp/register.jsp").forward(
					request, response);
			return;
		}
		// 3.如果校验成功，则调用service处理请求
		User user = new User();
		WebUtils.copyBean(form, user);
		user.setId(WebUtils.generateID());
		UserService service = new UserServiceImpl();
		try
		{
			service.register(user);
			// 6.如果service处理成功的话，跳转到网站的全局消息显示页面，为用户显示注册成功消息
			request.setAttribute("message", "恭喜您注册成功,3秒后返回首页...<meta http-equiv='refresh' content='3;url="+request.getContextPath()+"/LoginUIServlet'>");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch (UserExistException e)
		{
			// 4.如果serivce处理不成功，并且不成功的原因，是因为注册用户已存在的话，则跳回到注册页面，显示注册用户已存在
			form.getErrors().put("username", "注册用户已存在");
			request.setAttribute("form",form);
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(
					request, response);
			return;
		}catch(Exception e)
		{
			// 5.如果serivce处理不成功，并且不成功的原因，是其他的问题的话，跳转到网站的全局消息显示页面，为用户显示友好错误消息	
			e.printStackTrace();
			request.setAttribute("message", "服务器出现未知错误");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		
		

	}

}

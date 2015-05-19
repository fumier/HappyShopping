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
		// 1.�Ա��ֶν��кϷ���У��(�ѱ����ݷ�װ��formbean��)
		request.setCharacterEncoding("utf-8");
		RegisterForm form = WebUtils.request2Bean(request, RegisterForm.class);
		boolean b = form.validate();
		// 2.���У��ʧ�ܣ����ص���ҳ�棬����У��ʧ����Ϣ
		if (!b)
		{
			request.setAttribute("form", form);
			request.getRequestDispatcher("WEB-INF/jsp/register.jsp").forward(
					request, response);
			return;
		}
		// 3.���У��ɹ��������service��������
		User user = new User();
		WebUtils.copyBean(form, user);
		user.setId(WebUtils.generateID());
		UserService service = new UserServiceImpl();
		try
		{
			service.register(user);
			// 6.���service����ɹ��Ļ�����ת����վ��ȫ����Ϣ��ʾҳ�棬Ϊ�û���ʾע��ɹ���Ϣ
			request.setAttribute("message", "��ϲ��ע��ɹ�,3��󷵻���ҳ...<meta http-equiv='refresh' content='3;url="+request.getContextPath()+"/LoginUIServlet'>");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
		} catch (UserExistException e)
		{
			// 4.���serivce�����ɹ������Ҳ��ɹ���ԭ������Ϊע���û��Ѵ��ڵĻ��������ص�ע��ҳ�棬��ʾע���û��Ѵ���
			form.getErrors().put("username", "ע���û��Ѵ���");
			request.setAttribute("form",form);
			request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(
					request, response);
			return;
		}catch(Exception e)
		{
			// 5.���serivce�����ɹ������Ҳ��ɹ���ԭ��������������Ļ�����ת����վ��ȫ����Ϣ��ʾҳ�棬Ϊ�û���ʾ�Ѻô�����Ϣ	
			e.printStackTrace();
			request.setAttribute("message", "����������δ֪����");
			request.getRequestDispatcher("/message.jsp").forward(request, response);
			return;
		}
		
		
		

	}

}

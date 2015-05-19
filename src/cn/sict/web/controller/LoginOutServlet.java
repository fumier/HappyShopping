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
      HttpSession session=request.getSession(false);//�����ڻỰ�򷵻ظûỰ�����򷵻�NULL
      if(session!=null)
      {
    	  session.removeAttribute("user");
      }
      //ע���ɹ�����ת��ȫ����Ϣҳ��;����3�����ת����ҳ
      request.setAttribute("message", "ע���ɹ� ,3��󷵻���ҳ...<meta http-equiv='refresh' content='3;url="+request.getContextPath()+"/happyshopping.jsp'>");
      request.getRequestDispatcher("/message.jsp").forward(request, response);
     
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

package cn.sict.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import cn.sict.domain.Book;
import cn.sict.service.BookService;
import cn.sict.service.impl.BookServiceImpl;
import cn.sict.utils.PageSplitUtils;
import cn.sict.web.formbean.PageBean;

/**
 * Servlet implementation class PageServlet
 */
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      BookService service=new BookServiceImpl();
     
      String message="û����ز�Ʒ����Ϣ";
      try
	{
    	int pageSize=PageSplitUtils.getPageSize();
    	int rowPerPage=PageSplitUtils.getRowPerPage();
		int totalRecords=service.totalBookNum();
		String temp=request.getParameter("curPageNum");
		int curPageNum=0;
		int totalPages=PageSplitUtils.getTotalPages(totalRecords, pageSize);
		int totalBookNum=service.totalBookNum();
		if(null==temp||"".equals(temp))
		{
			curPageNum=1;
		}
		else 
		{
			curPageNum=Integer.parseInt(temp); 
			if(curPageNum>totalPages)
			{
			curPageNum=totalPages;
			}
		}
		int startIndex=PageSplitUtils.getPosition(curPageNum, totalPages, pageSize)-1;//ÿһҳ��ʼҳ����Ʒ��list�б��е�λ��
		int nextPage=PageSplitUtils.getNextCurPageNum(curPageNum, totalPages);
		int lastPage=PageSplitUtils.getPreviousCurPageNum(curPageNum);
		List<Book> list=service.pageData(startIndex, pageSize);//startIndex��0��ʼ��pageSize�Ĵ�СΪ5
		HttpSession session=request.getSession();
		if(list!=null)
		{
		
		session.setAttribute("nextPage", nextPage);
		session.setAttribute("lastPage", lastPage);
		session.setAttribute("curPageNum", curPageNum);
		session.setAttribute("totalPages", totalPages);
		session.setAttribute("rowPerPage", rowPerPage);
		session.setAttribute("pageData", list);
		response.sendRedirect(request.getContextPath() + "/index.jsp");
		return;
		}
		else
		{

			if(startIndex>totalBookNum)
			{
				message="�ף��Ѿ������һҳ�ˣ�û�и�����鼮�ˣ��뷵��~";
			}
			session.setAttribute("message", message);
			response.sendRedirect(request.getContextPath()+"/message.jsp");
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
		doGet(request,response);
	}

}

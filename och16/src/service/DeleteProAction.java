package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;

public class DeleteProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			int num=Integer.parseInt(request.getParameter("num"));
			String pageNum=request.getParameter("pageNum");
			String passwd=request.getParameter("passwd");
			BoardDao bd=BoardDao.getInstance();
			
			int result=bd.delete(num, passwd);
			request.setAttribute("result", result);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("num", num);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "deletePro.jsp";
	}
	
}

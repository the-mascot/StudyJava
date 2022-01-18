package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.BoardDao;

public class ContentAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BoardDao bd=BoardDao.getInstance();
		
		try {
			int num=Integer.parseInt(request.getParameter("num"));
			int pageNum=Integer.parseInt(request.getParameter("pageNum"));
			bd.readCount(num);
			Board board=bd.select(num);
			
			request.setAttribute("num", num);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("board", board);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return "content.jsp";
	}
}

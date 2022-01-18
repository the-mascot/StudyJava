package service;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.BoardDao;

public class UpdateFormAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			BoardDao bd=BoardDao.getInstance();
			int num=Integer.parseInt(request.getParameter("num"));
			int pageNum=Integer.parseInt(request.getParameter("pageNum"));
			Board board=bd.select(num);
			System.out.println(board.getNum());
			System.out.println(board.getWriter());
			
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("board", board);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return "updateForm.jsp";
	}
}

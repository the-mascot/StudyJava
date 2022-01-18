package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDao;
import vo.Board;

public class BoardListService implements CommandProcess {
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {

		BoardDao boardDao = BoardDao.getInstance();
		int totCnt = boardDao.getTotCnt();
		List<Board> boardList = boardDao.list();
		
		request.setAttribute("totCnt", totCnt);
		request.setAttribute("boardList", boardList);
		
		return "index.jsp";
	}
	
}

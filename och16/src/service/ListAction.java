package service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Board;
import dao.BoardDao;

public class ListAction implements CommandProcess {
	// 사용자 요청에 대한 Process 기술 --> Service
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BoardDao bd=BoardDao.getInstance();
		
		try {
			// Board의 총 갯수
			int totCnt=bd.getTotalCnt();
			String pageNum=request.getParameter("pageNum");
			if(pageNum==null||pageNum.equals("")) {
				pageNum="1";
			}
			int currentPage=Integer.parseInt(pageNum);
			int pageSize=10, blockSize=10;
			int startRow=(currentPage-1)*pageSize+1;
			int endRow=startRow+pageSize-1;
			int startNum=totCnt-startRow+1;
			List<Board> list=bd.list(startRow, endRow);
			int pageCnt=(int) Math.ceil((double)totCnt/pageSize);
			int StartPage=(int)(currentPage-1)/blockSize*blockSize+1;
			int endPage=StartPage+blockSize-1;
			System.out.println(pageNum);
			System.out.println(totCnt);
			if(endPage>pageCnt)
				endPage=pageCnt;
			request.setAttribute("totCnt", totCnt);
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startNum", startNum);
			request.setAttribute("list", list);
			request.setAttribute("blockSize", blockSize);
			request.setAttribute("pageCnt", pageCnt);
			request.setAttribute("startPage", StartPage);
			request.setAttribute("endPage", endPage);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// 사용자에게 보여줄 View
		return "list.jsp";
	}

}

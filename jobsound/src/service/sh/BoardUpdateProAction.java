package service.sh;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.sh.BoardDao;
import service.CommandProcess;

public class BoardUpdateProAction implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		// TODO Auto-generated method stub
		BoardDao bd = BoardDao.getInstance();
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("sessionID");
		String b_num = request.getParameter("b_num");
		
		System.out.println("받아온 id값"+id);
		System.out.println("받아온 b_num값"+b_num);
		
		boolean chk = bd.chkWriter(b_num, id);
		System.out.println("수정 완료후 실행된 chk값"+chk);
		
		if (id==null || id=="") {
			//로그인 안 된 상태
			return "LoginForm.jsp";
			
		} else if (chk==false){
			//아이디가 다름
			request.setAttribute("chk", chk);
			return "idCheckForm.jsp";
		} else {
			//정상 실행
			String content = request.getParameter("content");
			String title = request.getParameter("title");
			System.out.println("타이틀 출력"+title);
			int result = bd.boardUpdate(b_num,content,title);
			
			request.setAttribute("result", result);
			return "updatePro.jsp";
		}
	}

}



import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Greet
 */
@WebServlet("/Greet")
public class Greet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter log;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Greet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
															// true하면 append 해줌
		log=new PrintWriter(new FileWriter("c:/log/log.txt", true));
		} catch(Exception e) {
			System.out.println("c:/log/log.txt-->"+e.getMessage());
		}
	}
	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		if(log!=null) {
			log.flush();
			log.close();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String msg=name+"님 반가워\r\n";
		// File 저장
		GregorianCalendar gc=new GregorianCalendar();
		String date=String.format("%TF %TT\r\n", gc, gc);
		log.print(date+msg);
		// 화면
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<html><body><h2>인사</h2>"+msg);
		out.println("</body></html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 서블릿은 HttpServlet을 상속
public class HelloServlet extends HttpServlet {
	
	// Get방식의 요청을 처리 -> doGet()
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//  파라미터 확인
		String name = req.getParameter("name");
		if (name == null) {
			name = "Anonymous";
		}
		//	응답 출력을 위한 Writer
		PrintWriter out = resp.getWriter();
		out.println("<h3>Hello Servlet</h3>");
		out.println("<p>Welcome, " + name + "</p>");
	}

}
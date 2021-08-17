package com.example.emaillist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actionName = req.getParameter("a");
//		GET 요청 처리
		if ("joinform".equals(actionName)) {
			//	a=joinform
			RequestDispatcher rd = 
				req.getRequestDispatcher("/WEB-INF/views/users/joinform.jsp");
			rd.forward(req, resp);
		} else if ("joinsuccess".equals(actionName)) {
			//	a=joinsuccess
			//	가입 성공 페이지로 
			RequestDispatcher rd =
				req.getRequestDispatcher("/WEB-INF/views/users/joinsuccess.jsp");
			rd.forward(req, resp);
		} else {
			resp.sendError(404);	//	Page Not Found
		}
	}


}
package com.example.emaillist.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.emaillist.dao.UserDao;
import com.example.emaillist.dao.UserDaoImpl;
import com.example.emaillist.vo.UserVo;

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
		} else if ("loginform".equals(actionName)) {
			//	a=loginform
			//	로그인 폼 페이지로 
			RequestDispatcher rd =
				req.getRequestDispatcher("/WEB-INF/views/users/loginform.jsp");
			rd.forward(req, resp);
		} else {
			resp.sendError(404);	//	Page Not Found
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actionName = req.getParameter("a");

		if("join".equals(actionName)) {
			//	가입 절차 수행
			UserVo vo = new UserVo();
			String name = req.getParameter("name");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String gender = req.getParameter("gender");

			vo.setName(name);
			vo.setPassword(password);
			vo.setEmail(email);
			vo.setGender(gender);

			System.out.println("UserVo:" + vo);

			UserDao dao = new UserDaoImpl();
			int insertedCount = dao.insert(vo);

			//	체크
			if (insertedCount == 1) {
				//	가입 성공
				//	-> 성공 페이지로 리다이렉트
				resp.sendRedirect(req.getContextPath() + "/users?a=joinsuccess");
			} else {
				//	가입 실패
				//	-> 가입 폼으로 리다이렉트
				resp.sendRedirect(req.getContextPath() + "/users?a=joinform");
			}
		} 
	}


}
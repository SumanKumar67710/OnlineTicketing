package com.incapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.incapp.model.DAO;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdvocateLogin")
public class VehicleLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email=request.getParameter("email");
			String password=request.getParameter("password");
			DAO d=new DAO();
			String name=d.checkAdvocateLogin(email, password);
			if(name==null) {
				HttpSession session=request.getSession();
				session.setAttribute("msg","Email-Id or Password is wrong!");
				response.sendRedirect("Vehicle.jsp");
			}
			else {
				HttpSession session=request.getSession();
				session.setAttribute("name",name);
				session.setAttribute("email",email);
				response.sendRedirect("VehicleHome.jsp");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}

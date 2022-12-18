package com.incapp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.incapp.model.DAO;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/VehicleRegister")
@MultipartConfig
public class VehicleRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email=request.getParameter("email");
			String name=request.getParameter("name");
			String depttime=request.getParameter("depttime");
			String route=request.getParameter("route");
			String totaltime=request.getParameter("totaltime");
			/* int experience=Integer.parseInt(request.getParameter("experience")); */
			String password=request.getParameter("password");
			Part p=request.getPart("photo");
			String category=request.getParameter("category");
			InputStream photo=null;
			if(p!=null) {
				photo=p.getInputStream();
			}
			HashMap advocate=new HashMap();
			advocate.put("email",email);
			advocate.put("name",name);
			advocate.put("depttime",depttime);
			advocate.put("route",route);
			advocate.put("totaltime",totaltime);
			advocate.put("photo",photo);
			advocate.put("password",password);
			/* advocate.put("experience",experience); */
			advocate.put("category",category);
			DAO d=new DAO();
			String result=d.registerAdvocate(advocate);
			if(result.equalsIgnoreCase("success")) {
				HttpSession session=request.getSession();
				session.setAttribute("name",name);
				session.setAttribute("email",email);
				response.sendRedirect("VehicleHome.jsp");
			}
			else {
				HttpSession session=request.getSession();
				session.setAttribute("msg","Email Id Already Exist!");
				response.sendRedirect("Vehicle.jsp");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}

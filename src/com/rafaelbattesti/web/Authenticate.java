package com.rafaelbattesti.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rafaelbattesti.service.Manager;

/**
 * Servlet implementation class Authenticate
 */
@WebServlet("/Authenticate")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authenticate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Creates a manager to authenticate admin
		Manager manager = new Manager();
		
		//Gets parameters from user
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//Authenticate
		boolean isAuth = manager.authenticate(username, password);
		
		//Print html
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		if (isAuth) {
			out.println("Hello " + username + "!");
		} else {
			out.println(manager.getDatabaseMessage() + "!");
		}
		
	}

}

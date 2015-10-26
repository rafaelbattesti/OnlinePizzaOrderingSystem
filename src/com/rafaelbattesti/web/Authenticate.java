package com.rafaelbattesti.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rafaelbattesti.service.Manager;
import com.rafaelbattesti.service.User;

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
		User user = new User(username, password);
		user.setAuth(manager.authenticate(username, password));
		user.setMessage(manager.getDatabaseMessage());
		
		//Get session
		HttpSession session = request.getSession();
		
		//Authenticate
		boolean isAuth = manager.authenticate(username, password);
		session.setAttribute("user", user);
		response.sendRedirect("/Assign2/Admin");
	}

}

package com.rafaelbattesti.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rafaelbattesti.service.Manager;
import com.rafaelbattesti.service.ManagerInterface;
import com.rafaelbattesti.service.User;
import com.rafaelbattesti.service.UserInterface;

/**
 * Servlet for user authentication.
 */
@WebServlet("/Authenticate")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authenticate() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Creates a manager to authenticate admin
		ManagerInterface manager = new Manager();
		
		//Gets parameters from user
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserInterface user = new User(username, password);
		user = manager.authenticate(user);
		
		//Get session
		HttpSession session = request.getSession();
		
		//Sets user to session for validation at the /Admin servlet
		session.setAttribute("user", user);
		response.sendRedirect("/Assign2/Admin");
	}

}

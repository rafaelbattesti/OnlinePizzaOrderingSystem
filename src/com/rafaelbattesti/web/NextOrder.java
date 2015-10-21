package com.rafaelbattesti.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rafaelbattesti.service.Customer;

/**
 * Servlet implementation class Order
 */
@WebServlet("/Order")
public class NextOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NextOrder() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//get session from request
		HttpSession session = request.getSession();
		
		//Create customer object
		Customer customer;
		
		//session empty ? create : read
		if (session.getAttribute("customer") != null) {
			customer = (Customer) session.getAttribute("customer");
		} else {
			String firstName = request.getParameter("first_name");
			String lastName = request.getParameter("last_name");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			customer = new Customer(firstName, lastName, phone, address);
		}
		
		//Print html
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		//Send user to another page
		//response.sendRedirect("OnlinePizza.html");
		
	}

}

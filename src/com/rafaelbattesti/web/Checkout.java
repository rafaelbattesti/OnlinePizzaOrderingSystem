package com.rafaelbattesti.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rafaelbattesti.service.Customer;
import com.rafaelbattesti.service.Manager;
import com.rafaelbattesti.service.Pizza;
import com.rafaelbattesti.service.Receipt;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Checkout() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Get session
		HttpSession session = request.getSession();

		//Get parameters from session
		Customer customer = (Customer) session.getAttribute("customer");

		//Create manager to record database
		Manager manager = new Manager();

		//Creates a new receipt
		Receipt receipt = new Receipt();
		receipt.setDate();
		receipt.setCustomer(customer);
		
		try {
			receipt.setTime();
		} catch (ParseException e) {
			throw new RuntimeException();
		}

		//Send customer order to manager
		manager.addOrder(customer);

		//Print html
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		out.println("<html><head><meta charset=\"UTF-8\">");
		out.println("<title>Pizza Parlour</title>");
		out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
		out.println("</head><body><header>");
		out.println("<div id=\"logoContainer\">");
		out.println("<img alt=\"logo\" src=\"img/logo.jpg\"></div>");
		out.println("<div id=\"adminLogin\">");
		out.println("<a href=\"AdminLogin.html\">admin login</a></div>");
		out.println("<nav><ul id=\"navigation\">");
		out.println("<li class=\"active\">ORDER</li>");
		out.println("<li>MENU</li><li>LOCATIONS</li><li>TRACKER</li></ul></nav></header>");
		out.println("<div id=\"pageContent\">");
		out.println("<div id=\"loginInfo\">");

		out.println("<table id=\"receipt\">");
		out.println("<th class=\"orderHeader\">YOUR RECEIPT</th>");
		out.println("<tr><td class=\"orderList\" colspan=\"2\">" + customer.getLastName() + ", " + customer.getFirstName() + "</td></tr>");
		out.println("<tr><td class=\"orderList\" colspan=\"2\">" + customer.getPhone() + "</td></tr>");
		out.println("<tr><td class=\"orderList\" colspan=\"2\">" + customer.getAddress() + "</td></tr>");
		out.println("<tr><td class=\"orderList\">" + customer.getDeliveryMethod() + " time:</td>");
		out.println("<td class=\"orderList\">" + receipt.getTime() + "</td></tr>");
		out.println("<tr><td colspan=\"2\"><hr></td></tr>");

		for (Pizza pizza : customer.getPizzaList()) {
			out.println("<tr><td class=\"orderList\">" + pizza.toString() + "</td>" );
			out.println("<td> $" + pizza.calculateTotal() + "</td></tr>" );
		}

		out.println("<tr><td>TOTAL $</td><td>" + receipt.calculateTotal() + "</td></tr>");
		out.println("<tr><td colspan=\"2\"></td></tr>");
		out.println("<tr><td colspan=\"2\"><a href=\"OnlinePizza.html\"></td></tr>");
		out.println("</table></div></div>");
		out.println("<footer><address>&copy;Rafael Battesti - Sheridan College - 2015</address></footer>");
		out.println("</body></html>");	

	}

}

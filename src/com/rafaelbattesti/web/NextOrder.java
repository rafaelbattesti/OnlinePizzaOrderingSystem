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
import com.rafaelbattesti.service.CustomerInterface;
import com.rafaelbattesti.service.Pizza;
import com.rafaelbattesti.service.PizzaInterface;

/**
 * Servlet to control orders until checkout
 */
@WebServlet("/NextOrder")
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
		CustomerInterface customer;
		
		//session empty ? create : read
		if (session.getAttribute("customer") != null) {
			
			//Get customer from session
			customer = (CustomerInterface) session.getAttribute("customer");
			
			//Create new pizza
			String size = request.getParameter("size");
			String[] toppingList = request.getParameterValues("topping");
			int number = Integer.parseInt(request.getParameter("qty"));	
			Pizza pizza = new Pizza(size, toppingList, number);
			
			//Add pizza to customer
			for (int i = 0; i < number; i++) {
				customer.addPizza(pizza);
			}
			
		} else {
			
			//Create new Customer
			String firstName = request.getParameter("first_name");
			String lastName = request.getParameter("last_name");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String delivery = request.getParameter("delivery");
			customer = new Customer(firstName, lastName, phone, address, delivery);
			
			//Add customer to session
			session.setAttribute("customer", customer);
		}
		
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
		out.println("<form action=\"NextOrder\" method=\"POST\">");
		out.println("<fieldset><legend>BUILD YOUR PIZZA</legend>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td>Size:</td>");
		out.println("<td><input type=\"radio\" name=\"size\" value=\"small\">Small");
		out.println("<input type=\"radio\" name=\"size\" value=\"large\" checked>Large</td></tr>");
		out.println("<tr><td class=\"left\" colspan=\"2\">Choose your toppings:</td></tr>");
		out.println("<td class=\"left\" colspan=\"2\">");
		out.println("<input type=\"checkbox\" name=\"topping\" value=\"pepperoni\">Pepperoni<br>");
		out.println("<input type=\"checkbox\" name=\"topping\" value=\"bacon\">Bacon<br>");
		out.println("<input type=\"checkbox\" name=\"topping\" value=\"chicken\">Chicken<br>");
		out.println("<input type=\"checkbox\" name=\"topping\" value=\"mushrooms\">Mushrooms<br>");
		out.println("<input type=\"checkbox\" name=\"topping\" value=\"tomato\">Tomato<br>");
		out.println("<input type=\"checkbox\" name=\"topping\" value=\"onions\">Onions<br>");
		out.println("<input type=\"checkbox\" name=\"topping\" value=\"sausage\">Sausage<br>");
		out.println("<input type=\"checkbox\" name=\"topping\" value=\"beef\">Beef<br></td></tr>");
		out.println("<tr>");
		out.println("<tr><td>Number of Pizzas:</td><td><input type=\"text\" name=\"qty\" maxlenght=\"2\"></td></tr>");
		out.println("<tr><td colspan=\"2\"><input class=\"button\" type=\"submit\" value=\"send\"></td></tr>");
		out.println("</table></fieldset></form>");
		
		if (!customer.getPizzaList().isEmpty()) {

			//Create table for Orders
			out.println("<table class=\"orders\">");
			out.println("<th class=\"orderHeader\">YOUR ORDER</th>");
			for (PizzaInterface pizza : customer.getPizzaList()) {
				out.println("<tr><td class=\"orderList\">" + pizza.toString() + "</td></tr>" );
			}
			out.println("<tr><td><form action=\"Checkout\" method=\"POST\">");
			out.println("<input class=\"button\" type=\"submit\" value=\"checkout\"></form></td></tr>");
			out.println("</table>");
		}
		
		
		out.println("</div></div>");
		out.println("<footer><address>&copy;Rafael Battesti - Sheridan College - 2015</address></footer>");
		out.println("</body></html>");	
	}

}

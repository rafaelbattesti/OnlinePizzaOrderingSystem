package com.rafaelbattesti.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rafaelbattesti.service.Manager;
import com.rafaelbattesti.service.Pizza;
import com.rafaelbattesti.service.User;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get session
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		//Print html
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");	
		
		if (user.getAuth()) {
			
			//Create a manager
			Manager manager = new Manager();
			
			ArrayList<Pizza> pizzaList = manager.daySummary();
			
			//Print correct page
			out.println("<html><head><meta charset=\"UTF-8\">");
			out.println("<title>Admin Area</title>");
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
			out.println("</head><body><header>");
			out.println("<div id=\"logoContainer\">");
			out.println("<img alt=\"logo\" src=\"img/logo.jpg\"></div>");
			out.println("<div id=\"adminLogin\">");
			out.println("<a href=\"OnlinePizza.html\">order page</a>");
			out.println("</div>");
			out.println("<nav><ul id=\"navigation\"><li> WELCOME " + user.getUsername().toUpperCase() + "</li></ul></nav></header>");
			out.println("<div id=\"pageContent\"><div id=\"loginInfo\">");
			out.println("<table id=\"receipt\">");
			out.println("<th colspan=\"5\" class=\"orderHeader\">DAY BALANCE</th>");
			out.println("<tr><td class=\"orderHeader\">Size</td>" );
			out.println("<td class=\"orderHeader\">#Toppings</td>" );
			out.println("<td class=\"orderHeader\">Qty</td>" );
			out.println("<td class=\"orderHeader\">Delivery</td>" );
			out.println("<td class=\"orderHeader\">Price</td></tr>" );
			out.println("<tr><td colspan=\"5\"><hr></td></tr>" );
			
			for (Pizza pizza : pizzaList) {

				out.println("<tr><td class=\"center\">" + pizza.getSize() + "</td>" );
				out.println("<td class=\"center\">" + pizza.getNumToppings() + "</td>" );
				out.println("<td class=\"center\">" + pizza.getQty() + "</td>" );
				out.println("<td class=\"center\">" + pizza.getIsDelivery() + "</td>" );
				out.println("<td class=\"center\">" + pizza.getTotalPizza() + "</td></tr>" );
			}

			out.println("<tr><td colspan=\"5\"><hr></td></tr>" );
			out.println("<tr><td colspan=\"4\" class=\"orderHeader\">DAY TOTAL = $</td><td class=\"orderHeader\">" 
							+ manager.calculateTotal() + "</td></tr>");
			out.println("<tr><td colspan=\"5\"></td></tr>");
			out.println("<tr><td colspan=\"5\" class=\"center\"><a href=\"OnlinePizza.html\">back to order</a></td></tr>");
			out.println("</table></div></div>");
			out.println("<footer><address>&copy;Rafael Battesti - Sheridan College - 2015</address></footer>");
			out.println("</body></html>");	
			
		} else {
			//Prints login with error message
			out.println("<html><head><meta charset=\"UTF-8\">");
			out.println("<title>Admin Area</title>");
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">");
			out.println("</head><body><header>");
			out.println("<div id=\"logoContainer\">");
			out.println("<img alt=\"logo\" src=\"img/logo.jpg\"></div>");
			out.println("<div id=\"adminLogin\">");
			out.println("<a href=\"OnlinePizza.html\">order page</a>");
			out.println("</div>");
			out.println("<nav><ul id=\"navigation\"><li>ADMINISTRATIVE AREA</li></ul></nav></header>");
			out.println("<div id=\"pageContent\"><div id=\"loginInfo\">");
			out.println("<form action=\"Authenticate\" method=\"POST\"><fieldset><legend>PLEASE LOGIN</legend>");
			out.println("<table><tr><td>username:</td>");
			out.println("<td><input type=\"text\" name=\"username\"></td>");
			out.println("</tr><tr><td>password:</td><td><input type=\"password\" name=\"password\"></td></tr>");
			out.println("<tr><td colspan=\"2\"><input class=\"button\" type=\"submit\" value=\"sign in\"></td></tr>");
			out.println("<tr><td id=\"warning\" colspan=\"2\">" + user.getMessage() + "</td></tr>");
			out.println("</table></fieldset></form></div></div>");
			out.println("<footer><address>&copy;Rafael Battesti - Sheridan College - 2015</address></footer>");
			out.println("</body></html>");	
		}
	}
}

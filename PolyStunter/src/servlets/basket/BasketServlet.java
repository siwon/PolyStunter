package servlets.basket;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MarketDAO;

import beans.Basket;
import beans.Market;
import beans.User;
import beans.Product;

/**
 * Servlet implementation class BasketServlet
 * @author "Alexandre Bisiaux"
 *
 */
@WebServlet(
	    name = "BasketServlet", 
	    urlPatterns = {"/basket"}
	)
public class BasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResourceBundle rb = ResourceBundle.getBundle("properties.text");
		MarketDAO.getInstance().refresh(Market.getInstance());
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Basket basket = user.getBasket();
		StringBuffer buffer = new StringBuffer();
		
		for (Entry<Product,Integer> e : basket.getProducts().entrySet()) {
			buffer.append("<tr> <td><a href="+ request.getContextPath() + "/productSheet?id=" + e.getKey().getId() + ">");
			if(e.getKey().getName().length() > 20)
				buffer.append(e.getKey().getName().substring(0,20) + "...");
			else
				buffer.append(e.getKey().getName());
			buffer.append("</a></td>");
			buffer.append("<td><span>Ref. " + e.getKey().getReference() + "</span></td><td>");
			if(e.getKey().inStock())
				buffer.append("<span class='green'>" + rb.getString("inStock") + "</span>");
			else
				buffer.append("<span class='red'>" + rb.getString("outOfStock") + "</span>");
			buffer.append("</td><td><a href=" + request.getContextPath() + "/basketRemove?id=" + e.getKey().getId() + ">" + rb.getString("remove") + "</a></td>");
			buffer.append("<td>" + e.getKey().getPrice() + " &euro;</td>");
			buffer.append("<td>" + e.getValue() + "</td>");
			buffer.append("<td><div class='btn-group'><a href=" + request.getContextPath() + "/basketDecreaseQuantity?id=" + e.getKey().getId() + "><button class='btn btn-danger btn-mini'>-</button></a><a href=" + request.getContextPath() + "/basketIncreaseQuantity?id=" + e.getKey().getId() + "><button class='btn btn-success btn-mini'>+</button></a></div></td></tr>");
		
			
		}
		request.setAttribute("basket", buffer.toString());
		request.setAttribute("basketCost", basket.getCost());
		getServletContext().getRequestDispatcher("/WEB-INF/basket.jsp").forward(request, response);
	}
}

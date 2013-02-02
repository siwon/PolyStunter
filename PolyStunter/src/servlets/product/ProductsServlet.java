package servlets.product;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MarketDAO;

import beans.Market;
import beans.Product;

/**
 * Servlet implementation class ProductsServlet
 * @author "Alexandre Bisiaux"
 */
@WebServlet(
	    name = "ProductsServlet", 
	    urlPatterns = {"/products"}
	)
public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResourceBundle rb = ResourceBundle.getBundle("properties.text");
		HttpSession session = request.getSession();
		Market market = Market.getInstance();
		session.setAttribute("market", Market.getInstance());
		MarketDAO.getInstance().refresh(market);
		
		StringBuffer buffer = new StringBuffer();
		
		for (Product p : market.getProducts()) {
			buffer.append("<li class='span3'>");
			buffer.append("<img src='/PolyStunter/products/" + p.getPhoto() + "' width='100px' height='100px'/><br/>");
			if(p.getName().length() > 20)
				buffer.append(p.getName().substring(0,20) + "...");
			else
				buffer.append(p.getName());
			buffer.append("<br/><b class='red'>Prix : </b>" + p.getPrice() + "&euro;<br/>");
			if(p.inStock())
				buffer.append("<span class='green'>" + rb.getString("inStock") + "</span>");
			else
				buffer.append("<span class='red'>" + rb.getString("outOfStock") + "</span>");
			
			buffer.append("<br/><br/><a href=" + request.getContextPath() + "/productSheet?id=" + p.getId() + " class='btn btn-info'>" + rb.getString("see") + "</a></li>");
		}
		
		request.setAttribute("products", buffer.toString());
		
		getServletContext().getRequestDispatcher("/WEB-INF/products.jsp").forward(request, response);
	}

}

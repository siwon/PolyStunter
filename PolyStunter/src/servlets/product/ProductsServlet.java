package servlets.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MarketDAO;

import beans.Market;

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
		HttpSession session = request.getSession();
		Market market = Market.getInstance();
		session.setAttribute("market", Market.getInstance());
		MarketDAO.getInstance().refresh(market);
		
		request.setAttribute("products", market.getProducts());
		
		getServletContext().getRequestDispatcher("/WEB-INF/products.jsp").forward(request, response);
	}

}

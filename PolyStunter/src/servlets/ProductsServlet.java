package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Market;

/**
 * Servlet implementation class ProductsServlet
 * @author "Alexandre Bisiaux"
 */
@WebServlet("/ProductsServlet")
public class ProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Market market = Market.getInstance();
		market.refresh();
		HttpSession session = request.getSession();
		session.setAttribute("market", market);
		getServletContext().getRequestDispatcher("/WEB-INF/products.jsp").forward(request, response);
	}

}

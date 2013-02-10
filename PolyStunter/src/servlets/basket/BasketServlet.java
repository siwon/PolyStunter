package servlets.basket;

import java.io.IOException;
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
		MarketDAO.getInstance().refresh(Market.getInstance());
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Basket basket = user.getBasket();
		request.setAttribute("basket", basket.getProducts().entrySet());
		request.setAttribute("basketCost", basket.getCost());
		getServletContext().getRequestDispatcher("/WEB-INF/basket.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
	}
	
	
}

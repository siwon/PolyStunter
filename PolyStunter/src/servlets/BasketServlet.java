package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BasketDAO;
import dao.MarketDAO;

import beans.Basket;
import beans.Market;
import beans.User;

/**
 * Servlet implementation class BasketServlet
 * @author "Alexandre Bisiaux"
 *
 */
@WebServlet("/BasketServlet")
public class BasketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MarketDAO.getInstance().refresh(Market.getInstance());
		getServletContext().getRequestDispatcher("/WEB-INF/basket.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String button = req.getParameter("actionBasket");
		HttpSession session = req.getSession();
		User u = (User)session.getAttribute("userSession");
		
		if(button.equals("Vider"))
			BasketDAO.getInstance().empty(u.getBasket());
		
		getServletContext().getRequestDispatcher("/WEB-INF/basket.jsp").forward(req, resp);
	}
	

}

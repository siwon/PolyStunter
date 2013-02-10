package servlets.store;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MarketDAO;

import beans.Market;
import beans.User;

/**
 * Servlet implementation class StoreServlet
 * @author "Alexandre Bisiaux"
 */
@WebServlet(
	    name = "StoreServlet", 
	    urlPatterns = {"/store"}
	)
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Market market = Market.getInstance();
		MarketDAO.getInstance().refresh(market);
		User u = (User) session.getAttribute("user");
		
		request.setAttribute("products", market.getProductsOfSeller(u.getId()));
		
		getServletContext().getRequestDispatcher("/WEB-INF/store.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
	}
	
	
}

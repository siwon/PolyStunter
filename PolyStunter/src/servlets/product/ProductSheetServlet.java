package servlets.product;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BasketDAO;
import dao.MarketDAO;
import dao.UserDAO;

import beans.Market;
import beans.Product;
import beans.User;

/**
 * Servlet implementation class productSheetServlet
 * @author "Alexandre Bisiaux"
 */
@WebServlet(
	    name = "ProductSheetServlet", 
	    urlPatterns = {"/productSheet"}
	)
public class ProductSheetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("id");
		MarketDAO.getInstance().refresh(Market.getInstance());
		if(param.isEmpty() || Market.getInstance().getProduct(Integer.parseInt(param)) == null)
		{
			getServletContext().getRequestDispatcher("/WEB-INF/products.jsp").forward(request, response);
		} else {
			Product p = Market.getInstance().getProduct(Integer.parseInt(param));	
			request.setAttribute("product", p);
			getServletContext().getRequestDispatcher("/WEB-INF/productSheet.jsp").forward(request, response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("user");
		BasketDAO.getInstance().addProduct(u.getBasket(), id, quantity);
		resp.sendRedirect("/PolyStunter/basket");
	}


}

package servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BasketDAO;
import dao.MarketDAO;

import beans.Market;
import beans.User;

/**
 * Servlet implementation class productSheetServlet
 * @author "Alexandre Bisiaux"
 */
@WebServlet("/productSheetServlet")
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
			request.setAttribute("product", Market.getInstance().getProduct(Integer.parseInt(param)));
			getServletContext().getRequestDispatcher("/WEB-INF/productSheet.jsp").forward(request, response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		ServletContext c = getServletContext();
		c.log(id+"");
		HttpSession session = req.getSession();
		User u = (User) session.getAttribute("userSession");
		BasketDAO.getInstance().addProduct(u.getBasket(), id, quantity);
		
		resp.sendRedirect("/PolyStunter/basket");
	}


}

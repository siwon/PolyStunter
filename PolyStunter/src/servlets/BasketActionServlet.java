package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import dao.BasketDAO;

/**
 * Servlet implementation class BasketActionServlet
 * @author "Alexandre Bisiaux"
 */
@WebServlet("/BasketActionServlet")
public class BasketActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String action = request.getParameter("action");
		int id;
		switch(action) {
			case "empty":
				BasketDAO.getInstance().empty(user.getBasket());
				break;
			case "validate":
				break;
			case "remove":
				id = Integer.parseInt(request.getParameter("id"));
				BasketDAO.getInstance().removeProduct(user.getBasket(), id);
				break;
			case "decreaseQuantity":
				id = Integer.parseInt(request.getParameter("id"));
				BasketDAO.getInstance().decreaseQuantity(user.getBasket(), id);
				break;
			case "increaseQuantity":
				id = Integer.parseInt(request.getParameter("id"));
				BasketDAO.getInstance().increaseQuantity(user.getBasket(), id);
				break;
			default:
				break;
			
		}		
		getServletContext().getRequestDispatcher("/basket").forward(request, response);
	}


}

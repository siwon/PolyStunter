package servlets.basket;

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
 * Servlet implementation class BasketEmptyServlet
 */
@WebServlet(
	    name = "BasketEmptyServlet", 
	    urlPatterns = {"/basketEmpty"}
	)
public class BasketEmptyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		BasketDAO.getInstance().empty(user.getBasket());
		
		getServletContext().getRequestDispatcher("/basket").forward(request, response);
	}

}
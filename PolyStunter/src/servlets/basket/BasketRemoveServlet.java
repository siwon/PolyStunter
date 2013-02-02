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
 * Servlet implementation class BasketRemoveServlet
 */
@WebServlet(
	    name = "BasketRemoveServlet", 
	    urlPatterns = {"/basketRemove"}
	)
public class BasketRemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int id;
		if(request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
			BasketDAO.getInstance().removeProduct(user.getBasket(), id);
		}
		getServletContext().getRequestDispatcher("/basket").forward(request, response);
	}

}
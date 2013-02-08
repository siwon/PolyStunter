package servlets.basket;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BasketDAO;

import beans.User;

/**
 * Servlet implementation class BasketValidateServlet
*/

@WebServlet(
	    name = "BasketValidateServlet", 
	    urlPatterns = {"/basketValidate"}
	)

public class BasketValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user.getBasket().isEmpty())
			response.sendRedirect("/PolyStunter/basket");
		else {
			//BasketDAO.getInstance().valid(user.getBasket());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}

package servlets.basket;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Message;
import beans.User;

/**
 * Servlet implementation class BasketValidateServlet
*/

@WebServlet(
	    name = "BasketSummaryServlet", 
	    urlPatterns = {"/basketSummary"}
	)

public class BasketSummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user.getBasket().isEmpty())
			response.sendRedirect("/PolyStunter/basket");
		else {
			if(request.getParameter("street") != null && request.getParameter("zipCode") != null && request.getParameter("city") != null) {
				Message message = new Message();
				message.addSuccess("Adresse de livraison enregistrée.");
				request.setAttribute("basket", user.getBasket().getProducts().entrySet());
				request.setAttribute("basketCost", user.getBasket().getCost());
				request.setAttribute("street", request.getParameter("street"));
				request.setAttribute("zipCode", request.getParameter("zipCode"));
				request.setAttribute("city", request.getParameter("city"));
				request.setAttribute("message",message);
				getServletContext().getRequestDispatcher("/WEB-INF/orderSummary.jsp").forward(request, response);
			} else {
				Message message = new Message();
				message.addError("Adresse de livraison non valide.");
				request.setAttribute("message",message);
				getServletContext().getRequestDispatcher("/WEB-INF/basket.jsp").forward(request, response);
			}
		}
	}

}

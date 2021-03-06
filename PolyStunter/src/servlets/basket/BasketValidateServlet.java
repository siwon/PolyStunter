package servlets.basket;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Message;

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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Message message = new Message();
		User user = (User) request.getSession().getAttribute("user");
		if(user.getBasket().isEmpty())
			response.sendRedirect("/PolyStunter/basket");
		else {
			if(request.getParameter("street") != null && request.getParameter("zipCode") != null && request.getParameter("city") != null) {
				
				String forwardingAddress = request.getParameter("street") + "<br/>" + request.getParameter("zipCode") + request.getParameter("city");
				
				if(BasketDAO.getInstance().validate(user.getBasket(),forwardingAddress) == 1) {
					BasketDAO.getInstance().empty(user.getBasket());
					message.addSuccess("Votre commande a été validée avec succès.");
				} else {
					message.addError("Erreur de traitement de votre commande.");
				}
			} else {
				message.addError("Adresse de livraison non valide.");
			}
			request.setAttribute("message",message);
			getServletContext().getRequestDispatcher("/basket").forward(request, response);
		}
	}

}

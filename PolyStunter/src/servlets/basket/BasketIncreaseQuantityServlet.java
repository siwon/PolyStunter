package servlets.basket;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.Message;

import beans.User;

import dao.BasketDAO;

/**
 * Servlet implementation class BasketIncreaseQuantityServlet
 */

@WebServlet(
	    name = "BasketIncreaseQuantityServlet", 
	    urlPatterns = {"/basketIncreaseQuantity"}
	)
public class BasketIncreaseQuantityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		int id;
		boolean increase = false;
		if(request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
			increase = BasketDAO.getInstance().increaseQuantity(user.getBasket(), id);
		} 

		if(!increase){
			Message message = new Message();
			message.addError("La quantité maximale est atteinte.");
			request.setAttribute("message", message);
		}
		getServletContext().getRequestDispatcher("/basket").forward(request, response);
	}

}

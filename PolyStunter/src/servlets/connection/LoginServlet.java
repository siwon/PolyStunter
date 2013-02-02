package servlets.connection;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BasketDAO;

import forms.ConnectForm;

import beans.Basket;
import beans.User;



/**
 * Servlet implementation class ConnectionServlet
 * @author "Alexandre Bisiaux"
 */
@WebServlet(
	    name = "LoginServlet", 
	    urlPatterns = {"/login"}
	)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/connect.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectForm form = new ConnectForm();
		User user = form.connectUser(request);
		
		HttpSession session = request.getSession();
		
		request.setAttribute("message",form.message);
		
		if (form.message.getErrorMessages().isEmpty()) {
			
			session.setAttribute("user", user);
			
			if(user.isCustomer() || user.isAdmin()) { // Si c'est un client ou l'admin, chargement du panier
				Basket basket = new Basket(user.getId());
				BasketDAO.getInstance().loadBasket(basket);
				user.setBasket(basket);
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
			
		} else {
			session.setAttribute("user", null);
			this.getServletContext().getRequestDispatcher("/WEB-INF/connect.jsp").forward(request, response);
		}
	}

}

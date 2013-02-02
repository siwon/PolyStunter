package servlets.connection;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BasketDAO;

import beans.User;

/**
 * Servlet implementation class LogoutServlet
 * @author "Alexandre Bisiaux"
 */
@WebServlet(
	    name = "LogoutServlet", 
	    urlPatterns = {"/logout"}
	)
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("user");
		
		if(user.isCustomer())
			BasketDAO.getInstance().save(user.getBasket());
			
		request.getSession().invalidate();
		request.setAttribute("successMessage", "Déconnexion établie.");
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}
}

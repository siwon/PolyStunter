package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import forms.RegisterForm;

/**
 * Servlet implementation class RegisterServlet
 * @author "Alexandre Bisiaux"
 */
@WebServlet(
	    name = "RegisterServlet", 
	    urlPatterns = {"/register"}
	)
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegisterForm form = new RegisterForm();

		if(form.registerUser(request)) {
			request.setAttribute("message", form.message);
			this.getServletContext().getRequestDispatcher("/login").forward(request, response);
		} else {
			request.setAttribute("message", form.message);
			this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
		}
	}
}

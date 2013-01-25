package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StoreAction
 */
@WebServlet("/StoreAction")
public class StoreActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("update")) {
			getServletContext().getRequestDispatcher("/updateProduct").forward(request, response);
		}else if(action.equals("remove")) {
			
		} else {
			getServletContext().getRequestDispatcher("/store").forward(request, response);
		}
	}

}

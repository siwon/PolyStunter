package servlets.warehouse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;

import dao.WarehouseDAO;

/**
 * Servlet implementation class addWarehouseServlet
 */
@WebServlet(
	    name = "AddWarehouseServlet", 
	    urlPatterns = {"/addWarehouse"}
	)
public class AddWarehouseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("warehouseName");
		String street = request.getParameter("street");
		int zipCode = Integer.parseInt(request.getParameter("zipCode"));
		String city = request.getParameter("city");
		User user = (User) request.getSession().getAttribute("user");
		
		WarehouseDAO.getInstance().add(user.getId(), name, street, zipCode, city);
		response.sendRedirect("/PolyStunter/addProduct");
	}

}

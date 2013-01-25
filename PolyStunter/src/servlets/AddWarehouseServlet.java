package servlets;

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
@WebServlet("/addWarehouseServlet")
public class AddWarehouseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("warehouseName");
		String street = request.getParameter("street");
		int zipCode = Integer.parseInt(request.getParameter("zipCode"));
		String city = request.getParameter("city");
		User user = (User) request.getSession().getAttribute("user");
		
		WarehouseDAO.getInstance().addWarehouse(user.getId(), name, street, zipCode, city);
		getServletContext().getRequestDispatcher("/PolyStunter/addProduct").forward(request, response);
	}

}

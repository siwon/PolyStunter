package servlets.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.WarehouseDAO;

import beans.Market;
import beans.Product;
import beans.User;

/**
 * Servlet implementation class UpdateProductServlet
 */
@WebServlet(
	    name = "UpdateProductServlet", 
	    urlPatterns = {"/updateProduct"}
	)
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User user = (User) request.getSession().getAttribute("user");
		request.setAttribute("warehouses", WarehouseDAO.getInstance().getWarehousesOfSeller(user.getId()));
		Product p;
		if((p = Market.getInstance().getProduct(id)) != null)
		{
			request.setAttribute("product", p);
			getServletContext().getRequestDispatcher("/WEB-INF/updateProduct.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/WEB-INF/store.jsp").forward(request, response);
		}
	}
}

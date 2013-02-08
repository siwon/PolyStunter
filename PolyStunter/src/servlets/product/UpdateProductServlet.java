package servlets.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Message;

import dao.ProductDAO;
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
			request.setAttribute("warehouses", WarehouseDAO.getInstance().getWarehousesOfSeller(user.getId()));
			getServletContext().getRequestDispatcher("/WEB-INF/updateProduct.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/WEB-INF/store.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Message message = new Message();
		if(request.getParameter("id") != null && request.getParameter("name") != null 
				&& request.getParameter("reference") != null && request.getParameter("information") != null
				&& request.getParameter("price") != null && request.getParameter("quantity") != null) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String reference = request.getParameter("reference");
			String information = request.getParameter("information");
			double price = Double.parseDouble(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			int warehouse = Integer.parseInt(request.getParameter("warehouse"));
			
			if(ProductDAO.getInstance().updateProduct(id, name, reference, information, price, quantity, warehouse) == 0)
				message.addError("Erreur de mise à jour.");
		} else {
			message.addError("Veuillez remplir tous les champs.");
		}
		
		if(!message.isEmpty()) {
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/WEB-INF/updateProduct.jsp").forward(request, response);
		} else {
			response.sendRedirect("/PolyStunter/store");
		}	
		
	}
	
	
}

package servlets.warehouse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Message;

import beans.User;
import beans.Warehouse;
import dao.WarehouseDAO;

/**
 * Servlet implementation class UpdateWarehouseServlet
 */

@WebServlet(
	    name = "UpdateWarehouseServlet", 
	    urlPatterns = {"/updateWarehouse"}
	)
public class UpdateWarehouseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Message message = new Message();
		String forward = "/WEB-INF/warehouse.jsp";
		if(request.getParameter("id") != null){
			int id = Integer.valueOf(request.getParameter("id"));
			User user = (User) request.getSession().getAttribute("user");
			Warehouse w = WarehouseDAO.getInstance().getWarehouseById(id);
			if(w.getIdSeller() == user.getId()) {
				request.setAttribute("warehouse", w);
				forward = "/WEB-INF/updateWarehouse.jsp";
			} else {
				message.addError("Vous n'êtes pas autorisé à modifier ce dépôt.");
			}
				
		} else {
			message.addError("Ce dépôt n'existe pas.");
		}
		
		if(!message.isEmpty())
			request.setAttribute("message", message);
		
		getServletContext().getRequestDispatcher(forward).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Message message = new Message();
		if(request.getParameter("id") != null){
			int id = Integer.valueOf(request.getParameter("id"));
			User user = (User) request.getSession().getAttribute("user");
			Warehouse w = WarehouseDAO.getInstance().getWarehouseById(id);
			if(w.getIdSeller() == user.getId()) {
				if(request.getParameter("warehouseName") != null && request.getParameter("street") != null
					&& request.getParameter("zipCode") != null && request.getParameter("city") != null) {
					String name = request.getParameter("warehouseName");
					String street = request.getParameter("street");
					int zipCode = Integer.valueOf(request.getParameter("zipCode"));
					String city = request.getParameter("city");
					if(WarehouseDAO.getInstance().updateWarehouse(id, name, street, zipCode, city) == 0)
						message.addError("Erreur de mise à jour.");
						
				} else {
					message.addError("Vous n'êtes pas autorisé à modifier ce dépôt.");
				} 
			}
		} else {
			message.addError("Ce dépôt n'existe pas.");
		}
		
		if(!message.isEmpty()) {
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/WEB-INF/updateWarehouse.jsp").forward(request, response);
		} else {
			response.sendRedirect("/PolyStunter/warehouse");
		}	
	}
}

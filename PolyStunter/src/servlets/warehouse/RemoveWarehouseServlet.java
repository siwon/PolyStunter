package servlets.warehouse;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import beans.Warehouse;

import utils.Message;

import dao.WarehouseDAO;

/**
 * Servlet implementation class removeWarehouseServlet
 */
@WebServlet(
	    name = "RemoveWarehouseServlet", 
	    urlPatterns = {"/removeWarehouse"}
	)
public class RemoveWarehouseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Message message = new Message();
		User user = (User) request.getSession().getAttribute("user");
		if(request.getParameter("id") != null) {
			int id = Integer.valueOf(request.getParameter("id"));
			Warehouse w = WarehouseDAO.getInstance().getWarehouseById(id);
			if((w != null) && w.getIdSeller() == user.getId()) {
				if(!WarehouseDAO.getInstance().isUsed(id)) {
					if(WarehouseDAO.getInstance().removeWarehouse(id) == 0)
						message.addError("Erreur lors de la suppression.");
					else
						message.addSuccess("Dépôt correctement supprimé.");
				} else {
					message.addError("Des produits sont présents dans cet entrepôt.");
				}
			} else {
				message.addError("Ce dépôt ne vous appartient pas.");
			}
			request.setAttribute("message", message);
		}
		getServletContext().getRequestDispatcher("/warehouse").forward(request, response);
	}
}

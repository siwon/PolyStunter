package servlets.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Message;
import utils.UploadFileOnServer;
import beans.Market;
import beans.User;
import dao.ProductDAO;

/**
 * Servlet implementation class RemoveProductServlet
 */
@WebServlet(
	    name = "RemoveProductServlet", 
	    urlPatterns = {"/removeProduct"}
	)
public class RemoveProductServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		Message message = new Message();
		
		if(request.getParameter("id") != null) {
			int id = Integer.valueOf(request.getParameter("id"));
			if(Market.getInstance().getProduct(id).getIdSeller() == user.getId()) {
				UploadFileOnServer.removeFile(getServletContext().getRealPath("/") + "products/" + Market.getInstance().getProduct(id).getPhoto());
				ProductDAO.removeProduct(id);
				message.addSuccess("Produit correctement supprimé");
			} else {
				message.addError("Ce produit ne vous appartient pas");
			}
		}
		
		if(!message.isEmpty())
			request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/store").forward(request, response);
	}
}

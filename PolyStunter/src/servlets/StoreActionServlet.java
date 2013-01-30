package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.UploadFileOnServer;

import beans.Market;

import dao.ProductDAO;

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
			int id = Integer.valueOf(request.getParameter("id"));
			request.setAttribute("id", id);
			getServletContext().getRequestDispatcher("/updateProduct").forward(request, response);
		}else if(action.equals("remove")) {
			int id = Integer.valueOf(request.getParameter("id"));
			UploadFileOnServer.removeFile(getServletContext().getRealPath("/") + "products/" + Market.getInstance().getProduct(id).getPhoto());
			ProductDAO.remove(id);				
			getServletContext().getRequestDispatcher("/store").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/store").forward(request, response);
		}
	}

}

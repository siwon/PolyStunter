package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.logging.ErrorManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import utils.ErrorMessage;
import beans.Product;
import beans.User;

import dao.ProductDAO;
import dao.WarehouseDAO;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/addProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		request.setAttribute("warehouses", WarehouseDAO.getInstance().getWarehousesOfSeller(user.getId()));
		getServletContext().getRequestDispatcher("/WEB-INF/addProduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*ErrorMessage errors = new ErrorMessage();

		String name = request.getParameter("name");
		String information = request.getParameter("information");
		double price = Double.parseDouble(request.getParameter("price"));
		String reference = request.getParameter("reference");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		double latitude = Double.parseDouble(request.getParameter("latitude"));
		double longitude = Double.parseDouble(request.getParameter("longitude"));

		HttpSession session = request.getSession();
		User u = (User)session.getAttribute("userSession");
		
		int lastIdProduct = ProductDAO.getInstance().getLastId();
		if(lastIdProduct != -1) {
			UploadFileOnServer upload= new UploadFileOnServer();
			try {
				String filename = upload.uploadFile(lastIdProduct+1+"", request);
			} catch(Exception e) {
				errors.
			}
			if() {
				errors = "Erreur lors de l'envoie de la photo.";
			} else {
				ProductDAO.getInstance().addProduct(new Product(lastIdProduct+1,u.getId(),price,name,reference,quantity,information,new Location(latitude,longitude),lastIdProduct+1));
			}
			
		} else {
			errors = "Erreur interne";
		}
		
		if(errors != "") {
			request.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/addProduct.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/WEB-INF/store.jsp").forward(request, response);
		}*/
	}

	private String getFilename(Part part) {
		for (String cd : part.getHeader("content-disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
			}
		}
		return null;
	}
}

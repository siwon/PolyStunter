package servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import beans.Location;
import beans.Product;
import beans.User;

import sun.misc.IOUtils;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/addProduct.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String errors = "";

		Collection<Part> parts = request.getParts();

		if (parts.size() != 8) {
			errors = "Vous n'avez pas rempli tous les champs.";
		} else {
			Part filePart = request.getPart("photo");
			
			if(filePart.getSize() > 1048576){
				errors = "Photo trop volumineuse. Taille max de 1 Mo.";
			}
			
			InputStream imageInputStream = filePart.getInputStream();
			//read imageInputStream
			//Read Name, String Type 
			
			
			filePart.write("/products/");
			//can also write the photo to local storage

			
			//use nameInputStream if required        
			InputStream nameInputStream = namePart.getInputStream();
			//name , String type can also obtained using Request parameter 
			String nameParameter = request.getParameter("name");

			/*String name = request.getParameter("name");
				String information = request.getParameter("information");
				double price = Double.parseDouble(request.getParameter("price"));
				String reference = request.getParameter("reference");
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				double latitude = Double.parseDouble(request.getParameter("latitude"));
				double longitude = Double.parseDouble(request.getParameter("longitude"));


				HttpSession session = request.getSession();
				User u = (User)session.getAttribute("userSession");
				Product.addProduct(new Product(-1,u.getId(),price,name,reference,quantity,information,new Location(latitude,longitude),filename));
				getServletContext().getRequestDispatcher("/WEB-INF/store.jsp").forward(request, response);*/
		}
	} else {
		errors = "Erreur interne : Veuillez renouveler l'opération ultérieurement.";
	}

	if(errors != "") {
		request.setAttribute("errors", errors);
		getServletContext().getRequestDispatcher("/WEB-INF/addProduct.jsp").forward(request, response);
	} else {
		getServletContext().getRequestDispatcher("/WEB-INF/store.jsp").forward(request, response);
	}
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

package servlets;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import utils.Message;
import beans.Product;
import beans.User;

import dao.ProductDAO;
import dao.WarehouseDAO;
import exceptions.ExtensionException;

import utils.UploadFileOnServer;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/addProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		request.setAttribute("warehouses", WarehouseDAO.getInstance().getWarehousesOfSeller(user.getId()));
		getServletContext().getRequestDispatcher("/WEB-INF/addProduct.jsp").forward(request, response);
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Message errors = new Message();
		Message success = new Message();
		
		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		try {
			@SuppressWarnings("unchecked")
			List<FileItem> items = (List<FileItem>) uploadHandler.parseRequest(request);
			
			Iterator<FileItem> itr = (Iterator<FileItem>) items.iterator();
			
			HttpSession session = request.getSession();
			User u = (User)session.getAttribute("userSession");
						
			int lastIdProduct = ProductDAO.getInstance().getLastId();
			
			if(lastIdProduct != -1) {
				String name = "", information = "", reference = "", fileName = "", param = "", newName = "";
				int quantity = 0, warehouse = 0;
				double price = 0;
				
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					if(item.isFormField()) {
						param = item.getFieldName();
						switch(param) {
							case "name":
								name = item.getString();
								break;
							case "information":
								information = item.getString();
								break;
							case "reference":
								reference = item.getString();
								break;
							case "quantity":
								quantity = Integer.parseInt(item.getString());
								break;
							case "warehouse":
								warehouse = Integer.parseInt(item.getString());
								break;
							case "price":
								price = Double.parseDouble(item.getString());
								break;
							default:
								break;
						}						
					} else {
						newName = lastIdProduct+1 + "";
						UploadFileOnServer uploadFileOnServer = new UploadFileOnServer();
						fileName = uploadFileOnServer.uploadFile(item, getServletContext().getRealPath("/") + "products", newName);
					}
				}
				ProductDAO.getInstance().addProduct(new Product(lastIdProduct+1,u.getId(),price,name,reference,quantity,information,warehouse,fileName));
			} else {
				throw new Exception("Erreur interne.");
			}
		} catch(ExtensionException e) {
			errors.add(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			errors.add("Erreur d'encodage");
		} catch (Exception e) {
			errors.add("Erreur d'upload." + e.getMessage());
		}
		
		if(errors.isEmpty()) {
			success.add("Produit a été ajouté à votre boutique.");
			request.setAttribute("success", success);
			getServletContext().getRequestDispatcher("/WEB-INF/store.jsp").forward(request, response);	
		} else {
			request.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/addProduct.jsp").forward(request, response);
		}
	}
}



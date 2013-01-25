package servlets;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MarketDAO;

import beans.Market;
import beans.Product;
import beans.User;

/**
 * Servlet implementation class StoreServlet
 * @author "Alexandre Bisiaux"
 */
@WebServlet("/Store")
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ResourceBundle rb = ResourceBundle.getBundle("properties.text");
		HttpSession session = request.getSession();
		Market market = Market.getInstance();
		MarketDAO.getInstance().refresh(market);
		User u = (User) session.getAttribute("user");
		
		StringBuffer buffer = new StringBuffer();
		
		for (Product p : market.getProductsOfSeller(u.getId())) {
			buffer.append("<li class='span3'>");
			buffer.append("<img src='/PolyStunter/products/" + p.getPhoto() + "' width='100px' height='100px'/><br/>");
			if(p.getName().length() > 20)
				buffer.append(p.getName().substring(0,20) + "...");
			else
				buffer.append(p.getName());
			buffer.append("<br/><b class='red'>Prix : </b>" + p.getPrice() + "&euro;<br/>");
			if(p.inStock())
				buffer.append("<span class='green'>" + rb.getString("inStock") + "</span>");
			else
				buffer.append("<span class='red'>" + rb.getString("outOfStock") + "</span>");
			
			buffer.append("<br/><div class='btn-group'>");
			buffer.append("<a href=" + request.getContextPath() + "/storeAction?action=remove&id=" + p.getId() + " class='btn btn-danger btn-mini'>" + rb.getString("remove") + "</a>");
			buffer.append("<a href=" + request.getContextPath() + "/storeAction?action=update&id=" + p.getId() + " class='btn btn-warning btn-mini'>" + rb.getString("update") + "</a>");
			buffer.append("</div></li>");
		}
		
		request.setAttribute("products", buffer.toString());
		
		getServletContext().getRequestDispatcher("/WEB-INF/store.jsp").forward(request, response);
	}
}

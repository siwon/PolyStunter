package filters;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import utils.Message;

import beans.User;

/**
 * Filtre d'accès aux pages réservées aux livreurs
 * @author "Alexandre Bisiaux"
 */
@WebFilter(
		filterName = "/DeliveryManFilter",
		urlPatterns = {"/notification"}
		)
public class DeliveryManFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		ResourceBundle rb = ResourceBundle.getBundle("properties.text");
		
		HttpSession session = ((HttpServletRequest) request).getSession();
		User user = (User) session.getAttribute("user");
		if (user != null && (user.isDeliveryMan() || user.isAdmin())) {
			chain.doFilter(request, response);
		} else {
			Message message = new Message();
			message.addError(rb.getString("unauthorizedPage"));
			((HttpServletRequest) request).setAttribute("message", message);
			((HttpServletRequest) request).getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

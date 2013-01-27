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

import beans.User;

/**
 * Filtre d'accès aux pages réservées aux clients
 * @author "Alexandre Bisiaux"
 */
@WebFilter("/CustomerFilter")
public class CustomerFilter implements Filter {

   	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		ResourceBundle rb = ResourceBundle.getBundle("properties.text");
		HttpSession session = ((HttpServletRequest) request).getSession();
		User user = (User) session.getAttribute("user");
		if (user != null && (user.isCustomer() || user.isAdmin())) {
			chain.doFilter(request, response);
		} else {
			//request.setAttribute("errors", rb.getString("unauthorizedPage"));
			((HttpServletRequest) request).getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
}

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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.Message;
/**
 * 
 * @author "Alexandre Bisiaux"
 *
 */
@WebFilter(
		filterName = "/LoggedFilter",
		urlPatterns = {"/logout", "/profile"}
		)
public class LoggedFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		ResourceBundle rb = ResourceBundle.getBundle("properties.text");
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		HttpSession session = request.getSession();

		if (session.getAttribute("user") != null) {
			arg2.doFilter(arg0, arg1);
		} else {
			Message message = new Message();
			message.addError(rb.getString("unauthorizedPage"));
			((HttpServletRequest) request).setAttribute("message", message);
			request.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

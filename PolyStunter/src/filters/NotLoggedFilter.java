package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author "Alexandre Bisiaux"
 *
 */
public class NotLoggedFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;

		if(request.getSession().getAttribute("userSession")==null) {
			arg2.doFilter(arg0, arg1);
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}

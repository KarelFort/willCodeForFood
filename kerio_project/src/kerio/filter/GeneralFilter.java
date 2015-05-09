package kerio.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class GeneralFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD,
		DispatcherType.INCLUDE, DispatcherType.ERROR }, urlPatterns = { "/*" })
public class GeneralFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public GeneralFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		// changing type of request
		HttpServletRequest req = (HttpServletRequest) request;

		// changing coding to UTF-8
		req.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// if I have any meesage in session, I add it to the request and remove
		// from session
		// message will be displayed in JSP
		if (req.getSession().getAttribute("message") != null) {
			String message = req.getSession().getAttribute("message")
					.toString();
			String message_type = req.getSession().getAttribute("message_type")
					.toString();

			req.setAttribute("message", message);
			req.setAttribute("message_type", message_type);

			req.getSession().removeAttribute("message");
			req.getSession().removeAttribute("message_type");
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

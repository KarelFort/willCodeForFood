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
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, urlPatterns = { "/*" })
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		//pretypovani
				HttpServletRequest req = (HttpServletRequest) request;
				
		// pokud mam v sessne nejakou zpravu, pridam ji vcetne typu do requestu
				// a necham v jsp zobrazit
				if (req.getSession().getAttribute("zprava") != null) {
					String zprava = req.getSession().getAttribute("zprava").toString();
					String typZpravy = req.getSession().getAttribute("typZpravy").toString();

					req.setAttribute("zprava", zprava);
					req.setAttribute("typZpravy", typZpravy);
					
					req.getSession().removeAttribute("zprava");
					req.getSession().removeAttribute("typZpravy");
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

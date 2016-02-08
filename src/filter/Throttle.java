package filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Servlet Filter implementation class Throttle
 */
@WebFilter(urlPatterns = { "/Throttle", "/Start" }, servletNames = { "Start" })
public class Throttle implements Filter {

	private Date time;

	/**
	 * Default constructor.
	 */
	public Throttle() {
		// TODO Auto-generated constructor stub
		this.time = new Date();
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// request = new HttpServletRequestWrapper((HttpServletRequest)
		// request);

//		long time = ((HttpServletRequest) request).getSession().getLastAccessedTime();
//		Date then = new Date(time);
//		Date now = new Date();
//		long diff = now.getTime() - then.getTime();
//		System.out.println("Time difference between requests: " + diff);
//		if (diff <= 5000) {
//			request.getRequestDispatcher("/Throttle.jspx").forward(request, response);
//			// chain.doFilter(request, response);
//		} else {
//			chain.doFilter(request, response);
//		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

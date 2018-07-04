package edu.sua.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.sua.beans.UserAccount;
import edu.sua.utils.Utils;
import edu.sua.utils.UtilsDAO;

/**
 * In case, the user Logged and remembered information in previous access (for
 * example the day before). And now the user return, this Filter will check the
 * Cookie information stored by the browser and automatic Login.
 */

@WebFilter(filterName = "CookieFilter", urlPatterns = { "/*" })
public class CookieFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public CookieFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		UserAccount userInSession = Utils.getLoggedUser(session);
		if (userInSession != null) {
			session.setAttribute("COOKIE_CHECKED", "CHECKED");
			chain.doFilter(request, response);
			return;
		}

		// Connection was created in JDBCFilter.
		// Q: а откуда я знаю что JdbcFilter был вызван раньше CookieFilter !?
		// A: JDBCFilter & CookieFilter have the same  url-pattern =/*, 
		// you must be configured to ensure that JDBCFilter  is  executed first. 
		// Therefore, you need to declare the order in web.xml 
		// There is no way to declare the order by Annotation. 
		Connection conn = Utils.getStoredConnection(request);

		String checked = (String) session.getAttribute("COOKIE_CHECKED");
		if (checked == null && conn != null) { 
			String userName = Utils.getUserNameInCookie(req);
			try {
				// TODO no processing for not found User in DB (null returned)
				UserAccount user = UtilsDAO.findUser(conn, userName);
				Utils.storeLoggedUser(session, user);
			} catch (SQLException e) {
				e.printStackTrace();  
			}
			session.setAttribute("COOKIE_CHECKED", "CHECKED"); // Mark checked Cookies.
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}

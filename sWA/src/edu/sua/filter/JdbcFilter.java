package edu.sua.filter;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import edu.sua.conn.ConnectionUtils;
import edu.sua.utils.Utils;
import edu.sua.utils.UtilsFilter;

/**
 * JDBCFilter will check the request to ensure that it only opens JDBC
 * connection for the necessary request, eg for Servlet, avoid opening JDBC
 * connection to common requests like image, css, js, html
 */

@WebFilter(filterName = "JdbcFilter", urlPatterns = { "/*" })
public class JdbcFilter implements Filter {

	ServletContext sctx; // for logging

	/**
	 * Default constructor.
	 */
	public JdbcFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		sctx = null; // ?
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		if (UtilsFilter.needJdbc(req)) {

			Connection conn = null;
			try {
				conn = ConnectionUtils.getConnection(); // Create a Connection.
				sctx.log("JDBC Filter: Open Connection for: " + req.getServletPath());
				conn.setAutoCommit(false); // transaction enable
				Utils.storeConnection(request, conn); // Store conn.obj. in attribute of request.
				chain.doFilter(request, response);
				conn.commit(); // to complete the transaction with the DB.
			} catch (SQLException e) {
				if (e.getCause() instanceof ConnectException) {
					sctx.log("JDBC Filter: DB Server Connection refused. " + e.getCause());
					// TODO make redirect to JSP with err msg
				} else {
					sctx.log("JDBC Filter: SQL Exception, rollback transaction. " + e.getCause());
					ConnectionUtils.rollbackQuietly(conn);
				}
				return;
			} catch (Exception e) {
				e.printStackTrace();
				sctx.log("JDBC Filter: some other exception");
				throw new ServletException();
			} finally {
				ConnectionUtils.closeQuietly(conn);
			}
		} else {
			// With commons requests No need to open the connection.
			chain.doFilter(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		sctx = fConfig.getServletContext();
	}

}

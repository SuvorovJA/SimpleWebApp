package edu.sua.servlet;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TomcatPoolMonitorServlet
 */
@WebServlet("/poolMonitor")
public class TomcatPoolMonitorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TomcatPoolMonitorServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> poolAttributes = new HashMap<>();
		ServletContext sctx = request.getServletContext();
		try {
			MBeanServer server = ManagementFactory.getPlatformMBeanServer();
			Set<ObjectName> objectNames = server.queryNames(null, null);
			for (ObjectName name : objectNames) {
				MBeanInfo info = server.getMBeanInfo(name);
				if (info.getClassName().contains("jdbc")) {
					sctx.log("JDBC Pool: " + info.getClassName().toString());
				}
				if (info.getClassName().equals("org.apache.tomcat.jdbc.pool.jmx.ConnectionPool")) {
					for (MBeanAttributeInfo mf : info.getAttributes()) {
						Object attributeValue = server.getAttribute(name, mf.getName());
						if (attributeValue != null) {
							sctx.log("JDBC Pool: " + mf.getName() + ":" + attributeValue.toString());
							poolAttributes.put(mf.getName(), attributeValue.toString());
						}
					}
					break;
				}
			}
			if (poolAttributes != null) {
				request.setAttribute("poolAttributes", poolAttributes);
				sctx.log("JDBC Pool: count attributes = " + poolAttributes.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorString", e.getMessage());
		}

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/poolMonitorView.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

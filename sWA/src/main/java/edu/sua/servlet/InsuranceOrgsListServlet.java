package edu.sua.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.sua.entities.InsuranceOrg;
import edu.sua.utils.Utils;
import edu.sua.utils.UtilsDAO;

/**
 * Servlet implementation class InsuranceOrgsListServlet
 */
@WebServlet(urlPatterns = { "/insuranceOrgsList" })
public class InsuranceOrgsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsuranceOrgsListServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = Utils.getStoredConnection(request);

		String errorString = null;
		List<InsuranceOrg> list = null;
		try {
			list = UtilsDAO.queryInsuranceOrgs(conn, this.applyFilter(request));
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}
		// Store info in request attribute, before forward to views
		request.setAttribute("errorString", errorString);
		request.setAttribute("insuranceOrgsList", list);
		// replay search fields
		request.setAttribute("search_inn", request.getParameter("search_inn"));
		request.setAttribute("search_ogrn", request.getParameter("search_ogrn"));
		request.setAttribute("search_name", request.getParameter("search_name"));
		request.setAttribute("search_address", request.getParameter("search_address"));

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/insuranceOrgsListView.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private String applyFilter(HttpServletRequest request) {
		String result = "";
		List<String> names = Collections.list(request.getParameterNames());
		for (String n : names) {
			if (!request.getParameter(n).isEmpty()) {
				String ns = n.replace("search_", "");
				result += ns + " LIKE '%" + request.getParameter(n) + "%' AND ";
			}
		}
		if (result != "") {
			result = result.substring(0, result.length() - 4);
		}
		return result;
	}

}

package edu.sua.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.sua.entities.InsuranceOrg;
import edu.sua.entities.UserAccount;
import edu.sua.utils.Utils;
import edu.sua.utils.UtilsDAO;

/**
 * Servlet implementation class EditInsuranceOrgServlet
 */
@WebServlet(urlPatterns = { "/editInsuranceOrg" })
public class EditInsuranceOrgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditInsuranceOrgServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Check User has logged on
		HttpSession session = request.getSession();
		UserAccount LoggedUser = Utils.getLoggedUser(session);
		if (LoggedUser == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		Connection conn = Utils.getStoredConnection(request);
		long inn =  Long.valueOf((String) request.getParameter("inn"));
		InsuranceOrg insuranceOrg = null;
		String errorString = null;

		try {
			insuranceOrg = UtilsDAO.findInsuranceOrg(conn, inn);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// If The InsuranceOrg does not exist to edit. Redirect to insuranceOrgsList page.
		if (errorString != null && insuranceOrg == null) {
			response.sendRedirect(request.getServletPath() + "/insuranceOrgsList");
			return;
		}

		// Store errorString in request attribute, before forward to views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("insuranceOrg", insuranceOrg);

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editInsuranceOrgView.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = Utils.getStoredConnection(request);	
		String errorString = null;
		long inn = 0, ogrn = 0;

		// TODO move validating to ext
		try {
			inn = Long.valueOf((String) request.getParameter("inn"));
		} catch (NumberFormatException e) {
			errorString = "InsuranceOrg INN invalid! (must be number)";
		}
		try {
			ogrn = Long.valueOf((String) request.getParameter("ogrn"));
		} catch (NumberFormatException e) {
			errorString = "InsuranceOrg OGRN invalid! (must be number)";
		}
		String name = (String) request.getParameter("name");
		String address = (String) request.getParameter("address");
		
//		try {
//			price = Float.parseFloat(priceStr);
//		} catch (Exception e) {
//			errorString = "Product Price invalid! (must be number)";
//		}
		InsuranceOrg insuranceOrg = new InsuranceOrg(inn, ogrn, name, address);

		try {
			UtilsDAO.updateInsuranceOrg(conn, insuranceOrg);
		} catch (SQLException e) {
			e.printStackTrace();
			errorString = e.getMessage();
		}

		// Store infomation to request attribute, before forward to views.
		request.setAttribute("errorString", errorString);
		request.setAttribute("insuranceOrg", insuranceOrg);

		// If error, forward to Edit page.
		if (errorString != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/editInsuranceOrgView.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/insuranceOrgsList");
		}
	}
}

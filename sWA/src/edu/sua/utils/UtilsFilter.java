package edu.sua.utils;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;

public class UtilsFilter {

	// Check the target of the request is a servlet?
	public static boolean needJdbc(HttpServletRequest request) {
		// Servlet Url-pattern: /spath/*
		String servletPath = request.getServletPath(); // => /spath
		String pathInfo = request.getPathInfo(); // => /abc/mnp
		String urlPattern = servletPath;
		if (pathInfo != null) {
			urlPattern = servletPath + "/*"; // => /spath/*
		}
		// Collection of all servlet in us Webapp.
		// Key: servletName Value: ServletRegistration
		Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
				.getServletRegistrations();
		Collection<? extends ServletRegistration> values = servletRegistrations.values();
		for (ServletRegistration sr : values) {
			Collection<String> mappings = sr.getMappings();
			if (mappings.contains(urlPattern)) {
				request.getServletContext().log("JDBC Filter: urlPattern= " + urlPattern + "; found in servletRegistrations= " + mappings.toString());
				return true;
			}
		}
		request.getServletContext().log("JDBC Filter: urlPattern= " + urlPattern + "; not found in servletRegistrations, no need JDBC");
		return false;
	}
}

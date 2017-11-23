package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.repositories.GastenboekRepository;

@WebServlet("/gastenboek.htm")
public class GastenboekServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/gastenboek.jsp";
	private final transient GastenboekRepository gastenboekRepository = new GastenboekRepository();
	
	@Resource(name = GastenboekRepository.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
		gastenboekRepository.setDataSource(dataSource);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("gastenboek", gastenboekRepository.findAll());
		//request.setAttribute("showForm", request.getParameter("showForm"));		
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		String naam = request.getParameter("naam");
		if (naam == null || naam.trim().isEmpty()) {
			fouten.put("naam", "verplicht");
		}
		String bericht = request.getParameter("bericht");
		if (bericht == null || bericht.trim().isEmpty()) {
			fouten.put("bericht", "verplicht");
		}
		if (fouten.isEmpty()) {
			gastenboekRepository.toevoegen(naam, bericht);
			response.sendRedirect(request.getRequestURI());
		}
		else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
	
	// checken en testen

}

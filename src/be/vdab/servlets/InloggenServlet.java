package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/inloggen.htm")
public class InloggenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/inloggen.jsp";
	private static final String REDIRECT_URL = "/gastenboek.htm";
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		String wachtwoord = request.getParameter("wachtwoord");
		if (wachtwoord == null || wachtwoord.trim().isEmpty()) {
			fouten.put("wachtwoord", "Het wachtwoord moet ingevuld zijn!");
		}
		else {
			if(wachtwoord.equals(this.getServletContext().getInitParameter("wachtwoord"))) {
				HttpSession session = request.getSession();
				session.setAttribute("ingelogd", true);
			}
			else {
				fouten.put("wachtwoord", "Het ingevoerde wachtwoord is niet correct!");
			}
		}
		if (fouten.isEmpty()) {
			response.sendRedirect(request.getContextPath() + REDIRECT_URL);
		}
		else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}

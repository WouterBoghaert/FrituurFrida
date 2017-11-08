package be.vdab.servlets;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.repositories.ZoekDeFrietRepository;

@WebServlet("/zoekDeFriet.htm")
public class ZoekDeFrietServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/zoekdefriet.jsp";
	private ZoekDeFrietRepository zoekDeFrietRepository = new ZoekDeFrietRepository();;
    
//	public ZoekDeFrietServlet(HttpServletRequest request) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		if(request.getSession() != null) {
//			
//			session.removeAttribute("geklikt");
//		}
//	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("zoekDeFrietRepository", zoekDeFrietRepository);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("volgnummer") != null) {
			HttpSession session = request.getSession();
			@SuppressWarnings("unchecked")
			Set<Integer> geklikt = (Set<Integer>) session.getAttribute("geklikt");
			if(geklikt == null) {
				geklikt = new LinkedHashSet<>();
			}
			int volgnummer = Integer.parseInt(request.getParameter("volgnummer"));
			geklikt.add(volgnummer);
			session.setAttribute("geklikt", geklikt);
		}
		
		if(request.getParameter("nieuwSpel") != null) {
			zoekDeFrietRepository = new ZoekDeFrietRepository();
			HttpSession session = request.getSession();
			session.removeAttribute("geklikt");
		}
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
	}
}

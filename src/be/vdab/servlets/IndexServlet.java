package be.vdab.servlets;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Adres;
import be.vdab.entities.Gemeente;

@WebServlet(urlPatterns = "/index.htm", name="indexservlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setAttribute("telefoonHelpdesk", this.getServletContext().getInitParameter("telefoonHelpdesk"));
	request.setAttribute("adres", new Adres("Keizer Karelstraat", "100", new Gemeente("Gent", 9000)));
	DayOfWeek vandaag = LocalDate.now().getDayOfWeek();
	request.setAttribute("openGesloten", vandaag == DayOfWeek.MONDAY||vandaag == DayOfWeek.THURSDAY ? "gesloten" : "open");
	request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
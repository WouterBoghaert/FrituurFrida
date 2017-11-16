package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.entities.SausRadenSpel;
import be.vdab.repositories.SausRepository;

@WebServlet("/sausraden.htm")
public class SausRadenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/sausraden.jsp";
	private static final String SPEL = "sausRadenSpel";	
	private final transient SausRepository sausRepository = new SausRepository();
	
	@Resource (name = SausRepository.JNDI_NAME)
	void setDataSource (DataSource dataSource) {
		sausRepository.setDataSource(dataSource);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute(SPEL) == null) {
			session.setAttribute(SPEL, new SausRadenSpel(sausRepository.findAllSausNaam()));
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(request.getParameter("nieuwSpel") != null) {
			session.removeAttribute(SPEL);
		}
		else {
			if (request.getParameter("raden")!=null) {
				SausRadenSpel spel = (SausRadenSpel) session.getAttribute(SPEL);
				if(request.getParameter("letter") != null) {
					spel.setOutput(request.getParameter("letter"));
					session.setAttribute(SPEL, spel);
				}
			}
		}
		response.sendRedirect(request.getRequestURI());
	}
}

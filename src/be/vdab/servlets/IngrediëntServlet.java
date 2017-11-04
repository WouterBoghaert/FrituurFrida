package be.vdab.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Saus;
import be.vdab.repositories.SausRepository;

@WebServlet("/sauzen/ingrediënten.htm")
public class IngrediëntServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/ingrediënt.jsp";
    private final SausRepository sausRepository = new SausRepository();
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(request.getQueryString() != null) {
	    	String ingrediënt = request.getParameter("ingrediënt");
	    	if (ingrediënt != null && !ingrediënt.trim().isEmpty()) {
	    		List<Saus> sauzenByIngrediënt = sausRepository.findByIngrediënt(ingrediënt);
	    		if (!sauzenByIngrediënt.isEmpty()) {
	    			request.setAttribute("sauzenByIngrediënt", sauzenByIngrediënt);
	    		}
	    	}
	    	else {
	    		request.setAttribute("fout", "ingrediënt moet ingevuld zijn");
	    	}
    	}
    	request.getRequestDispatcher(VIEW).forward(request, response);
	}
}

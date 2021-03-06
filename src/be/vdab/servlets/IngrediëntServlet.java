package be.vdab.servlets;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.repositories.SausRepository;

@WebServlet("/sauzen/ingredienten.htm")
public class Ingredi�ntServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/ingredi�nt.jsp";
    private final transient SausRepository sausRepository = new SausRepository();
    
    @Resource (name = SausRepository.JNDI_NAME)
    void setDataSource (DataSource dataSource) {
    	sausRepository.setDataSource(dataSource);
    }
    
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(request.getQueryString() != null) {
	    	String ingredi�nt = request.getParameter("ingredi�nt");
	    	if (ingredi�nt != null && !ingredi�nt.trim().isEmpty()) {
	    		List<String> sauzenByIngredi�nt = sausRepository.findSausNaamByIngredi�nt(ingredi�nt);
	    		if (!sauzenByIngredi�nt.isEmpty()) {
	    			request.setAttribute("sauzenByIngredi�nt", sauzenByIngredi�nt);
	    		}
	    	}
	    	else {
	    		request.setAttribute("fout", "ingredi�nt moet ingevuld zijn");
	    	}
    	}
    	request.getRequestDispatcher(VIEW).forward(request, response);
	}
}

package be.vdab.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.repositories.SausRepository;

@WebServlet("/sauzen.htm")
public class SausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/saus.jsp";
	private static final String REDIRECT_URL = "/sauzen.htm";
	private final transient SausRepository sausRepository = new SausRepository();
	
	@Resource (name = SausRepository.JNDI_NAME)
	void setDataSource (DataSource dataSource) {
		sausRepository.setDataSource(dataSource);
	}
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("sauzen", sausRepository.findAll());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Set<Long> ids= Arrays.stream(request.getParameterValues("id")).map(id -> Long.parseLong(id)).collect(Collectors.toSet());
		if(ids != null && ids.size() != 0) {			
//			Arrays.stream(ids).forEach(id ->
//			sausRepository.removeById(Long.parseLong(id))); 
			sausRepository.removeByIds(ids);
		}
		response.sendRedirect(request.getContextPath() + REDIRECT_URL);
	}
}
package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Saus;

@WebServlet("/sauzen.htm")
public class SausServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/saus.jsp";
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Saus> sauzen = new ArrayList<>();
		sauzen.add(new Saus(1L, "cocktail",new String []{"ketchup","mayonaise","whisky"}));
		sauzen.add(new Saus(2L,"mayonaise",new String[] {"ei","mosterd","citroen","olijfolie"}));
		sauzen.add(new Saus(3L,"mosterd",new String[] {"mosterdzaad","olie"}));
		sauzen.add(new Saus(4L,"tartare",new String[] {"ei","mosterd","olijfolie","basilicum"}));
		sauzen.add(new Saus(5L,"vinaigrette",new String[] {"mosterd","olijfolie","azijn","basilicum"}));
		
		request.setAttribute("sauzen", sauzen);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
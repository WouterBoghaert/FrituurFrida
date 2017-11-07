package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/meisjesjongens.htm")
public class MeisjesJongensServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW ="/WEB-INF/JSP/meisjesjongens.jsp";
	private static final int COOKIE_MAX_LIFETIME = 1800;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getCookies() != null) {
			for(Cookie cookie : request.getCookies()) {
				if("meisjesjongens".equals(cookie.getName())){
					request.setAttribute("meisjesjongens", cookie.getValue());
					break;
				}
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie = new Cookie("meisjesjongens", request.getParameter("meisjesjongens"));
		cookie.setMaxAge(COOKIE_MAX_LIFETIME);
		response.addCookie(cookie);
		response.sendRedirect(request.getRequestURI());
	}

}

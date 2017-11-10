package be.vdab.filters;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("*.htm")
public class StatistiekRequestFilter implements Filter {
	private final static String STATISTIEK = "statistiek";

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		fConfig.getServletContext().setAttribute(STATISTIEK, 
	    		new ConcurrentHashMap<String, AtomicInteger>());
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if (request instanceof HttpServletRequest) {
			String url = ((HttpServletRequest)(request)).getRequestURI();
    		int index = url.indexOf(";jsessionid=");
    		if (index != -1) {
    			url = url.substring(0, index);
    		}
    		@SuppressWarnings("unchecked")
    		ConcurrentHashMap<String, AtomicInteger> statistiek = 
    			(ConcurrentHashMap<String, AtomicInteger>) request.getServletContext()
    			.getAttribute(STATISTIEK);
    		AtomicInteger aantalReedsAanwezig = 
				statistiek.putIfAbsent(url, new AtomicInteger(1));
    		if(aantalReedsAanwezig != null) {
    			aantalReedsAanwezig.incrementAndGet();
    		}
		}
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
		
	}

	/*
	 * package be.vdab.listeners;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class RequestStatistiekListener implements ServletContextListener, ServletRequestListener {

	private final static String STATISTIEK = "statistiek";
	private final static Set<String> UITGESLOTEN_EXTENSIES = 
		new CopyOnWriteArraySet<>(Arrays.asList("png","gif","jpg","css","js","ico"));
	
	@Override
	public void contextInitialized(ServletContextEvent event)  { 
        event.getServletContext().setAttribute(STATISTIEK, 
    		new ConcurrentHashMap<String, AtomicInteger>());
    }
	
	@Override
	public void contextDestroyed(ServletContextEvent event)  { 

    }
	
	@Override
	public void requestInitialized(ServletRequestEvent event)  { 
        if (event.getServletRequest() instanceof HttpServletRequest) {
        	HttpServletRequest request = 
    			(HttpServletRequest) event.getServletRequest();
        	String url = request.getRequestURI();
        	boolean verwerkRequest = true;
        	int laatstePuntIndex = url.lastIndexOf('.');
        	if (laatstePuntIndex != -1) {
        		String extensie = 
    				url.substring(laatstePuntIndex + 1).toLowerCase();
        		if(UITGESLOTEN_EXTENSIES.contains(extensie)) {
        			verwerkRequest = false;
        		}
        	}
        	if(verwerkRequest) {
        		int index = url.indexOf(";jsessionid=");
        		if (index != -1) {
        			url = url.substring(0, index);
        		}
        		@SuppressWarnings("unchecked")
        		ConcurrentHashMap<String, AtomicInteger> statistiek = 
        			(ConcurrentHashMap<String, AtomicInteger>) request.getServletContext()
        			.getAttribute(STATISTIEK);
        		AtomicInteger aantalReedsAanwezig = 
    				statistiek.putIfAbsent(url, new AtomicInteger(1));
        		if(aantalReedsAanwezig != null) {
        			aantalReedsAanwezig.incrementAndGet();
        		}
        	}
        }
    }
	
	@Override
    public void requestDestroyed(ServletRequestEvent event)  { 
         
    }

    

    

    
	
}

	 */
	

}

package be.vdab.listeners;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class RequestListener implements ServletContextListener, ServletRequestListener {

	private static final String INDEX_URL = "/";
	private static final String SAUZEN_URL = "/sauzen.htm";
	private static final String INGREDIENT_URL = "/sauzen/ingredienten.htm";	
	private static final String MEISJESJONGENS_URL = "/meisjesjongens.htm";
	private static final String ZOEKDEFRIET_URL = "/zoekdefriet.htm";
	private static final String SAUSRADEN_URL = "/sausraden.htm";
	private static final String STATISTIEK_URL = "/statistiek.htm";
	
	private static final String AANTAL_INDEX = "aantalIndex";
	private static final String AANTAL_SAUZEN = "aantalSauzen";
	private static final String AANTAL_INGREDIENTEN = "aantalIngredienten";
	private static final String AANTAL_MEISJESJONGENS = "aantalMeisjesjongens";
	private static final String AANTAL_ZOEKDEFRIET = "aantalZoekdefriet";
	private static final String AANTAL_SAUSRADEN = "aantalSausraden";
	private static final String AANTAL_STATISTIEK = "aantalStatistiek";
	
	@Override
	public void contextInitialized(ServletContextEvent event)  { 
        event.getServletContext().setAttribute(AANTAL_INDEX, new AtomicInteger());
        event.getServletContext().setAttribute(AANTAL_INGREDIENTEN, new AtomicInteger());
        event.getServletContext().setAttribute(AANTAL_MEISJESJONGENS, new AtomicInteger());
        event.getServletContext().setAttribute(AANTAL_SAUSRADEN, new AtomicInteger());
        event.getServletContext().setAttribute(AANTAL_SAUZEN, new AtomicInteger());
        event.getServletContext().setAttribute(AANTAL_STATISTIEK, new AtomicInteger());
        event.getServletContext().setAttribute(AANTAL_ZOEKDEFRIET, new AtomicInteger());
    }
	
	@Override
	public void contextDestroyed(ServletContextEvent event)  { 
        
	}
	
	@Override
	public void requestInitialized(ServletRequestEvent event)  {
	
		if (event.getServletRequest() instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
			 if ((event.getServletContext().getContextPath() + INDEX_URL).equals(request.getRequestURI())) {
		        	((AtomicInteger)
		    			event.getServletContext().getAttribute(AANTAL_INDEX))
		        		.incrementAndGet();
		     }
		}
       
        
		if (event.getServletRequest() instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
			 if ((event.getServletContext().getContextPath() + SAUZEN_URL).equals(request.getRequestURI())) {
		        	((AtomicInteger)
		    			event.getServletContext().getAttribute(AANTAL_SAUZEN))
		        		.incrementAndGet();
		     }
		}
        
		if (event.getServletRequest() instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
			 if ((event.getServletContext().getContextPath() + INGREDIENT_URL).equals(request.getRequestURI())) {
		        	((AtomicInteger)
		    			event.getServletContext().getAttribute(AANTAL_INGREDIENTEN))
		        		.incrementAndGet();
		     }
		}
        
		if (event.getServletRequest() instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
			 if ((event.getServletContext().getContextPath() + MEISJESJONGENS_URL).equals(request.getRequestURI())) {
		        	((AtomicInteger)
		    			event.getServletContext().getAttribute(AANTAL_MEISJESJONGENS))
		        		.incrementAndGet();
		     }
		}
        
		if (event.getServletRequest() instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
			 if ((event.getServletContext().getContextPath() + ZOEKDEFRIET_URL).equals(request.getRequestURI())) {
		        	((AtomicInteger)
		    			event.getServletContext().getAttribute(AANTAL_ZOEKDEFRIET))
		        		.incrementAndGet();
		     }
		}
        
		if (event.getServletRequest() instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
			 if ((event.getServletContext().getContextPath() + SAUSRADEN_URL).equals(request.getRequestURI())) {
		        	((AtomicInteger)
		    			event.getServletContext().getAttribute(AANTAL_SAUSRADEN))
		        		.incrementAndGet();
		     }
		}
        
		if (event.getServletRequest() instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
			 if ((event.getServletContext().getContextPath() + STATISTIEK_URL).equals(request.getRequestURI())) {
		        	((AtomicInteger)
		    			event.getServletContext().getAttribute(AANTAL_STATISTIEK))
		        		.incrementAndGet();
		     }
		}
	}
	
	@Override
    public void requestDestroyed(ServletRequestEvent event)  { 
         
    }

    

    

    
	
}

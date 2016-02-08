package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class MaxPrincipal
 *
 */
@WebListener
public class MaxPrincipal implements HttpSessionAttributeListener {

	/**
	 * Default constructor.
	 */
	public MaxPrincipal() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		Double maxPrincipal;
		HttpSession session = arg0.getSession();
		try {
			maxPrincipal = Double.parseDouble(session.getAttribute("A").toString());
		} catch (NullPointerException e) {
			maxPrincipal = 0.0;
		}
		Double principle = Double.parseDouble((session.getAttribute("A").toString()));
		if (principle > maxPrincipal) {
			maxPrincipal = principle;
		}
		try {
			session.setAttribute("maxPrincipal", maxPrincipal);
			return;
		} catch (StackOverflowError e) {
			return;
		}

	}

}

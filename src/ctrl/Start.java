package ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Loan;

/**
 * Servlet implementation class Start
 */
@WebServlet(urlPatterns = { "/Start", "/Start/*" })
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Start() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		Loan loan = new Loan();
		this.getServletContext().setAttribute("loan", loan);
	}

	/**
	 * doGet - Determine if the url path contains Fixed[A-Z] if so, sets the
	 * period to be fixed as per lab test description.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.setAttribute("A", 0.0); // for consistency

		// Set the path, used to determine if the path contains fixed principle
		// or not
		String path = request.getPathInfo();
		if (path == null) {
			path = "";
		}

		String letter = "";
		boolean fixed = false;

		// If the path contains Fixed[A-Z] then grab the letter, notify and set
		// the period to be fixed
		if (path.contains("FixedA") || path.contains("FixedB") || path.contains("FixedC") || path.contains("FixedD")
				|| path.contains("FixedE") || path.contains("FixedF")) {
			fixed = true;
			letter = path.replace("/Fixed", "");
			session.setAttribute("n", 10.0);
		} else if (path.contains("FixedG") || path.contains("FixedH") || path.contains("FixedI")
				|| path.contains("FixedJ") || path.contains("FixedK")) {
			fixed = true;
			letter = path.replace("/Fixed", "");
			session.setAttribute("n", 15.0);
		} else if (path.contains("FixedL") || path.contains("FixedM") || path.contains("FixedN")
				|| path.contains("FixedO")) {
			fixed = true;
			letter = path.replace("/Fixed", "");
			session.setAttribute("n", 20.0);
		} else if (path.contains("FixedP") || path.contains("FixedQ") || path.contains("FixedR")
				|| path.contains("FixedS") || path.contains("FixedT") || path.contains("FixedU")
				|| path.contains("FixedV") || path.contains("FixedW") || path.contains("FixedX")
				|| path.contains("FixedY") || path.contains("FixedZ")) {
			fixed = true;
			letter = path.replace("/Fixed", "");
			session.setAttribute("n", 30.0);
		}

		session.setAttribute("letter", letter);
		session.setAttribute("fixed", fixed);

		request.setAttribute("letter", letter);
		request.setAttribute("fixed", fixed);

		request.getRequestDispatcher("/UI.jspx").forward(request, response);
	}

	/**
	 * doPost - grabs the model from the session, grabs the values from the view
	 * then facilitates the communication from the view to the model.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Get the session
		HttpSession session = request.getSession();

		// Obtain the model
		Loan loan = (Loan) this.getServletContext().getAttribute("loan");

		// Determine the interest rate
		Double r = 0.0;
		if (request.getParameter("interest") != null) {
			r = Double.parseDouble(request.getParameter("interest"));
			session.setAttribute("r", r);
		} else {
			r = (Double) session.getAttribute("r");
		}

		// Determine the Principal. NOTE: I use principle really is principal,
		// but I used the spelling from lab1 all the way through all of the labs
		Double A = 0.0;
		if (request.getParameter("principle") != null) {
			A = Double.parseDouble(request.getParameter("principle"));
			session.setAttribute("A", A);
			double interest = A;
		} else {
			A = (Double) session.getAttribute("A");
		}

		// Determine the Period
		Double n = 0.0;
		if (request.getParameter("period") != null) {
			n = Double.parseDouble(request.getParameter("period"));
			session.setAttribute("n", n);
		} else {
			n = Double.parseDouble(session.getAttribute("n").toString());
		}

		// Determine Grace or not.
		boolean grace = false;
		if (request.getParameter("grace") != null) {
			grace = true;
		}
		session.setAttribute("grace", grace);

		// Compute Interest Rates and store in session
		double graceInterest = loan.computeGraceInterest(grace, r, A, n);
		double monthlyInterest = loan.computePayment(grace, r, A, n);
		session.setAttribute("graceInterest", graceInterest);
		session.setAttribute("monthlyInterest", monthlyInterest);

		// Store request attributes
		request.setAttribute("graceInterest", graceInterest);
		request.setAttribute("monthlyInterest", monthlyInterest);
		request.setAttribute("interest", r);
		request.setAttribute("period", n.toString().substring(0, n.toString().indexOf('.')));
		request.setAttribute("letter", session.getAttribute("letter"));
		request.setAttribute("fixed", session.getAttribute("fixed"));

		// Forward to view
		request.getRequestDispatcher("/Result.jspx").forward(request, response);
	}

}

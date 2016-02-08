package model;

public class Loan {

	static double fixedInterest = 5.0;
	static double gracePeriod = 6.0;

	/**
	 * Compute the Grace Period Interest
	 * 
	 * @param r
	 *            - principal
	 * @param A
	 *            - Interest
	 * @param n
	 *            - period
	 * @return
	 */
	public double computeGraceInterest(boolean grace, double r, double A, double n) {

		double result = 0;
		if (grace) {
			result = ((r + fixedInterest) / 100) * (A / 12) * gracePeriod;
		}
		System.out.println(result);
		return result;
	}

	/**
	 * 
	 * @param grace
	 * @param r
	 *            - interest
	 * @param A
	 *            - principal
	 * @param n
	 *            - period
	 * @return
	 */
	public double computePayment(boolean grace, double r, double A, double n) {
		double graceRate = ((r + fixedInterest) / 100);
		double rate = (r / 100);
		double result = (rate / 12) * (A / (1 - Math.pow((1 + (rate / 12)), (-n + gracePeriod))));
		if (grace) {

			double graceInterest = A * (graceRate / 12) * gracePeriod;
			result = result + (graceInterest / gracePeriod);
		}

		return result;
	}

}

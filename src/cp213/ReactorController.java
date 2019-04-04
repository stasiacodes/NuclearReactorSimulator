package cp213;

/**
 * A class to control a Reactor model. It's job is to initialize a Reactor and
 * maximize its power output while avoiding a meltdown.
 *
 * @author Anastasia Martynovitch
 * @author David Brown
 * @version 2019-03-22
 */
public class ReactorController implements Runnable {

	// The reactor to control.
	private Reactor model = null;

	/**
	 * Constructor.
	 *
	 * @param model The reactor model.
	 */
	public ReactorController(Reactor model) {
		this.model = model;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Runnable#run()
	 *
	 * Run the reactor control.
	 */
	@Override
	public void run() {

		final int MAXTEMP = 900;
		final int MAXDIFF = 4;

		while (this.model.getStatus() == Reactor.Status.OPERATING) {
			double oldtemp = this.model.getTemperature();
			if (this.model.getTemperature() >= 953) {
				this.model.dropRods();
				break;
			}
			this.model.tick();

			double temp = this.model.getTemperature();
			boolean increasing = (this.model.getTemperature() - oldtemp) > 0;
			boolean decreasingGr1 = (this.model.getTemperature() - oldtemp) < -MAXDIFF;
			boolean increasingGr1 = (this.model.getTemperature() - oldtemp) > MAXDIFF;

			if ((temp < MAXTEMP && !increasing) || decreasingGr1) {
				this.model.raiseRods();
			} else if ((temp > MAXTEMP && increasing) || increasingGr1) {
				this.model.lowerRods();
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
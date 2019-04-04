package cp213;

import cp213.Reactor.Status;

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
			boolean decreasingGr1 = (this.model.getTemperature()- oldtemp) < -MAXDIFF;
			boolean increasingGr1 = (this.model.getTemperature()- oldtemp) > MAXDIFF;
			
			if ((temp < MAXTEMP && !increasing) || decreasingGr1) {
					this.model.raiseRods();
			} else if ((temp > MAXTEMP && increasing) || increasingGr1) {
					this.model.lowerRods();
			}
			System.out.println("Status: " + this.model.getStatus());
			System.out.println("Temperature: " + this.model.getTemperature());
			System.out.println("Rod height: " + this.model.getRodsHeight());
			System.out.println("Power: " + this.model.getPower());
			System.out.println("Ticks: " + this.model.getTicks());
			System.out.println("Average Power: " + this.model.getAveragePower());
			System.out.println("Average Temp: " + this.model.getAverageTemperature());
			System.out.println("---------------------------------------");
		}
		System.out.println("Status: " + this.model.getStatus());
		System.out.println("Temperature: " + this.model.getTemperature());
	}
}
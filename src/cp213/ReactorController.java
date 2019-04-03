package cp213;

import cp213.Reactor.Status;

/**
 * A class to control a Reactor model. It's job is to initialize a Reactor and
 * maximize its power output while avoiding a meltdown.
 *
 * @author -- your name here --
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

//		double maxtemp = (997 + (this.model.getRodsHeight() - 200)) / 1.125;

//////////////////////////////////////////////////////////////////////////////////////	
		
//		int fast = 0;
//		int slow = 0;
//
//		double t = this.model.getTemperature();
//		int h = this.model.getRodsHeight();
//
//		while (t < maxtemp) {
//			t = t * 1.125 - h + 3;
//			h--;
//			fast++;
//			maxtemp = (997 + (h - 200)) / 1.125;
//		}
//
//		t = this.model.getTemperature();
//		h = this.model.getRodsHeight();
//
//		while (t < maxtemp) {
//			t = t * 1.125 - h - 3;
//			h--;
//			slow++;
//			maxtemp = (997 + (h - 200)) / 1.125;
//		}
//
//		System.out.println("fast: " + fast);
//		System.out.println("slow: " + slow);
//		System.out.println("----");
//
//		int i = 0;
//		int goalTemp = (int) (fast + slow) / 2;
//
//		while (i < goalTemp) {
//			this.model.raiseRods();
//			this.model.tick();
//			i++;
//			System.out.println("Status: " + this.model.getStatus());
//			System.out.println("Temperature: " + this.model.getTemperature());
//			System.out.println("Rod height: " + this.model.getRodsHeight());
//			System.out.println("Power: " + this.model.getPower());
//			System.out.println("Ticks: " + this.model.getTicks());
//			System.out.println("Average Power: " + this.model.getAveragePower());
//			System.out.println("Average Temp: " + this.model.getAverageTemperature());
//			System.out.println("----");
//		}
//		this.model.lowerRods();
//		while (this.model.getStatus() == Reactor.Status.OPERATING) {
//			this.model.tick();
//			if (this.model.getTemperature() >= maxtemp) {
//				this.model.dropRods();
//				break;
//			} else if (this.model.getTemperature() < goalTemp) {
//				this.model.raiseRods();
//			} else if (this.model.getTemperature() > goalTemp) {
//				this.model.lowerRods();
//			}
//		}

//////////////////////////////////////////////////////////////////////////////////////

//		double goalTemp = (997 + (this.model.getRodsHeight() - 200)) / 1.125;
//		System.out.println("Goal: " + goalTemp);
//
//		while (this.model.getStatus() == Reactor.Status.OPERATING && this.model.getTemperature() < goalTemp) {
//			this.model.tick();
//			if (this.model.getTemperature() < goalTemp) {
//				this.model.raiseRods();
//				goalTemp = (997 + (this.model.getRodsHeight() - 200)) / 1.125;
//			}
//			System.out.println("Status: " + this.model.getStatus());
//			System.out.println("Temperature: " + this.model.getTemperature());
//			System.out.println("Rod height: " + this.model.getRodsHeight());
//			System.out.println("Power: " + this.model.getPower());
//			System.out.println("Ticks: " + this.model.getTicks());
//			System.out.println("Average Power: " + this.model.getAveragePower());
//			System.out.println("Average Temp: " + this.model.getAverageTemperature());
//			System.out.println("----");
//		}
//		this.model.lowerRods();
//		while (this.model.getStatus() == Reactor.Status.OPERATING) {
//			this.model.tick();
//			if (this.model.getTemperature() >= maxtemp) {
//				this.model.dropRods();
//				break;
//			} else if (this.model.getTemperature() < goalTemp) {
//				this.model.raiseRods();
//			} else if (this.model.getTemperature() > goalTemp) {
//				this.model.lowerRods();
//			}
//		}

//////////////////////////////////////////////////////////////////////////////////////

//		double goalTemp = (maxtemp+100)/2;
//		while (this.model.getStatus() == Reactor.Status.OPERATING) {
//			
//			this.model.tick();
//
//			if (this.model.getTemperature() >= maxtemp) {
//				this.model.dropRods();
//				break;
//			} else if (this.model.getTemperature() < goalTemp) {
//				this.model.raiseRods();
//			} else if (this.model.getTemperature() > goalTemp) {
//				this.model.lowerRods();
//			}
//			
//			maxtemp = (997 + (this.model.getRodsHeight() - 200)) / 1.125;
//			
//			System.out.println("Status: " + this.model.getStatus());
//			System.out.println("Temperature: " + this.model.getTemperature());
//			System.out.println("Rod height: " + this.model.getRodsHeight());
//			System.out.println("Power: " + this.model.getPower());
//			System.out.println("Ticks: " + this.model.getTicks());
//			System.out.println("Average Power: " + this.model.getAveragePower());
//			System.out.println("Average Temp: " + this.model.getAverageTemperature());
//			System.out.println("Average: " + goalTemp);
//			System.out.println("----");
//		}

//////////////////////////////////////////////////////////////////////////////////////

//		double goalTemp = this.model.getTemperature();
////		double goalTemp = (1000+100)/2;
////		double goalHeight = 200/2;
//
//		double oldtemp = this.model.getTemperature();
//
//		while (this.model.getStatus() == Reactor.Status.OPERATING) {
//			if (this.model.getTemperature() >= maxtemp) {
//				this.model.dropRods();
//				break;
//			}
//			this.model.tick();
//			if (this.model.getTemperature() < goalTemp) {
//				if (this.model.getTemperature() < oldtemp) {
//					this.model.raiseRods();
//				}
//			} else if (this.model.getTemperature() > goalTemp) {
//				if (this.model.getTemperature() > oldtemp) {
//					this.model.lowerRods();
//
//				}
//			}
//			oldtemp = this.model.getTemperature();
//			maxtemp = (997 + (this.model.getRodsHeight() - 200)) / 1.125;
//			
//			System.out.println("Status: " + this.model.getStatus());
//			System.out.println("Temperature: " + this.model.getTemperature());
//			System.out.println("Rod height: " + this.model.getRodsHeight());
//			System.out.println("Power: " + this.model.getPower());
//			System.out.println("Ticks: " + this.model.getTicks());
//			System.out.println("Average Power: " + this.model.getAveragePower());
//			System.out.println("Average Temp: " + this.model.getAverageTemperature());
//			System.out.println("----");
//
//		}
		
//////////////////////////////////////////////////////////////////////////////////////	
		
		final int MAXTEMP = 900;
		final int MAXDIFF = 5;
		
		
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
			System.out.println("Ticks: " + this.model.getTicks());
			System.out.println("----");			
		}
//////////////////////////////////////////////////////////////////////////////////////
		System.out.println("Temperature: " + this.model.getTemperature());
		System.out.println("Status: " + this.model.getStatus());
		this.model.quit();
	}
}
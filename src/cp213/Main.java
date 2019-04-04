package cp213;

import javax.swing.JFrame;


/**
 * @author Anastasia Martynovitch
 * @author David Brown
 * @version 2019-03-22
 *
 */
public class Main {

	public static void main(String[] args) {
		Main main = new Main();

		// Run one or the other:

//		main.RunReactor(400, 50);
		main.RunReactorController(400, 50);
	}

	/**
	 * Run the Reactor model given an initial temperature and an initial rod
	 * lengths.
	 *
	 * @param initialTemperature Initial reactor temperature.
	 * @param initialRodsHeight  Initial reactor rods heights.
	 */
	public void RunReactor(final double initialTemperature, final int initialRodsHeight) {
		Reactor reactor = new Reactor(initialTemperature, initialRodsHeight);
		final ReactorView view = new ReactorView(reactor);

		final JFrame f = new JFrame("Nuclear Reactor");
		f.setContentPane(view);
		f.setSize(300, 350);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	    }

	/**
	 * Run the Reactor model with an automatic controller given an initial
	 * temperature and an initial rod lengths.
	 *
	 * @param initialTemperature Initial reactor temperature.
	 * @param initialRodsHeight  Initial reactor rods heights.
	 */
	public void RunReactorController(final int initialTemperature, final int initialRodsHeight) {
		Reactor reactor = new Reactor(initialTemperature, initialRodsHeight);
		final RCDataView view = new RCDataView(reactor);
		
		final JFrame f = new JFrame("Nuclear Reactor");
		f.setContentPane(view);
		f.setSize(300, 250);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	    }
		
//		final ReactorController rc = new ReactorController(reactor);
//		rc.run();
	}


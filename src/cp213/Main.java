package cp213;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cp213.Reactor.Status;
import java.util.Scanner;

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

		main.RunReactor(400, 50);
//		main.RunReactorController(400, 50);
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

		final JPanel dataView = new DataView(reactor);
		final JFrame dataFrame = new JFrame("Nuclear Reactor");
		dataFrame.setContentPane(dataView);
		dataFrame.setSize(300, 100);
		dataFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dataFrame.setVisible(true);
		
		final JPanel buttonView = new ButtonView(reactor);
		final JFrame buttonFrame = new JFrame("Nuclear Reactor");
		buttonFrame.setContentPane(buttonView);
		buttonFrame.setSize(300, 100);
		buttonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buttonFrame.setVisible(true);

//		while (reactor.getStatus() == Reactor.Status.OPERATING) {
//			System.out.println("Status: " + reactor.getStatus());
//			System.out.println("Temperature: " + reactor.getTemperature());
//			System.out.println("Rod height: " + reactor.getRodsHeight());
//			System.out.println("Power: " + reactor.getPower());
//			System.out.println("Ticks: " + reactor.getTicks());
//			System.out.println("Average Power: " + reactor.getAveragePower());
//			System.out.println("Average Temp: " + reactor.getAverageTemperature());
//			System.out.println("---------------------------------------");
//			Scanner keyboard = new Scanner(System.in);
//			System.out.println("1 = lower, 2 = raise, 3 = drop");
//			int rod = keyboard.nextInt();
//			if (rod == 1) {
//				reactor.lowerRods();
//			} else if (rod == 2) {
//				reactor.raiseRods();
//			} else if (rod == 3) {
//				reactor.dropRods();
//				break;
//			}
//			System.out.println("---------------------------------------");
//
//			reactor.tick();
//		}
//
//		System.out.println("Temperature: " + reactor.getTemperature());
//		System.out.println("Status: " + reactor.getStatus());
//		reactor.quit();
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
		ReactorController rc = new ReactorController(reactor);
		rc.run();
	}

}
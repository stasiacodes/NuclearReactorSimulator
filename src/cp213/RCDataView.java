package cp213;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//---------------------------------------------------------------
/**
 * Display all the key info about the reactor and allow the user to start and
 * stop the simulation. The controller will be controlled automatically.
 *
 * @author Anastasia Martynovitch
 * @version 2019-04-03
 */
@SuppressWarnings("serial")
public class RCDataView extends JPanel implements Runnable {

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// ---------------------------------------------------------------
	/**
	 * An inner class that uses an ActionListener to access the buttons. It sets the
	 * model values when the button is pressed.
	 */
	private class StartListener implements ActionListener {

		@Override
		public void actionPerformed(final ActionEvent evt) {
			RCDataView.this.quit.setEnabled(true);
			RCDataView.this.start.setEnabled(false);

			Runnable r = new Runnable() {
				public void run() {
					RCDataView.this.run();
				}
			};

			Thread t = new Thread(r);
			t.start();

		}
	}

	// ---------------------------------------------------------------
	/**
	 * An inner class that uses an ActionListener to access the buttons. It sets the
	 * model values when the button is pressed.
	 */
	private class QuitListener implements ActionListener {

		@Override
		public void actionPerformed(final ActionEvent evt) {
			RCDataView.this.model.quit();
			RCDataView.this.quit.setEnabled(false);
		}
	}

	// -------------------------------------------------------------------------------
	/**
	 * An inner class the updates the base and hypotenuse labels whenever the
	 * model's base attribute is updated.
	 */
	private class ChangeListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			RCDataView.this.status.setText(RCDataView.this.model.getStatus().toString());
			RCDataView.this.ticks.setText(Integer.toString(RCDataView.this.model.getTicks()));
			RCDataView.this.rodsHeight.setText(Integer.toString(RCDataView.this.model.getRodsHeight()));
			RCDataView.this.temp.setText(RCDataView.decimalFormat.format(RCDataView.this.model.getTemperature()));
			RCDataView.this.power.setText(RCDataView.decimalFormat.format(RCDataView.this.model.getPower()));
			RCDataView.this.avgTemp.setText(RCDataView.decimalFormat.format(RCDataView.this.model.getAverageTemperature()));
			RCDataView.this.avgPower.setText(RCDataView.decimalFormat.format(RCDataView.this.model.getAveragePower()));

		}
	}

	// -------------------------------------------------------------------------------

	/**
	 * Start Simulation
	 */
	private final JButton start = new JButton("Start");
	/**
	 * Quit Simulation
	 */
	private final JButton quit = new JButton("Quit");
	/**
	 * The format string for reading / displaying numeric input / output.
	 */
	private static final String formatString = "###.##";
	/**
	 * The formatters for reading / displaying numeric input / output.
	 */
	private static final DecimalFormat decimalFormat = new DecimalFormat(formatString);
	/**
	 * The status value field
	 */
	private final JLabel status = new JLabel(" ");
	/**
	 * The ticks value field
	 */
	private final JLabel ticks = new JLabel(" ");
	/**
	 * The Rods Height value field
	 */
	private final JLabel rodsHeight = new JLabel(" ");
	/**
	 * The ticks value field
	 */
	private final JLabel temp = new JLabel(" ");
	/**
	 * The ticks value field
	 */
	private final JLabel power = new JLabel(" ");
	/**
	 * The ticks value field
	 */
	private final JLabel avgTemp = new JLabel(" ");
	/**
	 * The ticks value field
	 */
	private final JLabel avgPower = new JLabel(" ");
	/**
	 * The reactor model.
	 */
	private final Reactor model;

	// ---------------------------------------------------------------
	/**
	 * The view constructor.
	 *
	 * @param model The Reactor model to view
	 */
	public RCDataView(final Reactor model) {
		this.model = model;
		this.layoutView();
		this.registerListeners();
		// Initialize the view labels.
		this.status.setText(this.model.getStatus().toString());
		this.ticks.setText(RCDataView.decimalFormat.format(this.model.getTicks()));
		this.rodsHeight.setText(RCDataView.decimalFormat.format(this.model.getRodsHeight()));
		this.temp.setText(RCDataView.decimalFormat.format(this.model.getTemperature()));
		this.power.setText(RCDataView.decimalFormat.format(this.model.getPower()));
		this.avgTemp.setText(RCDataView.decimalFormat.format(this.model.getAverageTemperature()));
		this.avgPower.setText(RCDataView.decimalFormat.format(this.model.getAveragePower()));
	}

	// ---------------------------------------------------------------
	/**
	 * Uses the GridLayout to place the labels and buttons
	 */
	private void layoutView() {
		// Define the widgets.
		this.status.setHorizontalAlignment(SwingConstants.RIGHT);
		this.ticks.setHorizontalAlignment(SwingConstants.RIGHT);
		this.rodsHeight.setHorizontalAlignment(SwingConstants.RIGHT);
		this.temp.setHorizontalAlignment(SwingConstants.RIGHT);
		this.power.setHorizontalAlignment(SwingConstants.RIGHT);
		this.avgTemp.setHorizontalAlignment(SwingConstants.RIGHT);
		this.avgPower.setHorizontalAlignment(SwingConstants.RIGHT);
		// Lay out the panel.
		this.setLayout(new GridLayout(8, 2));
		this.add(this.start);
		this.add(this.quit);
		this.add(new JLabel("Status: "));
		this.add(this.status);
		this.add(new JLabel("Ticks: "));
		this.add(this.ticks);
		this.add(new JLabel("Rods Height: "));
		this.add(this.rodsHeight);
		this.add(new JLabel("Core Temperature: "));
		this.add(this.temp);
		this.add(new JLabel("Power Generation: "));
		this.add(this.power);
		this.add(new JLabel("Average Temperature: "));
		this.add(this.avgTemp);
		this.add(new JLabel("Average Power: "));
		this.add(this.avgPower);
	}

	// ---------------------------------------------------------------
	/**
	 * Assigns listeners to the view widgets.
	 */
	private void registerListeners() {
		// Add widget listeners.
		this.start.addActionListener(new StartListener());
		this.quit.addActionListener(new QuitListener());
		this.model.addPropertyChangeListener("change", new ChangeListener());
	}

	// ---------------------------------------------------------------
}
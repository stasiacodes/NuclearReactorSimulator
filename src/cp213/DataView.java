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
 * stop the simulation.
 *
 * @author Anastasia Martynovitch
 * @version 2019-04-03
 */
@SuppressWarnings("serial")
public class DataView extends JPanel implements Runnable {

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Runnable#run()
	 *
	 * Run the reactor control.
	 */
	@Override
	public void run() {
		while (DataView.this.model.getStatus() == Reactor.Status.OPERATING) {
			DataView.this.model.tick();
			try {
				Thread.sleep(1000);
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
			DataView.this.quit.setEnabled(true);
			DataView.this.start.setEnabled(false);

			Runnable r = new Runnable() {
				public void run() {
					DataView.this.run();
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
			DataView.this.model.quit();
			DataView.this.quit.setEnabled(false);
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
			DataView.this.status.setText(DataView.this.model.getStatus().toString());
			DataView.this.ticks.setText(Integer.toString(DataView.this.model.getTicks()));
			DataView.this.rodsHeight.setText(Integer.toString(DataView.this.model.getRodsHeight()));
			DataView.this.temp.setText(DataView.decimalFormat.format(DataView.this.model.getTemperature()));
			DataView.this.power.setText(DataView.decimalFormat.format(DataView.this.model.getPower()));
			DataView.this.avgTemp.setText(DataView.decimalFormat.format(DataView.this.model.getAverageTemperature()));
			DataView.this.avgPower.setText(DataView.decimalFormat.format(DataView.this.model.getAveragePower()));

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
	public DataView(final Reactor model) {
		this.model = model;
		this.layoutView();
		this.registerListeners();
		// Initialize the view labels.
		this.status.setText(this.model.getStatus().toString());
		this.ticks.setText(DataView.decimalFormat.format(this.model.getTicks()));
		this.rodsHeight.setText(DataView.decimalFormat.format(this.model.getRodsHeight()));
		this.temp.setText(DataView.decimalFormat.format(this.model.getTemperature()));
		this.power.setText(DataView.decimalFormat.format(this.model.getPower()));
		this.avgTemp.setText(DataView.decimalFormat.format(this.model.getAverageTemperature()));
		this.avgPower.setText(DataView.decimalFormat.format(this.model.getAveragePower()));
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
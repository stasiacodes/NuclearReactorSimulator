package cp213;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// -------------------------------------------------------------------------------
/**
 * @author Anastasia Martynovitch
 * @version 2019-04-01
 *
 *          A simple thread example.
 */
@SuppressWarnings("serial")
public class ReactorView extends JPanel {

    // -------------------------------------------------------------------------------
    /**
     * Displays the current value of the model counter.
     */
//    private class DisplayListener implements PropertyChangeListener {
//
//	@Override
//	public void propertyChange(final PropertyChangeEvent arg0) {
//	    countLabel.setText("" + model.getCount());
//	}
//    }
//
    // -------------------------------------------------------------------------------
    /**
     * Suspends/resumes the counter.
     */
    private class RaiseRodsListener implements ActionListener {
	/**
	 * Suspends or resumes the model thread and updates the button text
	 * accordingly.
	 *
	 * @param evt
	 *            unused
	 */
	@Override
	public void actionPerformed(final ActionEvent evt) {

	    if (model.getSuspended()) {
		// Must define block as {@code synchronized} in order to call
		// {@code notify}.
		synchronized (model) {
		    model.setSuspended(false);
		    model.notify();
		}
		// Update the button text only after the model wakes up.
		suspendButton.setText("Suspend");
	    } else {
		// Must set model to 'wait' mode through the 'suspended' flag. A
		// direct call to {@code wait} from another object is not
		// allowed.
		model.setSuspended(true);
		suspendButton.setText("Resume");
	    }
	}
    }

    // -------------------------------------------------------------------------------

    private final JLabel rodHeightLabel = new JLabel("Rod Height");
    private final JLabel tempLabel = new JLabel("Core Temperature");
    private final JLabel powerLabel = new JLabel("Power");
    private final JLabel avgTempLabel = new JLabel("Avg. Temp");
    private final JLabel avgPowerLabel = new JLabel("Avg. Power");
    
    private final JLabel rodHeight = new JLabel();
    private final JLabel temp = new JLabel();
    private final JLabel power = new JLabel();
    private final JLabel avgTemp = new JLabel();
    private final JLabel avgPower = new JLabel();
    
    private final JButton lowerRods = new JButton("Lower Rods");
    private final JButton raiseRods = new JButton("Raise Rods");
    private final JButton dropRods = new JButton("EMERGENCY: DROP RODS");
    // The model to control.
    private final Reactor model;
    // Causes model to suspend or resume.
//    private final JButton suspendButton = new JButton("Suspend");

    // -------------------------------------------------------------------------------
    /**
     * Constructor.
     *
     * @param model
     *            The model to display.
     */
    public ReactorView(final Reactor model) {
	this.model = model;
	this.layoutView();
	this.registerListeners();
    }

    // -------------------------------------------------------------------------------
    /**
     * Lays out the panel components.
     */
    private void layoutView() {
    this.setLayout(new GridLayout(13, 2));
    // Add labels
	this.add(this.rodHeightLabel);
	this.add(this.tempLabel);
	this.add(this.powerLabel);
	this.add(this.avgTempLabel);
	this.add(this.avgPowerLabel);
	
	// Add labels with data
	this.rodHeight.setText("" + this.model.getRodsHeight());
	this.add(this.rodHeight);
	this.temp.setText("" + this.model.getTemperature());
	this.add(this.temp);
	this.power.setText("" + this.model.getPower());
	this.add(this.power);
	this.avgTemp.setText("" + this.model.getAverageTemperature());
	this.add(this.avgTemp);
	this.avgPower.setText("" + this.model.getAveragePower());
	this.add(this.avgPower);
	
	// Add buttons
	this.add(this.lowerRods);
	this.add(this.raiseRods);
	this.add(this.dropRods);

    }

    // -------------------------------------------------------------------------------
    /**
     * Registers all listeners with the appropriate inner listener classes.
     */
    private void registerListeners() {
	this.suspendButton.addActionListener(new SuspendListener());
//	this.model.addPropertyChangeListener(new DisplayListener());
    }

    // -------------------------------------------------------------------------------
}
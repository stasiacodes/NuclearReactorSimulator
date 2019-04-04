package cp213;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

// ---------------------------------------------------------------
/**
 * Display buttons to raise, lower, and drop rods.
 *
 * @author Anastasia Martynovitch
 * @version 2019-04-03
 */
@SuppressWarnings("serial")
public class ButtonView extends JPanel {

    // ---------------------------------------------------------------
    /**
     * An inner class that uses an ActionListener to access the buttons. It sets
     * the model values when the button is pressed.
     */
    private class LowerRodsListener implements ActionListener {
	
	@Override
	public void actionPerformed(final ActionEvent evt) {
	    ButtonView.this.model.lowerRods();
	}
    }

    // -------------------------------------------------------------------------------
    /**
     * An inner class that uses an ActionListener to access the buttons. It sets
     * the model values when the button is pressed.
     */
    private class RaiseRodsListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent evt) {
	    ButtonView.this.model.raiseRods();
	}
    }
    
 // -------------------------------------------------------------------------------
    /**
     * An inner class that uses an ActionListener to access the buttons. It sets
     * the model values when the button is pressed.
     */
    private class DropRodsListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent evt) {
	    ButtonView.this.model.dropRods();
	}
    }

    // -------------------------------------------------------------------------------
    /**
     * Lower Rods
     */
    private final JButton lowerRods = new JButton("Lower Rods");
    /**
     * Raise Rods
     */
    private final JButton raiseRods = new JButton("Raise Rods");
    /**
     * Drop Rods
     */
    private final JButton dropRods = new JButton("! DROP RODS !");
    /**
     * The right triangle model.
     */
    private final Reactor model;

    // ---------------------------------------------------------------
    /**
     * The view constructor.
     *
     * @param newModel
     *            The Reactor Model
     */
    public ButtonView(final Reactor newModel) {
	this.model = newModel;
	this.layoutView();
	this.registerListeners();
    }

    // ---------------------------------------------------------------
    /**
     * Uses the GridLayout to place the buttons.
     */
    private void layoutView() {
	this.setLayout(new BorderLayout());
	this.add(this.lowerRods, BorderLayout.WEST);
	this.add(this.raiseRods, BorderLayout.EAST);
	this.add(this.dropRods, BorderLayout.SOUTH);
    }

    // ---------------------------------------------------------------
    /**
     * Assigns listeners to the view widgets.
     */
    private void registerListeners() {
	// Add widget listeners.
	this.lowerRods.addActionListener(new LowerRodsListener());
	this.raiseRods.addActionListener(new RaiseRodsListener());
	this.dropRods.addActionListener(new DropRodsListener());
    }

    // ---------------------------------------------------------------
}
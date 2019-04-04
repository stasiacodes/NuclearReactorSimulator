package cp213;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//---------------------------------------------------------------
/**
 * View and update the right triangle model with numeric fields.
 *
 * @author David Brown from Byron Weber-Becker
 * @version 2017-06-19
 */
@SuppressWarnings("serial")
public class DataView extends JPanel {

    // ---------------------------------------------------------------
    /**
     * An inner class that uses a FocusListener to access the numeric field. It
     * sets the model values when the field loses focus.
     */
    private class BaseFieldListener implements FocusListener {
	// Automatically highlight the entire contents of the numeric field.
	@Override
	public void focusGained(final FocusEvent evt) {
	    DataView.this.base.selectAll();
	}

	@Override
	public void focusLost(final FocusEvent evt) {
	    double value = 0;

	    try {
		value = Double.parseDouble(DataView.this.base.getText());
	    } catch (final java.lang.NumberFormatException e) {
		value = Reactor.MAX_SIDE / 2;
	    } finally {
		DataView.this.model.setBase(value);
	    }
	}
    }

    // -------------------------------------------------------------------------------
    /**
     * An inner class the updates the base and hypotenuse labels whenever the
     * model's base attribute is updated.
     */
    private class BaseListener implements PropertyChangeListener {

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
	    DataView.this.base.setText(
		    decimalFormat.format(DataView.this.model.getBase()));
	    DataView.this.hypo.setText(DataView.decimalFormat
		    .format(DataView.this.model.getHypotenuse()));
	}
    }

    // -------------------------------------------------------------------------------
    /**
     * An inner class that uses a FocusListener to access the numeric field. It
     * sets the model values when the field loses focus.
     */
    private class HeightFieldListener implements FocusListener {
	// Automatically highlight the entire contents of the numeric field.
	@Override
	public void focusGained(final FocusEvent evt) {
	    DataView.this.height.selectAll();
	}

	@Override
	public void focusLost(final FocusEvent evt) {
	    double value = 0;

	    try {
		value = Double.parseDouble(DataView.this.height.getText());
	    } catch (final java.lang.NumberFormatException e) {
		value = Reactor.MAX_SIDE / 2;
	    } finally {
		DataView.this.model.setHeight(value);
	    }
	}
    }

    // -------------------------------------------------------------------------------
    /**
     * An inner class the updates the height and hypotenuse labels whenever the
     * model's height attribute is updated.
     */
    private class HeightListener implements PropertyChangeListener {

	@Override
	public void propertyChange(final PropertyChangeEvent evt) {
	    DataView.this.height.setText(
		    decimalFormat.format(DataView.this.model.getHeight()));
	    DataView.this.hypo.setText(DataView.decimalFormat
		    .format(DataView.this.model.getHypotenuse()));
	}
    }

    // -------------------------------------------------------------------------------

    /**
     * The format string for reading / displaying numeric input / output.
     */
    private static final String formatString = "###.##";
    /**
     * The formatters for reading / displaying numeric input / output.
     */
    private static final DecimalFormat decimalFormat = new DecimalFormat(
	    formatString);
    /**
     * The base value field.
     */
    private final JTextField base = new JTextField(formatString.length());
    /**
     * The height value field.
     */
    private final JTextField height = new JTextField(formatString.length());
    /**
     * The hypotenuse value field - cannot be edited by the user.
     */
    private final JLabel hypo = new JLabel(" ");
    /**
     * The right triangle model.
     */
    private final Reactor model;

    // ---------------------------------------------------------------
    /**
     * The view constructor.
     *
     * @param model
     *            The right triangle model to view.
     */
    public DataView(final Reactor model) {
	this.model = model;
	this.layoutView();
	this.registerListeners();
	// Initialize the view labels.
	this.base.setText(decimalFormat.format(this.model.getBase()));
	this.height.setText(decimalFormat.format(this.model.getHeight()));
	this.hypo.setText(
		DataView.decimalFormat.format(this.model.getHypotenuse()));
    }

    // ---------------------------------------------------------------
    /**
     * Uses the BoxLayout to place the labels and numeric fields.
     */
    private void layoutView() {
	// Define the widgets.
	this.base.setHorizontalAlignment(SwingConstants.RIGHT);
	this.height.setHorizontalAlignment(SwingConstants.RIGHT);
	this.hypo.setHorizontalAlignment(SwingConstants.RIGHT);
	// Lay out the panel.
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	this.add(new JLabel("Base: "));
	this.add(this.base);
	this.add(new JLabel("Height: "));
	this.add(this.height);
	this.add(new JLabel("Hypotenuse: "));
	this.add(this.hypo);
    }

    // ---------------------------------------------------------------
    /**
     * Assigns listeners to the field widgets and the model.
     */
    private void registerListeners() {
	// Add widget listeners.
	this.base.addFocusListener(new BaseFieldListener());
	this.height.addFocusListener(new HeightFieldListener());
	// Add model listeners.
	this.model.addPropertyChangeListener(Reactor.BASE_CHANGE,
		new BaseListener());
	this.model.addPropertyChangeListener(Reactor.HEIGHT_CHANGE,
		new HeightListener());
    }

    // ---------------------------------------------------------------
}
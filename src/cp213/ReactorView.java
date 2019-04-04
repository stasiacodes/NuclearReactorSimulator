package cp213;

import java.awt.BorderLayout;

import javax.swing.JPanel;

//---------------------------------------------------------------
/**
 * Puts three right triangle views into the same JPanel using various layouts.
 * Each view is updated individually rather than through top view.
 *
 * @author Anastasia Martynovitch
 * @version 2019-04-03
 */
@SuppressWarnings("serial")
public class ReactorView extends JPanel {
	// The model views.
	private ButtonView bView = null;
	private DataView dView = null;

	// ---------------------------------------------------------------
	/**
	 * View constructor.
	 *
	 * @param model The model to attach the individual views to.
	 */
	public ReactorView(final Reactor model) {
		this.dView = new DataView(model);
		this.bView = new ButtonView(model);
		this.layoutView();
	}

	// ---------------------------------------------------------------
	/**
	 * Lays out the individual model views within the main frame.
	 */
	private void layoutView() {
		// Place the numeric and button views on top and the graphic view
		// underneath.
		this.setLayout(new BorderLayout());
		this.add(this.dView, BorderLayout.NORTH);
		this.add(this.bView, BorderLayout.SOUTH);
	}

	// ---------------------------------------------------------------
	
	
	
}
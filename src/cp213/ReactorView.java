package cp213;

import java.awt.BorderLayout;

import javax.swing.JPanel;

//---------------------------------------------------------------
/**
 * Puts 2 controller views into the same panel. Each view is updated individually.
 *
 * @author Anastasia Martynovitch
 * @version 2019-04-03
 */
@SuppressWarnings("serial")
public class ReactorView extends JPanel implements Runnable {
	// The model views.
	private ButtonView bView = null;
	private DataView dView = null;
	private Reactor model = null;
	
	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Runnable#run()
	 *
	 * Run the reactor control.
	 */
	@Override
	public void run() {
		while (this.model.getStatus() == Reactor.Status.OPERATING) {
			this.model.tick();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// ---------------------------------------------------------------
	/**
	 * View constructor.
	 *
	 * @param model The model to attach the individual views to.
	 */
	public ReactorView(final Reactor model) {
		this.dView = new DataView(model);
		this.bView = new ButtonView(model);
		this.model = model;
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
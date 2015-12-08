package plugins;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Class used to trigger an action every given milliseconds
 * 
 * @author Maxime Opsommer
 * @author Damien Toulouse
 */
public class ConfigurableTimer implements ActionListener{
	
	// Fields
	
	protected final int maxIterations;
	protected int nbIterations;
	protected final ActionListener listener;
	
	// Methods
	
	/**
	 * Constructor for the Timer class
	 * 
	 * @param listener : the listener to which we want to send a message
	 * @param maxIterations : the limit of iterations the Timer has to do
	 */
	public ConfigurableTimer(ActionListener listener, int maxIterations) {
		this.maxIterations = maxIterations;
		this.listener = listener;
	}
	
	/**
	 * Constructor for the Timer class, there is no iterations limit
	 * 
	 * @param listener : the listener to which we want to send a message
	 */
	public ConfigurableTimer(ActionListener listener) {
		this(listener, 0);
	}
	
	/**
	 * Starts the iterations
	 * 
	 * @param milliseconds : the interval between each iteration
	 */
	public void start(int milliseconds) {
		nbIterations = 0;
		Timer timer = new Timer(milliseconds, this);
		timer.start();
		while(maxIterations == 0 || nbIterations < maxIterations)
			nbIterations++;
		timer.stop();
	}

	/**
	 * @see ActionListener#actionPerformed(ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		nbIterations++;
		listener.actionPerformed(e);
	}

}

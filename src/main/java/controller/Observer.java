package controller;

/**
 * Interface class for the Observer, which forces all views to implement the
 * update method.
 */

public interface Observer {

	/**
	 * method to be implemented that reacts to the notification generally by
	 * interrogating the model object and displaying its newly updated state.
	 * 
	 * @param p_observableState: Object that is passed by the subject (observable).
	 *                            Very often, this object is the subject itself, but
	 *                            not necessarily.
	 */
	void update(Observable p_observableState);
}

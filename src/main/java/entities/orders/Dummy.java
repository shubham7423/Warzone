package entities.orders;

import controller.GameEngine;

/**
 * This class is dummy order class, in order to return something of Order type in 
 * command which does all the processing itself and not done by Orders object, then this class is used. 
 */
public class Dummy implements Orders {

	/**
	 * This is the dummy order which does nothing.
	 * 
	 * @param p_game Object of Game Engine.
	 * @return String format for output of execute order.
	 */
	@Override
	public String executeOrder(GameEngine p_game) {
		return null;
	}
	
	/**
	 * This method is used to get the order in String format.
	 * 
	 * @return command in String form.
	 */
	public String getOrder() {
		return "";
	}
}

package entities.orders;

import controller.GameEngine;

public class Dummy implements Orders {

	/**
	 * This is the dummy order which does nothing.
	 * 
	 * @param p_game Object of Game Engine.
	 * @return String format for output of execute order.
	 */
	@Override
	public String executeOrder(GameEngine p_game) {
		// TODO Auto-generated method stub
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

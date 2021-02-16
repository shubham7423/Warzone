package entities;

import controller.GameStarter;

public class Deploy implements Orders {

	@Override
	public String executeOrder(GameStarter p_game, String[] p_commandSplitted, Player p_player) {
		if(!p_player.getCountries().containsKey(p_commandSplitted[1])) {
			return String.format("Player \"%s\" does not control country \"%s\"", p_player.getName(), p_commandSplitted[1]);
		}
		if(p_player.getNumberOfArmies() < Integer.parseInt(p_commandSplitted[2])) {
			return String.format("Player \"%s\" does not enough armies", p_player.getName());
		}
		p_game.getGameMap().getCountries().get(p_commandSplitted[1]).placeArmies(Integer.parseInt(p_commandSplitted[2]));
		p_player.removeArmies(Integer.parseInt(p_commandSplitted[2]));
		return String.format("Player \"%s\" deployed \"%s\" armies to country \"%s\"", p_player.getName(), p_commandSplitted[2], p_commandSplitted[1]);
	}

}

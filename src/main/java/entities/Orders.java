package entities;

import controller.GameStarter;

public interface Orders {
	String executeOrder(GameStarter p_game, String[] p_commandSplitted, Player p_player);
}

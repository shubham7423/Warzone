/**
 * 
 */
package entities.orders;

import controller.GameEngine;
import entities.Player;

/**
 * This class represents the diplomacy card.
 */
public class Diplomacy implements Orders {

	private Player d_player;
	private Player d_otherPlayer;
	private String d_otherPlayerName;
	
	public Diplomacy(Player p_player, String p_otherPlayer) {
		d_player = p_player;
		d_otherPlayerName = p_otherPlayer;
	}
	
	/**
	 * @param p_game gets the object of GameEngine class
	 * @return string
	 */
	@Override
	public String executeOrder(GameEngine p_game) {
		// TODO Auto-generated method stub
		if(!p_game.d_playerName.contains(d_otherPlayerName)) {
			return "Mentioned Player \"" + d_otherPlayerName + "\" does not exist";
		} else {
			int l_diplomacyCardCount = d_player.d_cardsOwned.get("diplomacy");
			if(l_diplomacyCardCount < 1) {
				return "Player does not have Diplomacy Card.";
			} else {
				d_otherPlayer = p_game.d_players.get(d_otherPlayerName);
				d_otherPlayer.d_negotiatedPlayerNames.add(d_player.getName());
				d_player.d_negotiatedPlayerNames.add(d_otherPlayer.getName());
				d_player.d_cardsOwned.replace("diplomacy", (l_diplomacyCardCount-1));
				return "Negotiation between Players \"" + d_player.getName()+"\" and \"" + d_otherPlayer.getName() + "\" done successfully for 1 time.";
			}
		}
	}
}

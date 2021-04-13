package entities.orders;

import controller.GameEngine;
import entities.Player;

/**
 * This class represents the diplomacy card. The Diplomacy Card enforces peace
 * between two players for a variable number of turns. While peace is enforced,
 * neither player will be able to attack the other. The card takes effect the
 * turn after it is played.
 */
public class Diplomacy implements Orders {
	private Player d_player;
	private Player d_otherPlayer;
	private String d_otherPlayerName;

	/**
	 * Constructor of Diplomacy Card class for instantiating members.
	 * 
	 * @param p_player      Player object of the player who wants to negotiate with
	 *                      other player for one time.
	 * @param p_otherPlayer Name of the other player who is being negotiated for one
	 *                      time.
	 */
	public Diplomacy(Player p_player, String p_otherPlayer) {
		d_player = p_player;
		d_otherPlayerName = p_otherPlayer;
	}

	/**
	 * This method is used to get the order in String format.
	 * 
	 * @return command in String form.
	 */
	public String getOrder() {
		return "negotiate " + d_otherPlayerName;
	}

	/**
	 * method to execute diplomacy command
	 * 
	 * @param p_game gets the object of GameEngine class
	 * @return string Result based on the execution of the Diplomacy Card.
	 */
	@Override
	public String executeOrder(GameEngine p_game) {
		if (!p_game.d_playerName.contains(d_otherPlayerName)) {
			return "Player \"" + d_otherPlayerName + "\" does not exist";
		} else {
			int l_diplomacyCardCount = d_player.d_cardsOwned.get("diplomacy");
			if (l_diplomacyCardCount < 1) {
				return String.format("Player \"%s\" does not have Diplomacy Card.", d_player.getName());
			} else {
				d_otherPlayer = p_game.d_players.get(d_otherPlayerName);
				d_otherPlayer.d_negotiatedPlayerNames.add(d_player.getName());
				d_player.d_negotiatedPlayerNames.add(d_otherPlayer.getName());
				d_player.d_cardsOwned.replace("diplomacy", (l_diplomacyCardCount - 1));
				return "Diplomacy between Players \"" + d_player.getName() + "\" and \"" + d_otherPlayer.getName()
						+ "\" established successfully.";
			}
		}
	}
}

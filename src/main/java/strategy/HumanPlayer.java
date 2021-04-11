package strategy;

import controller.GameEngine;
import controller.UserCommand;
import controller.state.gamephase.gameplay.IssueOrders;
import entities.Player;
import entities.orders.Advance;
import entities.orders.Airlift;
import entities.orders.Blockade;
import entities.orders.Bomb;
import entities.orders.Deploy;
import entities.orders.Diplomacy;
import entities.orders.Exit;
import entities.orders.Orders;
import entities.savedgames.SaveGame;

public class HumanPlayer extends PlayerStrategy {

	public HumanPlayer(Player p_player, GameEngine p_gameEngine) {
		super(p_player, p_gameEngine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Orders createOrder() {
		SaveGame l_saveGame= new SaveGame(d_gameEngine);
		// TODO Auto-generated method stub
		UserCommand l_userCommand = new UserCommand();
		l_userCommand.setPhase(new IssueOrders(null));
		String[] l_splittedOrder = null;
		boolean l_isCorrect = false;
		Orders l_orders = null;
		while (!l_isCorrect) {
			try {
				String l_result = l_userCommand.getCommand();
				if ("exit()".equals(l_result)) {
					l_isCorrect = true;
					d_player.setIsCommit(true);
					return new Exit();
				} else {
					l_splittedOrder = l_result.split(" ");

					switch (l_splittedOrder[0]) {
					case "deploy":
						if (l_splittedOrder.length != 3) {
							String l_temp = "Invalid command. Correct command is - deploy countryId numarmies";
							System.out.println(l_temp);
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_temp);
							continue;
						} else if (!isNumeric(l_splittedOrder[1]) || !isNumeric(l_splittedOrder[2])) {
							String l_temp = "After deploy keyword, you can only use integer to represent the countryId and numarmies";
							System.out.println(l_temp);
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_temp);
							continue;
						} else {
							Deploy l_deploy = new Deploy(d_player, Integer.parseInt(l_splittedOrder[1]),
									Integer.parseInt(l_splittedOrder[2]));
							l_orders = l_deploy;
							String l_temp = "deploy " + Integer.parseInt(l_splittedOrder[1]) + " "
									+ Integer.parseInt(l_splittedOrder[2]);
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_temp);
							l_isCorrect = true;
						}
						break;
					case "advance":
						if (l_splittedOrder.length != 4) {
							String l_temp = "Invalid command. Correct command is - advance countryFrom countryTo numarmies";
							System.out.println(l_temp);
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_temp);
							continue;
						} else if (!isNumeric(l_splittedOrder[1]) || !isNumeric(l_splittedOrder[2])
								|| !isNumeric(l_splittedOrder[3])) {
							String l_temp = "After advance keyword, you can only use integer to represent the countryFrom, countryTo and numarmies";
							System.out.println(l_temp);
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_temp);
							continue;
						} else {
							Advance l_advance = new Advance(d_player, Integer.parseInt(l_splittedOrder[1]),
									Integer.parseInt(l_splittedOrder[2]), Integer.parseInt(l_splittedOrder[3]));
							l_orders = l_advance;
							String l_temp = "advance " + Integer.parseInt(l_splittedOrder[1]) + " "
									+ Integer.parseInt(l_splittedOrder[2]) + " " + Integer.parseInt(l_splittedOrder[3]);
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_temp);
							l_isCorrect = true;
						}
						break;
					case "bomb":
						if (l_splittedOrder.length != 2) {
							String l_temp = "Invalid command. Correct command is - bomb countryId";
							System.out.println(l_temp);
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_temp);
							continue;
						} else if (!isNumeric(l_splittedOrder[1])) {
							String l_temp = "After bomb keyword, you can only use integer to represent the countryId";
							System.out.println(l_temp);
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_temp);
							continue;
						} else {
							Bomb l_bomb = new Bomb(d_player, Integer.parseInt(l_splittedOrder[1]));
							l_orders = l_bomb;
							String l_temp = "bomb " + Integer.parseInt(l_splittedOrder[1]);
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_temp);
							l_isCorrect = true;
						}
						break;
					case "blockade":
						if (l_splittedOrder.length != 2) {
							String l_temp = "Invalid command. Correct command is - blockade countryId";
							System.out.println(l_temp);
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_temp);
							continue;
						} else if (!isNumeric(l_splittedOrder[1])) {
							String l_temp = "After blockade keyword, you can only use integer to represent the countryId";
							System.out.println(l_temp);
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_temp);
							continue;
						} else {
							Blockade l_blockade = new Blockade(d_player, Integer.parseInt(l_splittedOrder[1]));
							l_orders = l_blockade;
							String l_temp = "blockade " + Integer.parseInt(l_splittedOrder[1]);
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_temp);
							l_isCorrect = true;
						}
						break;
					case "airlift":
						if (l_splittedOrder.length != 4) {
							String l_temp = "Invalid command. Correct command is - airlift sourceCountryId targetCountryId numarmies";
							System.out.println(l_temp);
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_temp);
							continue;
						} else if (!isNumeric(l_splittedOrder[1]) || !isNumeric(l_splittedOrder[2])
								|| !isNumeric(l_splittedOrder[3])) {
							String l_temp = "After airlift keyword, you can only use integer to represent the sourceCountryId, targetCountryId and numarmies";
							System.out.println(l_temp);
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_temp);
							continue;
						} else {
							Airlift l_airlift = new Airlift(d_player, Integer.parseInt(l_splittedOrder[1]),
									Integer.parseInt(l_splittedOrder[2]), Integer.parseInt(l_splittedOrder[3]));
							l_orders = l_airlift;
							String l_temp = "airlift " + Integer.parseInt(l_splittedOrder[1]) + " "
									+ Integer.parseInt(l_splittedOrder[2]) + " " + Integer.parseInt(l_splittedOrder[3]);
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_temp);
							l_isCorrect = true;
						}
						break;
					case "negotiate":
						if (l_splittedOrder.length != 2) {
							String l_temp = "Invalid command. Correct command is - negotiate playerId";
							System.out.println(l_temp);
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_temp);
							continue;
						} else if (isNumeric(l_splittedOrder[1])) {
							String l_temp = "After negotiate keyword, you can not use integer to represent the playerName";
							System.out.println(l_temp);
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_temp);
							continue;
						} else {
							Diplomacy l_diplomacy = new Diplomacy(d_player, l_splittedOrder[1]);
							l_orders = l_diplomacy;;
							String l_temp = "negotiate " + l_splittedOrder[1];
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_temp);
							l_isCorrect = true;
						}
						break;
					case "savegame":
						if (l_splittedOrder.length != 2) {
							String l_temp = "Invalid command. Correct command is - savegame fileName.game";
							System.out.println(l_temp);
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_temp);
							continue;
						} else {
							String l_saveGameResult = l_saveGame.saveGame(l_splittedOrder[1], d_player);
							l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_saveGameResult);
							System.out.println(l_saveGameResult);
						}
						break;
					default:
						System.out.println(l_result);
						l_userCommand.d_gameEngine.d_logEntryBuffer.setString(l_result);
						break;
					}
				}
			} catch (Exception p_exception) {
				System.out.println("Invalid command in phase IssueOrders.");
			}
		}
		return l_orders;
	}
	
	/**
	 * This function is used to check if a string can be converted to integer or
	 * not.
	 * 
	 * @param p_str represents the string to be casted to Integer value.
	 * @return true if the string can be parsed to an Integer.
	 */
	public static boolean isNumeric(String p_str) {
		try {
			Integer.parseInt(p_str);
			return true;
		} catch (NumberFormatException p_e) {
			return false;
		} catch (Exception p_e) {
			return false;
		}
	}

}

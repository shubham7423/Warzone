package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import controller.state.Phase;
import controller.state.edit.EditPhase;
import controller.state.edit.PreEdit;
import controller.state.gamephase.GamePhase;
import controller.state.gamephase.gamesetup.PreLoad;
import entities.GameMap;
import entities.Player;
import entities.orders.Deploy;

/**
 * Game Engine class is the starting point of the game where commands are
 * processed and extracted to support the functionalities provided by the
 * commands.
 */
public class GameEngine {
	private Phase d_phase;
	private GameMap d_gameMap = new GameMap();
	public Queue<Player> d_playersOrder = new LinkedList<>();
	public HashMap<String, Player> d_players = new HashMap<>();
	public ArrayList<String> d_playerName = new ArrayList<>();
	public UserCommand d_userCommand;
	public LogEntryBuffer d_logEntryBuffer;
	private LogWriter d_logWriter;
	public Player d_neutralPlayer;
	public Random d_random;
	private int d_turnNumber = 0;
	private int d_maxTurn = 500;

	/**
	 * Game Engine constructor which creates a neutral player when game is started
	 * and also initializes LogEntryBuffer which is observable and attaches it to
	 * LogWriter.
	 */
	public GameEngine() {
		d_logEntryBuffer = new LogEntryBuffer();
		d_logWriter = new LogWriter(d_logEntryBuffer);
		d_neutralPlayer = new Player("neutralPlayer#1");
		d_random = new Random();
	}

	/**
	 * a function containing switch case to identify which command has been entered
	 * by the user for further processing
	 * 
	 * @param p_splittedCommand the command that has been splitted into multiple
	 *                          parts for processing
	 * @return the result of the command to be executed
	 */
	public String executeCommand(String[] p_splittedCommand) {
		String l_result = "";
		switch (p_splittedCommand[0]) {
		case "loadmap":
			l_result = loadMap(p_splittedCommand);
			break;

		case "editcontinent":
			l_result = editContinent(p_splittedCommand);
			break;

		case "editcountry":
			l_result = editCountry(p_splittedCommand);
			break;

		case "editneighbor":
			l_result = editNeighbor(p_splittedCommand);
			break;

		case "editmap":
			l_result = editMap(p_splittedCommand);
			break;

		case "savemap":
			l_result = saveMap(p_splittedCommand);
			break;

		case "gameplayer":
			l_result = gamePlayer(p_splittedCommand);
			break;

		case "assigncountries":
			l_result = assignCountries(p_splittedCommand);
			break;

		case "validatemap":
			l_result = validateMap(p_splittedCommand);
			break;

		case "showmap":
			l_result = showmap();
			break;

		case "deploy":
			l_result = deploy(p_splittedCommand);
			break;

		case "negotiate":
			l_result = diplomacy(p_splittedCommand);
			break;

		case "advance":
			l_result = advance(p_splittedCommand);
			break;

		case "airlift":
			l_result = airlift(p_splittedCommand);
			break;

		case "bomb":
			l_result = bomb(p_splittedCommand);
			break;

		case "blockade":
			l_result = blockade(p_splittedCommand);
			break;

		case "tournament":
			l_result = tournament(p_splittedCommand);
			break;

		default:
			l_result = "Command not found";
		}

		return l_result;
	}

	/**
	 * function to get the order size of the player
	 * 
	 * @return an integer representing the size of the orders, the players has
	 */
	public int getPlayersOrderSize() {
		return d_playersOrder.size();
	}

	/**
	 * function to add order to the order queue of the player
	 * 
	 * @param p_player represents the player whose order is to be added to the queue
	 */
	public void addPlayerOrder(Player p_player) {
		d_playersOrder.add(p_player);
	}

	/**
	 * function to remove an order from the order queue of the player
	 * 
	 * @return removal of order from the queue of the player
	 */
	public Player getPlayerOrder() {
		return d_playersOrder.remove();
	}

	/**
	 * function to obtain the phase in which we are present in the game
	 * 
	 * @return the phase we currently are in the game
	 */
	public Phase getPhase() {
		return d_phase;
	}

	/**
	 * function to return the current state/situation of the game map
	 * 
	 * @return the current game map
	 */
	public GameMap getGameMap() {
		return d_gameMap;
	}

	/**
	 * function to return the maximum turns in a game. Game is draw when maximum.
	 * turns reached.
	 * 
	 * @return maximum turns in game
	 */
	public int getMaxTurns() {
		return d_maxTurn;
	}

	/**
	 * function to set maximum turns per game.
	 * 
	 * @param p_maxTurn number of turns.
	 */
	public void setMaxTurns(int p_maxTurn) {
		d_maxTurn = p_maxTurn;
	}

	/**
	 * function to return current turn in game play.
	 * 
	 * @return return current turn number
	 */
	public int getCurrentTurn() {
		return d_turnNumber;
	}

	/**
	 * function to increment the value of current turn, incremented when one
	 * execution phase is completed.
	 */
	public void incrementTurn() {
		++d_turnNumber;
	}

	/**
	 * function to set the game map according to the parameter provided
	 * 
	 * @param p_gameMap the game map state we want to set
	 */
	public void setGameMap(GameMap p_gameMap) {
		d_gameMap = p_gameMap;
	}

	/**
	 * function to set the phases of the game like editing phase, loading phase,
	 * gamesetup and gameplay phase
	 * 
	 * @param p_phase the name of the phase to be set
	 */
	public void setPhase(Phase p_phase) {
		d_phase = p_phase;
	}

	/**
	 * Function to support deployment of armies to countries
	 * 
	 * @param p_splittedCommand the command that has been splitted into multiple
	 *                          parts for further processing
	 * @return the result of the deploy command
	 */
	public String deploy(String[] p_splittedCommand) {
		return d_phase.deploy(p_splittedCommand);
	}

	/**
	 * Function to support advance of armies from player country to enemy country or
	 * own country
	 * 
	 * @param p_splittedCommand the command that has been splitted into multiple
	 *                          parts for further processing
	 * @return the result of the advance command
	 */
	public String advance(String[] p_splittedCommand) {
		return d_phase.deploy(p_splittedCommand);
	}

	/**
	 * Function to support airlift of armies to player's own countries
	 * 
	 * @param p_splittedCommand the command that has been splitted into multiple
	 *                          parts for further processing
	 * @return the result of the airlift command
	 */
	public String airlift(String[] p_splittedCommand) {
		return d_phase.deploy(p_splittedCommand);
	}

	/**
	 * Function to support bombing of the country of enemy player
	 * 
	 * @param p_splittedCommand the command that has been splitted into multiple
	 *                          parts for further processing
	 * @return the result of the bomb command
	 */
	public String bomb(String[] p_splittedCommand) {
		return d_phase.deploy(p_splittedCommand);
	}

	/**
	 * Function to support blockade card that blocks player own country
	 * 
	 * @param p_splittedCommand the command that has been splitted into multiple
	 *                          parts for further processing
	 * @return the result of the blockade command
	 */
	public String blockade(String[] p_splittedCommand) {
		return d_phase.deploy(p_splittedCommand);
	}

	/**
	 * Function to establish diplomacy among players
	 * 
	 * @param p_splittedCommand the command that has been splitted into multiple
	 *                          parts for further processing
	 * @return the result of the diplomacy command
	 */
	public String diplomacy(String[] p_splittedCommand) {
		return d_phase.deploy(p_splittedCommand);
	}

	/**
	 * function to support editing of map and enter edit phase
	 * 
	 * @param p_splittedCommand the command that has been splitted into multiple
	 *                          parts for further processing
	 * @return the result of the editmap command depending upon the syntax of
	 *         command provided
	 */
	public String editMap(String[] p_splittedCommand) {
		if (p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		if (p_splittedCommand[1].split("\\.").length <= 1) {
			return "File extension should be .map";
		}
		if (!"map".equals(p_splittedCommand[1].split("\\.")[1])) {
			return "File extension should be .map";
		}
		return d_phase.editMap(p_splittedCommand[1]);
	}

	/**
	 * function to support adding and removing of players to the game map
	 * 
	 * @param p_splittedCommand the command that has been splitted into multiple
	 *                          parts for further processing
	 * @return the result of the gameplayer command depending upon the syntax of
	 *         command provided
	 */
	public String gamePlayer(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int l_i = 1;
		if (p_splittedCommand.length < 3) {
			return "Please enter valid command. Command is \"gameplayer -add playerName -remove playerName\".";
		}

		int l_addRemoveCount = 0;
		int l_argsPerCmd = 2;

		for (int l_index = 1; l_index < p_splittedCommand.length; l_index++) {
			if ("-add".equals(p_splittedCommand[l_index]) || "-remove".equals(p_splittedCommand[l_index])) {
				l_addRemoveCount++;
			}
		}

		if ((p_splittedCommand.length - 1) != (l_addRemoveCount * 2)) {
			return "Number of arguments does not match with the add and remove command. Command is: \"gameplayer -add playername -remove playername\".";
		}

		int l_validAddRemovePlacement = 1;
		while (l_validAddRemovePlacement < p_splittedCommand.length) {
			if (!"-add".equals(p_splittedCommand[l_validAddRemovePlacement])
					&& !"-remove".equals(p_splittedCommand[l_validAddRemovePlacement])) {
				return "Misplacement of -add and -remove keyword w.r.t number of arguments. Command is \"gameplayer -add playername -remove playername\". Use -add and -remove as per your need.";
			}
			l_validAddRemovePlacement += l_argsPerCmd;
		}

		try {
			while (l_i < p_splittedCommand.length) {
				if ("-add".equals(p_splittedCommand[l_i])) {
					l_commandParts = new String[3];
					l_commandParts[0] = p_splittedCommand[l_i];
					l_commandParts[1] = p_splittedCommand[l_i + 1];
					if (!"".equals(l_result)) {
						l_result += "\n";
					}
					l_result += d_phase.gamePlayer(l_commandParts);
					l_i = l_i + 2;
				} else if ("-remove".equals(p_splittedCommand[l_i])) {
					l_commandParts = new String[2];
					l_commandParts[0] = p_splittedCommand[l_i];
					l_commandParts[1] = p_splittedCommand[l_i + 1];
					if (!"".equals(l_result)) {
						l_result += "\n";
					}
					l_result += d_phase.gamePlayer(l_commandParts);
					l_i = l_i + 2;
				} else {
					if (!"".equals(l_result)) {
						l_result += "\n";
					}
					l_result += "Command needs to have -add or -remove.";
					l_i++;
				}
			}
		} catch (Exception p_e) {
			System.out.println("Valid command not entered.");
		}
		return l_result;
	}

	/**
	 * function allowing user to display the current state of the map
	 * 
	 * @return the map with all the information of the current state
	 */
	public String showmap() {
		return d_phase.showMap();
	}

	/**
	 * function to support adding and removing of continents to the map
	 * 
	 * @param p_splittedCommand the command that has been splitted into multiple
	 *                          parts for further processing
	 * @return the result of the editContinent command depending upon the syntax of
	 *         command provided
	 */
	public String editContinent(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int l_i = 1;
		if (p_splittedCommand.length < 3) {
			return "Please enter valid command. Command is: \"editcontinent -add continentId controlValue -remove continentId\"";
		}

		int l_addCount = 0;
		int l_removeCount = 0;

		for (int l_index = 1; l_index < p_splittedCommand.length; l_index++) {
			if ("-add".equals(p_splittedCommand[l_index])) {
				l_addCount++;
			}
			if ("-remove".equals(p_splittedCommand[l_index])) {
				l_removeCount++;
			}
		}

		if ((p_splittedCommand.length - 1) != ((l_addCount * 3) + (l_removeCount * 2))) {
			return "Number of arguments does not match with the add and remove command. Command is: \"editcontinent -add continentId controlValue -remove continentId\".";
		}

		int l_validAddRemovePlacement = 1;
		while (l_validAddRemovePlacement < p_splittedCommand.length) {
			if (!"-add".equals(p_splittedCommand[l_validAddRemovePlacement])
					&& !"-remove".equals(p_splittedCommand[l_validAddRemovePlacement])) {
				return "Misplacement of -add and -remove keyword w.r.t number of arguments. Command is: \"editcontinent -add continentId controlValue -remove continentId\". Use -add and -remove as per your need.";
			}
			if ("-add".equals(p_splittedCommand[l_validAddRemovePlacement])) {
				l_validAddRemovePlacement += 3;
			} else {
				l_validAddRemovePlacement += 2;
			}
		}

		l_validAddRemovePlacement = 1;
		while (l_validAddRemovePlacement < p_splittedCommand.length) {
			if ("-add".equals(p_splittedCommand[l_validAddRemovePlacement])) {
				if (!(isNumeric(p_splittedCommand[l_validAddRemovePlacement + 1])
						&& isNumeric(p_splittedCommand[l_validAddRemovePlacement + 2]))) {
					return "You can only use Integer to represent continentId and controlValue for adding continent. Add Continent command is \"editneighbor -add continentId controlValue\".";
				}
				l_validAddRemovePlacement += 3;
			} else {
				if (!isNumeric(p_splittedCommand[l_validAddRemovePlacement + 1])) {
					return "You can only use Integer to represent continentId for removing continent. Remove Continent command is \"editneighbor -remove continentId\".";
				}
				l_validAddRemovePlacement += 2;
			}
		}

		try {
			while (l_i < p_splittedCommand.length) {
				if ("-add".equals(p_splittedCommand[l_i])) {
					l_commandParts = new String[3];
					l_commandParts[0] = p_splittedCommand[l_i];
					l_commandParts[1] = p_splittedCommand[l_i + 1];
					l_commandParts[2] = p_splittedCommand[l_i + 2];
					if (!"".equals(l_result)) {
						l_result += "\n";
					}
					l_result += d_phase.editContinent(l_commandParts);
					l_i = l_i + 3;
				} else if ("-remove".equals(p_splittedCommand[l_i])) {
					l_commandParts = new String[2];
					l_commandParts[0] = p_splittedCommand[l_i];
					l_commandParts[1] = p_splittedCommand[l_i + 1];
					if (!"".equals(l_result)) {
						l_result += "\n";
					}
					l_result += d_phase.editContinent(l_commandParts);
					l_i = l_i + 2;
				} else {
					if (!"".equals(l_result)) {
						l_result += "\n";
					}
					l_result += "Command needs to have -add or -remove.";
					l_i++;
				}
			}
		} catch (Exception p_e) {
			System.out.println("valid command not entered.");
		}
		return l_result;
	}

	/**
	 * function to support adding and removing of countries to the map
	 * 
	 * @param p_splittedCommand the command that has been splitted into multiple
	 *                          parts for further processing
	 * @return the result of the editCountry command depending upon the syntax of
	 *         command provided
	 */
	public String editCountry(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int l_i = 1;
		if (p_splittedCommand.length < 3) {
			return "Please enter valid command. Command is: \"editcountry -add countryId continentId -remove countryId\".";
		}

		int l_addCount = 0;
		int l_removeCount = 0;

		for (int l_index = 1; l_index < p_splittedCommand.length; l_index++) {
			if ("-add".equals(p_splittedCommand[l_index])) {
				l_addCount++;
			}
			if ("-remove".equals(p_splittedCommand[l_index])) {
				l_removeCount++;
			}
		}

		if ((p_splittedCommand.length - 1) != ((l_addCount * 3) + (l_removeCount * 2))) {
			return "Number of arguments does not match with the add and remove command. Command is: \"editcountry -add countryId continentId -remove countryId\".";
		}

		int l_validAddRemovePlacement = 1;
		while (l_validAddRemovePlacement < p_splittedCommand.length) {
			if (!"-add".equals(p_splittedCommand[l_validAddRemovePlacement])
					&& !"-remove".equals(p_splittedCommand[l_validAddRemovePlacement])) {
				return "Misplacement of -add and -remove keyword w.r.t number of arguments. Command is \"editcountry -add countryId continentId -remove countryId\". Use -add and -remove as per your need.";
			}
			if ("-add".equals(p_splittedCommand[l_validAddRemovePlacement])) {
				l_validAddRemovePlacement += 3;
			} else {
				l_validAddRemovePlacement += 2;
			}
		}

		l_validAddRemovePlacement = 1;
		while (l_validAddRemovePlacement < p_splittedCommand.length) {
			if ("-add".equals(p_splittedCommand[l_validAddRemovePlacement])) {
				if (!(isNumeric(p_splittedCommand[l_validAddRemovePlacement + 1])
						&& isNumeric(p_splittedCommand[l_validAddRemovePlacement + 2]))) {
					return "You can only use Integer to represent countryId and continentId for adding Country. Add Country command is \"editcountry -add countryId continentId\".";
				}
				l_validAddRemovePlacement += 3;
			} else {
				if (!isNumeric(p_splittedCommand[l_validAddRemovePlacement + 1])) {
					return "You can only use Integer to represent countryId for removing Country. Remove Country command is \"editcountry -remove countryId\".";
				}
				l_validAddRemovePlacement += 2;
			}
		}

		while (l_i < p_splittedCommand.length) {
			if ("-add".equals(p_splittedCommand[l_i])) {
				l_commandParts = new String[3];
				l_commandParts[0] = p_splittedCommand[l_i];
				l_commandParts[1] = p_splittedCommand[l_i + 1];
				l_commandParts[2] = p_splittedCommand[l_i + 2];
				if (!"".equals(l_result)) {
					l_result += "\n";
				}
				l_result += d_phase.editCountry(l_commandParts);
				l_i = l_i + 3;
			} else if ("-remove".equals(p_splittedCommand[l_i])) {
				l_commandParts = new String[2];
				l_commandParts[0] = p_splittedCommand[l_i];
				l_commandParts[1] = p_splittedCommand[l_i + 1];
				if (!"".equals(l_result)) {
					l_result += "\n";
				}
				l_result += d_phase.editCountry(l_commandParts);
				l_i = l_i + 2;
			} else {
				if (!"".equals(l_result)) {
					l_result += "\n";
				}
				l_result += "Command needs to have -add or -remove.";
				l_i++;
			}
		}
		return l_result;
	}

	/**
	 * function to support adding and removing of neighboring countries to the map
	 * 
	 * @param p_splittedCommand the command that has been splitted into multiple
	 *                          parts for further processing
	 * @return the result of the editNeighbor command depending upon the syntax of
	 *         command provided
	 */
	public String editNeighbor(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int l_i = 1;
		if (p_splittedCommand.length < 4) {
			return "Please enter valid command. Command is \"editneighbor -add countryId neighborCountryId -remove countryId neighborCountryId\".";
		}

		int l_addCount = 0;
		int l_removeCount = 0;

		for (int l_index = 1; l_index < p_splittedCommand.length; l_index++) {
			if ("-add".equals(p_splittedCommand[l_index])) {
				l_addCount++;
			}
			if ("-remove".equals(p_splittedCommand[l_index])) {
				l_removeCount++;
			}
		}

		if ((p_splittedCommand.length - 1) != ((l_addCount * 3) + (l_removeCount * 3))) {
			return "Number of arguments does not match with the add and remove command. Command is \"editneighbor -add countryId neighborCountryId -remove countryId neighborCountryId\".";
		}

		int l_validAddRemovePlacement = 1;
		while (l_validAddRemovePlacement < p_splittedCommand.length) {
			if (!"-add".equals(p_splittedCommand[l_validAddRemovePlacement])
					&& !"-remove".equals(p_splittedCommand[l_validAddRemovePlacement])) {
				return "Misplacement of -add and -remove keyword w.r.t number of arguments. Command is \"editneighbor -add countryId neighborCountryId -remove countryId neighborCountryId\". Use -add and -remove as per your need.";
			}
			if ("-add".equals(p_splittedCommand[l_validAddRemovePlacement])) {
				l_validAddRemovePlacement += 3;
			} else {
				l_validAddRemovePlacement += 3;
			}
		}

		l_validAddRemovePlacement = 1;
		while (l_validAddRemovePlacement < p_splittedCommand.length) {
			if ("-add".equals(p_splittedCommand[l_validAddRemovePlacement])) {
				if (!(isNumeric(p_splittedCommand[l_validAddRemovePlacement + 1])
						&& isNumeric(p_splittedCommand[l_validAddRemovePlacement + 2]))) {
					return "You can only use Integer to represent continentId and neighborCountryId for adding Neighbor. Add Neighbor command is \"editneighbor -add countryId neighborCountryId\".";
				}
				l_validAddRemovePlacement += 3;
			} else {
				if (!(isNumeric(p_splittedCommand[l_validAddRemovePlacement + 1])
						&& isNumeric(p_splittedCommand[l_validAddRemovePlacement + 2]))) {
					return "You can only use Integer to represent continentId and neighborCountryId for removing Neighbor. Remove Neighbor command is \"editneighbor -remove countryId neighborCountryId\".";
				}
				l_validAddRemovePlacement += 3;
			}
		}

		while (l_i < p_splittedCommand.length) {
			if ("-add".equals(p_splittedCommand[l_i])) {
				l_commandParts = new String[3];
				l_commandParts[0] = p_splittedCommand[l_i];
				l_commandParts[1] = p_splittedCommand[l_i + 1];
				l_commandParts[2] = p_splittedCommand[l_i + 2];
				if (!"".equals(l_result)) {
					l_result += "\n";
				}
				l_result += d_phase.editNeighbor(l_commandParts);
				l_i = l_i + 3;
			} else if ("-remove".equals(p_splittedCommand[l_i])) {
				l_commandParts = new String[3];
				l_commandParts[0] = p_splittedCommand[l_i];
				l_commandParts[1] = p_splittedCommand[l_i + 1];
				l_commandParts[2] = p_splittedCommand[l_i + 2];
				if (!"".equals(l_result)) {
					l_result += "\n";
				}
				l_result += d_phase.editNeighbor(l_commandParts);
				l_i = l_i + 3;
			} else {
				if (!"".equals(l_result)) {
					l_result += "\n";
				}
				l_result += "Command needs to have -add or -remove.";
				l_i++;
			}
		}
		return l_result;
	}

	/**
	 * function to allow saving of map to the directory
	 * 
	 * @param p_splittedCommand the command that has been splitted into multiple
	 *                          parts for further processing
	 * @return the result of executing the saveMap command
	 */
	public String saveMap(String[] p_splittedCommand) {
		if (p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		if (p_splittedCommand[1].split("\\.").length <= 1) {
			return "File extension should be .map";
		}
		if (!"map".equals(p_splittedCommand[1].split("\\.")[1])) {
			return "File extension should be .map";
		}
		return d_phase.saveMap(p_splittedCommand[1]);
	}

	/**
	 * function to allow validation of map to check connectivity of the graph of the
	 * map
	 * 
	 * @param p_splittedCommand the command that has been splitted into multiple
	 *                          parts for further processing
	 * @return the result of map validation
	 */
	public String validateMap(String[] p_splittedCommand) {
		if (p_splittedCommand.length > 1) {
			return "Please enter valid command";
		}
		return d_phase.validateMap();
	}

	/**
	 * function to support loading of map from the directory to enter the gameplay
	 * phase
	 * 
	 * @param p_splittedCommand the command that has been splitted into multiple
	 *                          parts for further processing
	 * @return the result of loading of map
	 */
	public String loadMap(String[] p_splittedCommand) {
		try {
			if (d_phase instanceof EditPhase) {
				setPhase(new PreLoad(this));
			}
			if (p_splittedCommand.length < 2) {
				return "Please enter valid command";
			}
			if (p_splittedCommand[1].split("\\.").length <= 1) {
				return "File extension should be .map";
			}
			if (!"map".equals(p_splittedCommand[1].split("\\.")[1])) {
				return "File extension should be .map";
			}
			return d_phase.loadMap(p_splittedCommand[1]);
		} catch (Exception p_e) {
			return "Enter valid loadmap command with valid map file. Command is \"loadmap fileName.map\"";
		}
	}

	/**
	 * function to assign countries to the players present in the game
	 * 
	 * @param p_splittedCommand the command that has been splitted into multiple
	 *                          parts for further processing
	 * @return the result of assigning countries to the players
	 */
	public String assignCountries(String[] p_splittedCommand) {
		if (p_splittedCommand.length > 1) {
			return String.format("Invalid Command");
		}
		return d_phase.assignCountries();
	}

	/**
	 * function to start tournament, user provides list of maps, players strategies,
	 * number of games per map and the maximum turns per game.
	 * 
	 * @param p_splittedCommand splitted command that contains list of maps, players
	 *                          strategies, number of games per map and the maximum
	 *                          turns per game.
	 * @return String which contains winners of each games played on each maps.
	 */
	public String tournament(String[] p_splittedCommand) {
		ArrayList<String> l_playerStrategy = new ArrayList<>(Arrays.asList("Random", "Aggresive", "Benevolent", "Cheater"));
		int l_i = 2;
		ArrayList<String> l_maps = new ArrayList<>();
		ArrayList<String> l_players = new ArrayList<>();
		int l_numGames;
		int l_numTurns;
		while (!p_splittedCommand[l_i].equals("-P")) {
			l_maps.add(p_splittedCommand[l_i]);
			++l_i;
		}
		++l_i;
		while (!p_splittedCommand[l_i].equals("-G")) {
			if(!l_playerStrategy.contains(p_splittedCommand[l_i])) {
				return "Only strategies Random, Aggresive, Benevolent and Cheater are allowed.";
			}
			l_players.add(p_splittedCommand[l_i]);
			++l_i;
		}
		if (l_players.size() > new HashSet<String>(l_players).size()) {
			return "Duplicate players not permitted.";
		}
		++l_i;
		l_numGames = Integer.parseInt(p_splittedCommand[l_i]);
		++l_i;
		++l_i;
		l_numTurns = Integer.parseInt(p_splittedCommand[l_i]);
		setPhase(new PreLoad(this));
		return d_phase.tournament(l_maps, l_players, l_numGames, l_numTurns);
	}

	/**
	 * function to process the entire command provided by the user that will be
	 * splitted for further processing
	 * 
	 * @param p_userCommand the entire line that acts as the command
	 */
	public void setUserCommand(UserCommand p_userCommand) {
		d_userCommand = p_userCommand;
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

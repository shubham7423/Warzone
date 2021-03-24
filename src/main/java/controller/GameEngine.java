package controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import controller.state.Phase;
import controller.state.edit.EditPhase;
import controller.state.edit.PreEdit;
import controller.state.gamephase.gamesetup.PreLoad;
import entities.GameMap;
import entities.Player;
import entities.orders.Deploy;

/**
 * Game Engine class is the starting point of the game where commands are processed and extracted to support the 
 * functionalities provided by the commands.
 */
public class GameEngine {
	private Phase d_phase;
	private GameMap d_gameMap = new GameMap();
	private Queue<Player> d_playersOrder = new LinkedList<>();
	public HashMap<String, Player> d_players = new HashMap<>();
	public ArrayList<String> d_playerName = new ArrayList<>();
	public UserCommand d_userCommand;
	public LogEntryBuffer d_logEntryBuffer;;
	private LogWriter d_logWriter;
	public Player d_neutralPlayer;
	
	public GameEngine() {
		d_logEntryBuffer = new LogEntryBuffer();
		d_logWriter = new LogWriter(d_logEntryBuffer);
		d_neutralPlayer = new Player("neutralPlayer#1");
	}
	
	/**
	 * function to get the order size of the player
	 * @return an integer representing the size of the orders, the players has
	 */
	public int getPlayeraOrderSize() {
		return d_playersOrder.size();
	}
	
	/**
	 * function to add order to the order queue of the player
	 * @param p_player represents the player whose order is to be added to the queue
	 */
	public void addPlayerOrder(Player p_player) {
		d_playersOrder.add(p_player);
	}
	
	/***
	 * function to remove and order from the order queue of the player
	 * @return removal of order from the queue of the player
	 */
	public Player getPlayerOrder() {
		return d_playersOrder.remove();
	}
	
	/**
	 * function to obtain the phase in which we are present in the game
	 * @return the phase we currently are in the game
	 */
	public Phase getPhase() {
		return d_phase;
	}
	
	/**
	 * function to return the current state/situation of the game map 
	 * @return the current game map 
	 */
	public GameMap getGameMap() {
		return d_gameMap;
	}
	
	/**
	 * function to set the game map according to the parameter provided
	 * @param p_gameMap the game map state we want to set
	 */
	public void setGameMap(GameMap p_gameMap) {
		d_gameMap = p_gameMap;
	}
	
	/**
	 * function to set the phases of the game like editing phase, loading phase, gamesetup and gameplay phase
	 * @param p_phase the name of the phase to be set
	 */
	public void setPhase(Phase p_phase) { 
		d_phase = p_phase;
	}
	
	/**
	 * a function containing switch case to identify which command has been entered by the user for further processing
	 * @param p_splittedCommand the command that has been splitted into multiple parts for processing
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

		default:
			l_result = "Command not found";
		}

		return l_result;
	}
	
	/**
	 * Function to support deployment of armies to countries
	 * @param p_splittedCommand the command that has been splitted into multiple parts for further processing
	 * @return the result of the deploy command
	 */
	public String deploy(String[] p_splittedCommand) {
		return d_phase.deploy(p_splittedCommand);
	}
	
	public String advance(String[] p_splittedCommand) {
		return d_phase.deploy(p_splittedCommand);
	}
	
	public String airlift(String[] p_splittedCommand) {
		return d_phase.deploy(p_splittedCommand);
	}
	
	public String bomb(String[] p_splittedCommand) {
		return d_phase.deploy(p_splittedCommand);
	}
	
	public String blockade(String[] p_splittedCommand) {
		return d_phase.deploy(p_splittedCommand);
	}
	
	public String diplomacy(String[] p_splittedCommand) {
		return d_phase.deploy(p_splittedCommand);
	}
	
	/**
	 * function to support editing of map and enter edit phase
	 * @param p_splittedCommand the command that has been splitted into multiple parts for further processing
	 * @return the result of the editmap command depending upon the syntax of command provided
	 */
	public String editMap(String[] p_splittedCommand) {
		if (p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		if (p_splittedCommand[1].split("\\.").length <= 1) {
			return "File extension should be .map";
		}
		if (!p_splittedCommand[1].split("\\.")[1].equals("map")) {
			return "File extension should be .map";
		}
		return d_phase.editMap(p_splittedCommand[1]);
	}
	
	/**
	 * function to support adding and removing of players to the game map
	 * @param p_splittedCommand the command that has been splitted into multiple parts for further processing
	 * @return the result of the gameplayer command depending upon the syntax of command provided
	 */
	public String gamePlayer(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int l_i = 1;
		if (p_splittedCommand.length < 3) {
			return "Please enter valid command. Command is \"gameplayer -add playername -remove playername\", irrespective of multiple addition and removal order.";
		}

		int l_addRemoveCount = 0;
		int l_argsPerCmd = 2;
		
		for(int l_index = 1; l_index<p_splittedCommand.length; l_index++) {
			if(p_splittedCommand[l_index].equals("-add") || p_splittedCommand[l_index].equals("-remove")){
				l_addRemoveCount ++;
			}
		}
		
		if((p_splittedCommand.length-1)%l_argsPerCmd != 0) {
			return "Number of arguments does not match with the add and remove command."; 
		}
		
		int l_validAddRemovePlacement = 1;
		while(l_validAddRemovePlacement<p_splittedCommand.length) {
			if(!p_splittedCommand[l_validAddRemovePlacement].equals("-add") && !p_splittedCommand[l_validAddRemovePlacement].equals("-remove")) {
				return "Misplacement of -add and -remove w.r.t number of arguments.";
			}
			l_validAddRemovePlacement += l_argsPerCmd;
		}
		System.out.println("Done.1");
				
		try {
			while (l_i < p_splittedCommand.length) {	
				if (p_splittedCommand[l_i].equals("-add")) {
					l_commandParts = new String[3];
					l_commandParts[0] = p_splittedCommand[l_i];
					l_commandParts[1] = p_splittedCommand[l_i + 1];
					if (!l_result.equals("")) {
						l_result += "\n";
					}
					l_result += d_phase.gamePlayer(l_commandParts);
					l_i = l_i + 2;
				} else if (p_splittedCommand[l_i].equals("-remove")) {
					l_commandParts = new String[2];
					l_commandParts[0] = p_splittedCommand[l_i];
					l_commandParts[1] = p_splittedCommand[l_i + 1];
					if (!l_result.equals("")) {
						l_result += "\n";
					}
					l_result += d_phase.gamePlayer(l_commandParts);
					l_i = l_i + 2;
				} else {
					if (!l_result.equals("")) {
						l_result += "\n";
					}
					l_result += "Command needs to have -add or -remove.";
					l_i++;
				}
			}
		} catch (Exception p_e) {
			System.out.println("valid command not entered");
		}
		return l_result;
	}
	
	/**
	 * function allowing user to display the current state of the map 
	 * @return the map with all the information of the current state
	 */
	public String showmap() {
		return d_phase.showMap();
	}
	
	/**
	 * function to support adding and removing of continents to the map
	 * @param p_splittedCommand the command that has been splitted into multiple parts for further processing
	 * @return the result of the editContinent command depending upon the syntax of command provided
	 */
	public String editContinent(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int l_i = 1;
		if (p_splittedCommand.length < 4) {
			return "Please enter valid command";
		}
		try {			
			while (l_i < p_splittedCommand.length) {
				if (p_splittedCommand[l_i].equals("-add")) {
					l_commandParts = new String[3];
					l_commandParts[0] = p_splittedCommand[l_i];
					l_commandParts[1] = p_splittedCommand[l_i + 1];
					l_commandParts[2] = p_splittedCommand[l_i + 2];
					if (!l_result.equals("")) {
						l_result += "\n";
					}
					l_result += d_phase.editContinent(l_commandParts);
					l_i = l_i + 3;
				} else if (p_splittedCommand[l_i].equals("-remove")) {
					l_commandParts = new String[2];
					l_commandParts[0] = p_splittedCommand[l_i];
					l_commandParts[1] = p_splittedCommand[l_i + 1];
					if (!l_result.equals("")) {
						l_result += "\n";
					}
					l_result += d_phase.editContinent(l_commandParts);
					l_i = l_i + 2;
				} else {
					if (!l_result.equals("")) {
						l_result += "\n";
					}
					l_result += "Command needs to have -add or -remove.";
					l_i++;
				}
			}
		} catch (Exception p_e) {
			System.out.println("valid command not entered");
		}
		return l_result;
	}
	
	/**
	 * function to support adding and removing of countries to the map
	 * @param p_splittedCommand the command that has been splitted into multiple parts for further processing
	 * @return the result of the editCountry command depending upon the syntax of command provided
	 */
	public String editCountry(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int l_i = 1;
		if (p_splittedCommand.length < 4) {
			return "Please enter valid command";
		}
		

		int l_addCount = 0;
		int l_removeCount = 0;
		
		for(int l_index = 1; l_index<p_splittedCommand.length; l_index++) {
			if(p_splittedCommand[l_index].equals("-add")){
				l_addCount ++;
			}
			if(p_splittedCommand[l_index].equals("-remove")){
				l_removeCount ++;
			}
		}
		
		if((p_splittedCommand.length-1) != ((l_addCount*3) + (l_removeCount*2))) {
			return "Number of arguments does not match with the add and remove command.";
		}
		
		int l_validAddRemovePlacement = 1;
		while(l_validAddRemovePlacement<p_splittedCommand.length) {
			if(!p_splittedCommand[l_validAddRemovePlacement].equals("-add") && !p_splittedCommand[l_validAddRemovePlacement].equals("-remove")) {
				return "Misplacement of -add and -remove w.r.t number of arguments.";
			}
			if(p_splittedCommand[l_validAddRemovePlacement].equals("-add")) {
				l_validAddRemovePlacement += 3;
			} else {
				l_validAddRemovePlacement += 2;
			}
		}
		
		l_validAddRemovePlacement = 1;
		while(l_validAddRemovePlacement<p_splittedCommand.length) {
			if(p_splittedCommand[l_validAddRemovePlacement].equals("-add")) {
				if(!(isNumeric( p_splittedCommand[l_validAddRemovePlacement+1]) && isNumeric( p_splittedCommand[l_validAddRemovePlacement+2]))) {
					return "You can only use String to represent value. change this to full command.";
				}
				l_validAddRemovePlacement += 3;
			} else {
				if(!isNumeric( p_splittedCommand[l_validAddRemovePlacement+1])) {
					return "You can only use String to represent value. change this to full command.";
				}
				l_validAddRemovePlacement += 2;
			}
		}
		System.out.println("Done.1");

		while (l_i < p_splittedCommand.length) {
			if (p_splittedCommand[l_i].equals("-add")) {
				l_commandParts = new String[3];
				l_commandParts[0] = p_splittedCommand[l_i];
				l_commandParts[1] = p_splittedCommand[l_i + 1];
				l_commandParts[2] = p_splittedCommand[l_i + 2];
				if (!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_phase.editCountry(l_commandParts);
				l_i = l_i + 3;
			} else if (p_splittedCommand[l_i].equals("-remove")) {
				l_commandParts = new String[2];
				l_commandParts[0] = p_splittedCommand[l_i];
				l_commandParts[1] = p_splittedCommand[l_i + 1];
				if (!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_phase.editCountry(l_commandParts);
				l_i = l_i + 2;
			} else {
				if (!l_result.equals("")) {
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
	 * @param p_splittedCommand the command that has been splitted into multiple parts for further processing
	 * @return the result of the editNeighbor command depending upon the syntax of command provided
	 */
	public String editNeighbor(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int l_i = 1;
		if (p_splittedCommand.length < 4) {
			return "Please enter valid command";
		}
		while (l_i < p_splittedCommand.length){
			if (p_splittedCommand[l_i].equals("-add")) {
				l_commandParts = new String[3];
				l_commandParts[0] = p_splittedCommand[l_i];
				l_commandParts[1] = p_splittedCommand[l_i + 1];
				l_commandParts[2] = p_splittedCommand[l_i + 2];
				if (!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_phase.editNeighbor(l_commandParts);
				l_i = l_i + 3;
			} else if (p_splittedCommand[l_i].equals("-remove")) {
				l_commandParts = new String[3];
				l_commandParts[0] = p_splittedCommand[l_i];
				l_commandParts[1] = p_splittedCommand[l_i + 1];
				l_commandParts[2] = p_splittedCommand[l_i + 2];
				if (!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_phase.editNeighbor(l_commandParts);
				l_i = l_i + 3;
			} else {
				if (!l_result.equals("")) {
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
	 * @param p_splittedCommand the command that has been splitted into multiple parts for further processing
	 * @return the result of executing the saveMap command
	 */
	public String saveMap(String[] p_splittedCommand) {
		if (p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		if (p_splittedCommand[1].split("\\.").length <= 1) {
			return "File extension should be .map";
		}
		if (!p_splittedCommand[1].split("\\.")[1].equals("map")) {
			return "File extension should be .map";
		}
		return d_phase.saveMap(p_splittedCommand[1]);
	}
	
	/**
	 * function to allow validation of map to to check connectivity of the graph of the map
	 * @param p_splittedCommand the command that has been splitted into multiple parts for further processing
	 * @return the result of map validation
	 */
	public String validateMap(String[] p_splittedCommand) {
		if (p_splittedCommand.length > 1) {
			return "Please enter valid command";
		}
		return d_phase.validateMap();
	}
	
	/**
	 * function to support loading of map from the directory to enter the gameplay phase
	 * @param p_splittedCommand the command that has been splitted into multiple parts for further processing
	 * @return the result of loading of map
	 */
	public String loadMap(String[] p_splittedCommand) {
		if(d_phase instanceof EditPhase) {
			setPhase(new PreLoad(this));
		}
		if (p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		if (p_splittedCommand[1].split("\\.").length <= 1) {
			return "File extension should be .map";
		}
		if (!p_splittedCommand[1].split("\\.")[1].equals("map")) {
			return "File extension should be .map";
		}
		return d_phase.loadMap(p_splittedCommand[1]);
	}
	
	/**
	 * function to assign countries to the players present in the game
	 * @param p_splittedCommand the command that has been splitted into multiple parts for further processing
	 * @return the result of assigning countries to the players
	 */
	public String assignCountries(String[] p_splittedCommand) {
//		try {
			if (p_splittedCommand.length > 1) {
				return String.format("Invalid Command");
			}
			return d_phase.assignCountries();
//		}
//		finally {
//			d_phase.assignArmies();
//		}
		
//		if (p_splittedCommand.length > 1) {
//			return String.format("Invalid Command");
//		}
//		String l_result =  d_phase.assignCountries();
//		d_phase.assignArmies();
//		return l_result;
	}
	
//	public void getCommand() {
//		UserCommand l_userCommand = new UserCommand();
//		GameStarter l_gameStarter = new GameStarter();
//		l_gameStarter.setPhase(new PreEdit(l_gameStarter));
//		System.out.println("Welcome to Warzone");
//		while (true) {
//			String[] l_splittedCommandString = l_userCommand.getCommand();
//			if (l_splittedCommandString[0].equals("exit()")) {
//				break;
//			}
//			System.out.println(l_gameStarter.executeCommand(l_splittedCommandString));
//		}
//	}
	
	/**
	 * function to process the entire command provided by the user that will be splitted for further processing
	 * @param p_userCommand the entire line that acts as the command
	 */
	public void setUserCommand(UserCommand p_userCommand) {
		d_userCommand = p_userCommand;
//		System.out.println(d_userCommand);
	}
	
	/**
	 * function that launches the game
	 */
	public void start() {
//		setUserCommand(new UserCommand());
		System.out.println("Welcome to Warzone");
		this.d_logEntryBuffer.setString("Game Started");
		UserCommand l_UserCommand = new UserCommand();
		while (true) {
//			String[] l_splittedCommandString = l_userCommand.getCommand(l_gameStarter);
//			if (l_splittedCommandString[0].equals("exit()")) {
//				break;
//			}
//			System.out.println(a);
			String l_commandOpt = l_UserCommand.getCommand();
			if(l_commandOpt.equals("exit()")) {
				break;
			}
			System.out.println(l_commandOpt);
		}
		System.out.print("\nThank you for playing Warzone :)");
		l_UserCommand.l_scannerScanner.close();
	}

	/**
	 * This function is used to check if a string can be converted to integer or not.
	 * @param p_str represents the string to be casted to Integer value.
	 * @return true if the string can be parsed to an Integer.
	 */
	public static boolean isNumeric(String p_str) { 
		try {  
			Integer.parseInt(p_str);  
			return true;
		} catch(NumberFormatException p_e){  
			return false;
		} catch(Exception p_e) {
			return false;
		}
	}
	
	public static void main(String[] args) {
//		Commands l_commands = new Commands();
//		UserCommand l_userCommand = new UserCommand();
//		GameStarter l_gameStarter = new GameStarter();
//		d_userCommand = new UserCommand();
////		l_gameStarter.setPhase(new PreEdit(l_gameStarter));
//		System.out.println("Welcome to Warzone");
//		while (true) {
////			String[] l_splittedCommandString = l_userCommand.getCommand(l_gameStarter);
////			if (l_splittedCommandString[0].equals("exit()")) {
////				break;
////			}
//			String l_commandOpt = l_gameStarter.d_userCommand.getCommand();
//			if(l_commandOpt.equals("exit()")) {
//				break;
//			}
//			System.out.println(l_commandOpt);
//		}
//		System.out.print("\nThank you for playing Warzone :)");
//		l_gameStarter.d_userCommand.l_scannerScanner.close();
		new GameEngine().start();
	}
}

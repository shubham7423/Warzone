package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


import org.apache.commons.digester.SetNestedPropertiesRule;


import entities.Continent;
import entities.Country;
import entities.GameMap;
import entities.Player;

/**
 * 
 * Main file to start game
 *
 */
public class GameStarter {
	
	GameMap d_gameMap = new GameMap();
	boolean is_loadedMap = false, is_editMap = false;
	private HashMap<String, Player> d_players = new HashMap<>();
	private ArrayList<String> d_playerName =  new ArrayList<>(); 
	int d_currentPlayer = 0; 
	
	/**
	 * method to edit map, it creates new file when specified file name does not exists else loads existing map file.
	 * @param p_fileName file name
	 * @return String which states completion of the operation
	 */
	public String editMap(String p_fileName) {
		String l_result;
		if (!is_editMap && !is_loadedMap) {
			this.loadMap(p_fileName);
			if(!Files.exists(Paths.get(Paths.get("").toAbsolutePath().toString() + "/maps/" + p_fileName))) {
				try {
					Files.createDirectories(Paths.get(Paths.get("").toAbsolutePath().toString() + "/maps"));
					Files.createFile(Paths.get(Paths.get("").toAbsolutePath().toString() + "/maps/" + p_fileName));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			l_result = String.format("Map \"%s\" ready for edit", p_fileName);
			is_editMap = true;
			is_loadedMap = false;
		}
		else {
			l_result = String.format("Edit map not available");
		}
		return l_result;
	}
	
	/**
	 * method to load map
	 * @param p_fileName file name of map
	 * @return loaded map(responses positive or negative)
	 */
	public String loadMap(String p_fileName) {
		String l_result;
		if(!is_editMap) {
			l_result = d_gameMap.loadMap(p_fileName);
			is_loadedMap = true;
		}
		else {
			l_result = String.format("Cannot be loaded map when map is edited");
		}
		return l_result;
	}
	
	/**
	 * Edit continents
	 * @param p_commandSplitted splitted commands
	 * @return shows whether continents are added or removed
	 */
	public String editContinent(String[] p_commandSplitted) {
		String l_result;
		if(is_editMap && !is_loadedMap) {
			if (p_commandSplitted[0].equals("-add")) {
				l_result = d_gameMap.addContinent(Integer.parseInt(p_commandSplitted[1]), Integer.parseInt(p_commandSplitted[2]));
			}
			else {
				l_result = d_gameMap.removeContinent(Integer.parseInt(p_commandSplitted[1]));
			}
		}
		else {
			l_result = String.format("Map can only be edited when file is open in edit phase");
		}
		return l_result;
	}
	
	/**
	 * edit countries
	 * @param p_commandSplitted splitted commands
	 * @return shows whether countries are added or removed with respect to their continents
	 */
	public String editCountry(String[] p_commandSplitted) {
		String l_result;
		if(is_editMap && !is_loadedMap) {
			if (p_commandSplitted[0].equals("-add")) {
				l_result = d_gameMap.addCountry(Integer.parseInt(p_commandSplitted[1]), Integer.parseInt(p_commandSplitted[2]));
			}
			else {
				l_result = d_gameMap.removeCountry(Integer.parseInt(p_commandSplitted[1]));
			}
		}
		else {
			l_result = String.format("Map can only be edited when file is open in edit phase");
		}
		return l_result;
	}
	
	/**
	 * edit neighbours
	 * @param p_commandSplitted splitted commands
	 * @return shows whether neighbours countries are added or removed
	 */
	public String editNeighbour(String[] p_commandSplitted) {
		String l_result;
		if(is_editMap && !is_loadedMap) {
			if (p_commandSplitted[0].equals("-add")) {
				l_result = d_gameMap.addNeighbour(Integer.parseInt(p_commandSplitted[1]), Integer.parseInt(p_commandSplitted[2]));
			}
			else {
				l_result = d_gameMap.removeNeighbour(Integer.parseInt(p_commandSplitted[1]), Integer.parseInt(p_commandSplitted[2]));
			}
		}
		else {
			l_result = String.format("Map can only be edited when file is open in edit phase");
		}
		return l_result;
	}
	
	/**
	 * method to save map, it creates new file with specified file name.
	 * @param p_fileName filename of map to be saved
	 * @return shows whether map is saved or not
	 */
	public String saveMap(String p_fileName) {
		String l_result;
		if(is_editMap && !is_loadedMap) {
			l_result = d_gameMap.saveMap(p_fileName);
			is_loadedMap = false;
			is_editMap = false;
		}
		else {
			l_result = String.format("Cannot save map");
		}
		return l_result;	
	}
	
	/**
	 * method to add players to the game
	 * @param p_commandSplitted splitted commands
	 * @return shows whether players are added or removed
	 */
	public String gamePlayer(String[] p_commandSplitted) {
		String l_result;
		if(!is_editMap && is_loadedMap) {
			if (p_commandSplitted[0].equals("-add")) {
				l_result = addPlayer(p_commandSplitted[1]);
			}
			else {
				l_result = removePlayer(p_commandSplitted[1]);
			}
		}
		else {
			l_result = String.format("Players cannot be added/removed in this phase");
		}
		return l_result;
	}
	
	/**
	 * add a player to the game
	 * @param p_playerName name of the player
	 * @return Positive response if player is added
	 */
	public String addPlayer(String p_playerName) {
		if(d_players.containsKey(p_playerName)) {
			return String.format("Player \"%s\" already present in game", p_playerName);
		}
		d_players.put(p_playerName, new Player(p_playerName));
		d_playerName.add(p_playerName);
		return String.format("Player \"%s\" added to map", p_playerName);
	}
	
	/**
	 * remove a player to the game
	 * @param p_playerName name of the player
	 * @return Positive response if player is removed
	 */
	public String removePlayer(String p_playerName) {
		if(!d_players.containsKey(p_playerName)) {
			return String.format("Player \"%s\" not present in game", p_playerName);
		}
		d_players.remove(p_playerName);
		d_playerName.remove(p_playerName);
		return String.format("Player \"%s\" removed from map", p_playerName);
	}
	
	/**
	 * To get map
	 * @return object of gamemap
	 */
	public GameMap getGameMap() {
		return d_gameMap;
	}
	
//	public String assign() {
//		for(String playerName:d_playerName) {
//			if(playerName.equals("Shubham")) {
//				d_players.get(playerName).addCountry(this.getGameMap().getCountries().get(1));
//				d_players.get(playerName).addCountry(this.getGameMap().getCountries().get(2));
//				d_players.get(playerName).addCountry(this.getGameMap().getCountries().get(3));
//				d_players.get(playerName).addContinent(this.getGameMap().getContinents().get(1));
//			}
//			else {
//				d_players.get(playerName).addCountry(this.getGameMap().getCountries().get(4));
//				d_players.get(playerName).addCountry(this.getGameMap().getCountries().get(5));
//				d_players.get(playerName).addContinent(this.getGameMap().getContinents().get(3));
//			}
//			d_players.get(playerName).setNumberOfArmies();
//		}
//		deployPhase();
//		return String.format("Countries and armies assigned");
//		
//	}
	
	
	/**
	 * Loop over players and player deploys their reinforcements to their countries
	 */
	public void deployPhase() {
		int l_currentPlayer = 0;
		HashSet<String> l_playersCompleted = new HashSet<>();
		System.out.println("Deploy phase entered");
		while(l_playersCompleted.size() < d_playerName.size()) {
			if(d_players.get(d_playerName.get(l_currentPlayer)).getNumberOfArmies() > 0) {
				System.out.println("Player " +d_playerName.get(l_currentPlayer)+ "'s turn");
				System.out.println("Number of armies left: " + d_players.get(d_playerName.get(l_currentPlayer)).getNumberOfArmies());
				d_players.get(d_playerName.get(l_currentPlayer)).issueOrder();
				System.out.println(d_players.get(d_playerName.get(l_currentPlayer)).nextOrder().executeOrder(this));
			}
			else {
				l_playersCompleted.add(d_playerName.get(l_currentPlayer));
			}
			++l_currentPlayer;
			if(l_currentPlayer == d_playerName.size()) {
				l_currentPlayer = 0;
			}
		}
	}
	
	/**
	 * Get the hash map of player names corresponding to its object
	 * @return players hashmap
	 */
	public HashMap<String, Player> getPlayers() {
		return d_players;
	}
	
	/**
	 * method to assign the countries to the players at the start of the game
	 * @return returns the message to the caller
	 */
	public String assignCountries() {
		HashMap<Integer, Country> l_countries = d_gameMap.getCountries();
		List<Country> l_countryObjects = new ArrayList<Country>();
		l_countryObjects.addAll(l_countries.values());
		System.out.println(l_countries.keySet());
		Random l_random = new Random();
		while (true) {
			for (Player p_player : d_players.values()) {
				if(l_countryObjects.size() == 0) {
					break;
				}
				int l_idOfCountry = l_random.nextInt(l_countryObjects.size());
				p_player.addCountry(l_countryObjects.get(l_idOfCountry));
				l_countryObjects.remove(l_countryObjects.get(l_idOfCountry));
			}
			if(l_countryObjects.size() == 0) {
				break;
			}
		}
		for (Player p_player: d_players.values()) {
			System.out.println(p_player.getCountries().size());
			System.out.println(p_player.getName() + " : " + p_player.getCountries().keySet());
		}
		return "Country assigned";
	}
	
	public static void main(String[] args) {
		GameStarter gStarter = new GameStarter();
		gStarter.loadMap("risk.map");
		gStarter.addPlayer("Shubham");
		gStarter.addPlayer("Patel");
		gStarter.addPlayer("Virag");
		gStarter.addPlayer("Vandit");
		
		String result = gStarter.assignCountries();
		System.out.println(result);
		
		/**gStarter.getGameMap().addContinent(1, 6);
		gStarter.getGameMap().addContinent(7, 5);
		gStarter.getGameMap().addCountry(1, 1);
		gStarter.getGameMap().addCountry(2, 1);
		gStarter.getGameMap().addCountry(3, 1);
		gStarter.getGameMap().addCountry(4, 1);
		gStarter.getGameMap().addCountry(5, 2);
		gStarter.getGameMap().addCountry(6, 2);
		gStarter.getGameMap().addCountry(7, 2);
		gStarter.getGameMap().addCountry(8, 2);
		gStarter.getGameMap().addCountry(9, 2);
		
//		System.out.println(gStarter.getGameMap().getContinents().get(1).getId() +", "+ gStarter.getGameMap().getContinents().get(1).getControlValue());
		
		
		gStarter.d_players.get("Shubham").addCountry(gStarter.getGameMap().getCountries().get(1));
		gStarter.d_players.get("Shubham").addCountry(gStarter.getGameMap().getCountries().get(2));
		gStarter.d_players.get("Shubham").addCountry(gStarter.getGameMap().getCountries().get(3));
		gStarter.d_players.get("Shubham").addCountry(gStarter.getGameMap().getCountries().get(4));
		gStarter.d_players.get("Shubham").addCountry(gStarter.getGameMap().getCountries().get(9));
//		gStarter.d_players.get("Shubham").addContinent(gStarter.getGameMap().getContinents().get(1));
		gStarter.d_players.get("Shubham").addContinent(gStarter.getGameMap().getContinents().get(7));
		
		gStarter.d_players.get("Patel").addCountry(gStarter.getGameMap().getCountries().get(5));
		gStarter.d_players.get("Patel").addCountry(gStarter.getGameMap().getCountries().get(6));
		gStarter.d_players.get("Patel").addCountry(gStarter.getGameMap().getCountries().get(7));
		gStarter.d_players.get("Patel").addCountry(gStarter.getGameMap().getCountries().get(8)); 
		
		System.out.println(gStarter.d_players.get("Shubham").getCountries().keySet());
		System.out.println(gStarter.d_players.get("Shubham").getContinents().keySet());
		gStarter.d_players.get("Shubham").setNumberOfArmies();
//		gStarter.d_players.get("Patel").setNumberOfArmies();
		
		System.out.println(gStarter.d_players.get("Shubham").getNumberOfArmies());
//		System.out.println(gStarter.d_players.get("Patel").getNumberOfArmies());*/
//		gStarter.deployPhase();
		
	}
}

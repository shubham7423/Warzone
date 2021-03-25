package controller.state.gamephase.gamesetup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import controller.GameEngine;
import controller.state.gamephase.gameplay.AssignArmies;
import entities.Country;
import entities.Player;

public class PostLoad extends GameSetup {

	/**
	 * constructor method that takes game engine object from the parent class 
	 * @param p_gameEngine object of the game engine
	 */
	public PostLoad(GameEngine p_gameEngine) {
		super(p_gameEngine);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * function to load the map for playing the game
	 * @return string suggesting that map has already been loaded as it is in the postLoad phase
	 */
	@Override
	public String loadMap(String p_fileName) {
		// TODO Auto-generated method stub
		return "Map already loaded";
	}

	/**
	 * function to support adding and removing of players to the game
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return the result of the command provided
	 */
	@Override
	public String gamePlayer(String[] p_commandSplitted) {
		String l_result;
		if ("-add".equals(p_commandSplitted[0])) {
			l_result = addPlayer(p_commandSplitted[1]);
		} else {
			l_result = removePlayer(p_commandSplitted[1]);
		}
		return l_result;
	}
	
	/**
	 * function to assign countries to the players present in the game
	 * @return string indicating that countries are assigned to the players
	 */
	public String assignCountries() {
		if (d_gameEngine.d_players.size() < 2) {
			return "There must be at least two player";
		}
		HashMap<Integer, Country> l_countries = d_gameEngine.getGameMap().getCountries();
		List<Country> l_countryObjects = new ArrayList<Country>();
		l_countryObjects.addAll(l_countries.values());
		Random l_random = new Random();
		while (true) {
			for (Player p_player : d_gameEngine.d_players.values()) {
				if (l_countryObjects.size() == 0) {
					break;
				}
				int l_idOfCountry = l_random.nextInt(l_countryObjects.size());
				p_player.addCountry(l_countryObjects.get(l_idOfCountry));
				l_countryObjects.get(l_idOfCountry).setPlayer(p_player);
				l_countryObjects.remove(l_countryObjects.get(l_idOfCountry));
			}
			if (l_countryObjects.size() == 0) {
				break;
			}
		}
		d_gameEngine.d_logEntryBuffer.setString("Countries Assigned");
		System.out.println("Countries Assigned");
		next();
		d_gameEngine.getPhase().assignArmies();
		return "Countries Assigned";
	}
	
	/**
	 * function to add a player in the game and checks whether the player is already present in the game or not 
	 * @return sting indicating the whether player is added or not
	 */
	@Override
	public String addPlayer(String p_playerName) {
		if (d_gameEngine.d_players.containsKey(p_playerName)) {
			d_gameEngine.d_logEntryBuffer.setString(String.format("Player \"%s\" already present in game", p_playerName));
			return String.format("Player \"%s\" already present in game", p_playerName);
		}
		d_gameEngine.d_players.put(p_playerName, new Player(p_playerName));
		d_gameEngine.d_playerName.add(p_playerName);
		d_gameEngine.d_logEntryBuffer.setString(String.format("Player \"%s\" added to game", p_playerName));
		return String.format("Player \"%s\" added to game", p_playerName);
	}

	/**
	 * function to remove a player from the game and checks whether the player is already present in the game or not 
	 * @return sting indicating the whether player is removed or not
	 */
	@Override
	public String removePlayer(String p_playerName) {
		if (!d_gameEngine.d_players.containsKey(p_playerName)) {
			d_gameEngine.d_logEntryBuffer.setString(String.format("Player \"%s\" not present in game", p_playerName));
			return String.format("Player \"%s\" not present in game", p_playerName);
		}
		d_gameEngine.d_players.remove(p_playerName);
		d_gameEngine.d_playerName.remove(p_playerName);
		d_gameEngine.d_logEntryBuffer.setString(String.format("Player \"%s\" removed from game", p_playerName));
		return String.format("Player \"%s\" removed from game", p_playerName);
	}

	/**
	 * Function that sets the phase to proceed in the game
	 */
	@Override
	public void next() {
		d_gameEngine.setPhase(new AssignArmies(d_gameEngine));
	}

}

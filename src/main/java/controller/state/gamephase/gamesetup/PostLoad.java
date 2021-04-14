package controller.state.gamephase.gamesetup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import controller.GameEngine;
import controller.state.gamephase.gameplay.AssignArmies;
import entities.Country;
import entities.Player;
import strategy.Aggresive;
import strategy.Benevolent;
import strategy.Cheater;
import strategy.HumanPlayer;
import strategy.RandomPlayer;

/**
 * Phase entered after preload class, it contains methods to add or remove
 * players from game and to assign countries randomly to players.
 */
public class PostLoad extends GameSetup {

	/**
	 * constructor method that takes game engine object from the parent class
	 * 
	 * @param p_gameEngine object of the game engine
	 */
	public PostLoad(GameEngine p_gameEngine) {
		super(p_gameEngine);
	}

	/**
	 * function to load a previously saved game
	 * 
	 * @param p_fileName name of the file to be loaded
	 * @return string representing the result of loading the file
	 */
	@Override
	public String loadGame(String p_fileName) {
		return printInvalidCommandMessage();
	}

	/**
	 * function to load the map for playing the game
	 * 
	 * @param p_fileName Name of the map file to be loaded
	 * @return string suggesting that map has already been loaded as it is in the
	 *         postLoad phase
	 */
	@Override
	public String loadMap(String p_fileName) {
		return "Map already loaded";
	}

	/**
	 * function to support adding and removing of players to the game
	 * 
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
	 * 
	 * @return string indicating that countries are assigned to the players
	 */
	@Override
	public String assignCountries() {
		if (d_gameEngine.d_players.size() < 2) {
			return "There must be at least two player";
		}
		for (Player l_player : d_gameEngine.d_players.values()) {
			boolean l_isCorrect = false;
			if (l_player.d_strategy == null) {
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println(" _______________________________ ");
				System.out.println("| Player Strategy Selection\t|");
				System.out.println("|===============================|");
				System.out.println("| 1. Random Player\t\t|");
				System.out.println("| 2. Human Player\t\t|");
				System.out.println("| 3. Benevolent Player\t\t|");
				System.out.println("| 4. Aggresive Player\t\t|");
				System.out.println("| 5. Cheater Player\t\t|");
				System.out.println("|_______________________________|");
				while (!l_isCorrect) {
					System.out.print("Enter strategy for player " + l_player.getName() + ": ");
					switch (new Scanner(System.in).nextInt()) {
					case 1:
						l_player.setStrategy(new RandomPlayer(l_player, d_gameEngine));
						l_isCorrect = true;
						break;

					case 2:
						l_player.setStrategy(new HumanPlayer(l_player, d_gameEngine));
						l_isCorrect = true;
						break;

					case 3:
						l_player.setStrategy(new Benevolent(l_player, d_gameEngine));
						l_isCorrect = true;
						break;

					case 4:
						l_player.setStrategy(new Aggresive(l_player, d_gameEngine));
						l_isCorrect = true;
						break;

					case 5:
						l_player.setStrategy(new Cheater(l_player, d_gameEngine));
						l_isCorrect = true;
						break;

					default:
						System.out.println("Please enter valid behaviour");
					}
				}

			}
		}
		HashMap<Integer, Country> l_countries = d_gameEngine.getGameMap().getCountries();
		List<Country> l_countryObjects = new ArrayList<Country>();
		l_countryObjects.addAll(l_countries.values());
		while (true) {
			for (Player p_player : d_gameEngine.d_players.values()) {
				if (l_countryObjects.size() == 0) {
					break;
				}
				int l_idOfCountry = d_gameEngine.d_random.nextInt(l_countryObjects.size());
				p_player.addCountry(l_countryObjects.get(l_idOfCountry));
				l_countryObjects.get(l_idOfCountry).setPlayer(p_player);
				l_countryObjects.remove(l_countryObjects.get(l_idOfCountry));
			}
			if (l_countryObjects.size() == 0) {
				break;
			}
		}
		System.out.println("Countries Assigned");
		d_gameEngine.d_logEntryBuffer.setString("Countries Assigned");
		System.out.println("Countries Assigned");
		next();
		d_gameEngine.getPhase().assignArmies();
		return "";
	}

	/**
	 * function to add a player in the game and checks whether the player is already
	 * present in the game or not
	 * 
	 * @param p_playerName Name of the player to be added
	 * @return sting indicating the whether player is added or not
	 */
	@Override
	public String addPlayer(String p_playerName) {
		if (d_gameEngine.d_players.containsKey(p_playerName)) {
			d_gameEngine.d_logEntryBuffer
					.setString(String.format("Player \"%s\" already present in game", p_playerName));
			return String.format("Player \"%s\" already present in game", p_playerName);
		}
		d_gameEngine.d_players.put(p_playerName, new Player(p_playerName));
		d_gameEngine.d_playerName.add(p_playerName);
		d_gameEngine.d_logEntryBuffer.setString(String.format("Player \"%s\" added to game", p_playerName));
		return String.format("Player \"%s\" added to game", p_playerName);
	}

	/**
	 * function to remove a player from the game and checks whether the player is
	 * already present in the game or not
	 * 
	 * @param p_playerName Name of the player to be removed
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

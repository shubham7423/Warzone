package entities.savedgames;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

import controller.GameEngine;
import controller.state.gamephase.gamesetup.PostLoad;
import controller.state.gamephase.gamesetup.PreLoad;
import entities.Player;
import strategy.*;

/**
 * Class to load a game from a ".game" file.
 */
public class LoadGame {
	private GameEngine d_gameEngine;
	private HashMap<String, Integer> d_continentsMap;
	private HashMap<Integer, String> d_countriesMap;
	Scanner d_reader;
	
	public LoadGame(GameEngine p_gameEngine) {
		d_gameEngine = p_gameEngine;
		d_continentsMap = new HashMap<>();
		d_countriesMap = new HashMap<>();
	}

	/**
	 * Method that opens a file and reads the Game Data from that file.
	 * 
	 * @param p_fileName from .game file
	 * @return 
	 */
	
	public String loadGame(String p_fileName){
		try {
			File l_mapFile = new File(
					Paths.get(Paths.get("").toAbsolutePath().toString() + "/games/" + p_fileName).toString());
			String l_line, l_dataString;
			int l_countryCtn = 0, l_continentCtn = 0;
			
			d_reader = new Scanner(l_mapFile);
			while (d_reader.hasNextLine()) {
				l_dataString = d_reader.nextLine();

//				Read continents
				if ("[continents]".equals(l_dataString)) {
					while (d_reader.hasNextLine()) {
						l_line = d_reader.nextLine();
						if (l_line.length() > 0) {
							String[] l_continents = l_line.split(" ");
							++l_continentCtn;
							d_continentsMap.put(l_continents[0], l_continentCtn);
							d_gameEngine.getGameMap().addContinent(l_continentCtn, Integer.parseInt(l_continents[1]));
						} else {
							break;
						}
					}
				}
				
//				Read countries
				else if ("[countries]".equals(l_dataString)) {
					while (d_reader.hasNextLine()) {
						l_line = d_reader.nextLine();
						if (l_line.length() > 0) {
							String[] l_countries = l_line.split(" ");
							++l_countryCtn;
							d_countriesMap.put(l_countryCtn, l_countries[1]);
							d_gameEngine.getGameMap().addCountry(Integer.parseInt(l_countries[0]), Integer.parseInt(l_countries[2]));
						} else {
							break;
						}
					}
				}
				
//				Read boundries
				else if ("[borders]".equals(l_dataString)) {
					while (d_reader.hasNextLine()) {
						l_line = d_reader.nextLine();
						if (l_line.length() > 0) {
							String[] l_borders = l_line.split(" ");
							int l_countryId = Integer.parseInt(l_borders[0]);
							int l_neighborId;
							for (int i = 1; i < l_borders.length; i++) {
								l_neighborId = Integer.parseInt(l_borders[i]);
								d_gameEngine.getGameMap().addNeighbor(l_countryId, l_neighborId);
							}
						} else {
							break;
						}

					}
				}
				
				else if ("[PlayerName|Strategy|#Continents|#Countries|NumArmies|[ContinentId]|[CountryId CountryArmies]|[Airlift,Blockade,Bomb,Diplomacy]|[NegotiatedPlayersList]]".equals(l_dataString)) {
					while (d_reader.hasNextLine()) {
						l_line = d_reader.nextLine();
						if (l_line.length() > 0) {
							String[] l_playerInfo = l_line.split("[|]");
							
//[PlayerName|Strategy|#Continents|#Countries|NumArmies|[ContinentId]|[CountryId CountryArmies]|[Airlift,Blockade,Bomb,Diplomacy]|[NegotiatedPlayersList]]
//a|humanPlayer|2|3|9|[2,3]|[3 0,4 0,5 0]|[airlift 0,blockade 0,bomb 0,diplomacy 0]|[]
//							adding player to game
							d_gameEngine.setPhase(new PostLoad(d_gameEngine));
							d_gameEngine.gamePlayer(new String[]{"gameplayer","-add",l_playerInfo[0]});
							
//							setting strategy type of the player
							Player l_currentPlayer = d_gameEngine.d_players.get(l_playerInfo[0]);
							switch(l_playerInfo[1]) {
								case "aggressivePlayer":
									l_currentPlayer.setStrategy(new Aggresive(l_currentPlayer, d_gameEngine));
									break;
								case "benevolentPlayer":
									l_currentPlayer.setStrategy(new Benevolent(l_currentPlayer, d_gameEngine));
									break;
								case "cheaterPlayer":
									l_currentPlayer.setStrategy(new Cheater(l_currentPlayer, d_gameEngine));
									break;
								case "humanPlayer":
									l_currentPlayer.setStrategy(new HumanPlayer(l_currentPlayer, d_gameEngine));
									break;
								case "randomPlayer":
									l_currentPlayer.setStrategy(new RandomPlayer(l_currentPlayer, d_gameEngine));
									break;
							}
							
						} else {
							break;
						}
					}
				}
			}
			d_reader.close();
			return "kaik return thay che";//to be implemented
		}catch (FileNotFoundException p_e) {
			System.out.println("Exception " + p_e.getMessage());
			p_e.printStackTrace();
			return "kaik return nthi thtu";
		}
	}
}
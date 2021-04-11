package entities.savedgames;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


import controller.GameEngine;
import entities.Continent;
import entities.Country;
import entities.GameMap;
import entities.Player;
import entities.orders.Orders;
import strategy.Cheater;
import strategy.Aggresive;
import strategy.Benevolent;
import strategy.HumanPlayer;
import strategy.RandomPlayer;

/**
 * Class to save the game into a ".game" file.
 */
public class SaveGame {

	private GameMap d_gameMap;
	private LinkedHashMap<Integer, Integer> d_continentsMap;
	private LinkedHashMap<Integer, Integer> d_countriesMap;
	private BufferedWriter d_writer;
	private GameEngine d_gameEngine;
	
	public SaveGame(GameEngine p_gameEngine) {
		d_gameMap = new GameMap();
		d_continentsMap = new LinkedHashMap<>();
		d_countriesMap = new LinkedHashMap<>();
		d_gameEngine = p_gameEngine;
	}

	public String saveGame(String p_fileName, Player p_callePlayer) {
		d_gameMap = d_gameEngine.getGameMap();
		int l_countryCtn = 0, l_continentCtn = 0;
		try {
			FileWriter l_fw = new FileWriter(
					Paths.get(Paths.get("").toAbsolutePath().toString() + "/games/" + p_fileName).toString());
			d_writer = new BufferedWriter(l_fw);
//			 Saving Map first for loading all components on it
//			 Writing Continents
			HashMap<Integer, Continent> l_continents = new HashMap<>();
			l_continents = d_gameMap.getContinents();
			d_writer.write("[continents]");
			d_writer.newLine();
			for (int p_continents : l_continents.keySet()) {
				d_continentsMap.put(p_continents, ++l_continentCtn);
				d_writer.write(p_continents + " " + l_continents.get(p_continents).getControlValue());
				d_writer.newLine();
			}
			
//			 Writing countries
			d_writer.write("\n");
			d_writer.write("[countries]");
			d_writer.newLine();
			for (int p_continents : d_continentsMap.keySet()) {
				Set<Integer> l_countriesId = l_continents.get(p_continents).getCountriesIds();
				for (int p_countriesId : l_countriesId) {
					d_countriesMap.put(p_countriesId, ++l_countryCtn);
					d_writer.write(l_countryCtn + " " + p_countriesId + " " + d_continentsMap.get(p_continents));
					d_writer.newLine();
				}
			}

//			 Writing borders
			HashMap<Integer, Country> l_countries = new HashMap<>();
			l_countries = d_gameMap.getCountries();
			d_writer.write("\n");
			d_writer.write("[borders]");
			d_writer.newLine();
			for (int p_countries : d_countriesMap.keySet()) {
				Set<Integer> l_neighborIds = l_countries.get(p_countries).getNeighborIds();
				StringBuilder l_sb = new StringBuilder("");
				l_sb.append(d_countriesMap.get(p_countries).toString() + " ");

				for (int p_neighborIds : l_neighborIds) {
					l_sb.append(d_countriesMap.get(p_neighborIds).toString() + " ");
				}
				d_writer.write(l_sb.toString());
				d_writer.newLine();
			}

			d_writer.newLine();
			d_writer.write("[PlayerName|Strategy|#Continents|#Countries|NumArmies|[ContinentId]|[CountryId CountryArmies]|[Airlift,Blockade,Bomb,Diplomacy]|[NegotiatedPlayersList]]");
			d_writer.newLine();
			for(Player l_currentPlayer : d_gameEngine.d_players.values()) {
				//player name
				d_writer.write(l_currentPlayer.getName());
				
				//behavior of the player
				d_writer.write("|");
				{
					if(l_currentPlayer.getPlayerBehaviour() instanceof Aggresive) {
						d_writer.write("aggressivePlayer");
					} else if(l_currentPlayer.getPlayerBehaviour() instanceof Benevolent) {
						d_writer.write("benevolentPlayer");						
					} else if(l_currentPlayer.getPlayerBehaviour() instanceof Cheater) {
						d_writer.write("cheaterPlayer");						
					} else if(l_currentPlayer.getPlayerBehaviour() instanceof HumanPlayer) {
						d_writer.write("humanPlayer");						
					} else if(l_currentPlayer.getPlayerBehaviour() instanceof RandomPlayer) {
						d_writer.write("randomPlayer");						
					}
				}
				
				//number of continents owned by the player
				d_writer.write("|");
				d_writer.write(l_currentPlayer.getContinents().size()+"");
				
				//number of countries owned by the player
				d_writer.write("|");
				d_writer.write(l_currentPlayer.getCountries().size()+"");
				
				//number of armies owned by the player
				d_writer.write("|");
				d_writer.write(l_currentPlayer.getNumberOfArmies()+"");
				
				//all continents owned by the player
				d_writer.write("|");
				d_writer.write("[");
				boolean l_tempFlag = false;
				for(Continent l_continent: l_currentPlayer.getContinents().values()) {
					if(l_tempFlag) {
						d_writer.write(",");
					}
					l_tempFlag = true;
					d_writer.write(l_continent.getId()+"");
				}
				d_writer.write("]");
				
				//all countries owned by the player and it's armies placed in each country
				d_writer.write("|");
				d_writer.write("[");
				l_tempFlag = false;
				for(Country l_country: l_currentPlayer.getCountries().values()) {
					if(l_tempFlag) {
						d_writer.write(",");
					}
					l_tempFlag = true;
					d_writer.write(l_country.getId()+" "+l_country.getNumberOfArmiesPresent());
				}
				d_writer.write("]");

				//cards owned by the player
				d_writer.write("|");
				d_writer.write("[");
				HashMap<String, Integer> l_cardsOwned = l_currentPlayer.d_cardsOwned;
				d_writer.write("airlift " +l_cardsOwned.get("airlift"));
				d_writer.write(",blockade " +l_cardsOwned.get("blockade"));
				d_writer.write(",bomb " +l_cardsOwned.get("bomb"));
				d_writer.write(",diplomacy " +l_cardsOwned.get("diplomacy"));
				d_writer.write("]");
				d_writer.write("|");
				d_writer.write("[");
				l_tempFlag = false;
				for(String l_negotiatedPlayer : l_currentPlayer.d_negotiatedPlayerNames) {
					if(l_tempFlag) {
						d_writer.write(",");
					}
					l_tempFlag = true;
					d_writer.write(l_negotiatedPlayer);
				}
				d_writer.write("]");
				d_writer.newLine();
			}
			
			Player l_neutralPlayer = d_gameEngine.d_neutralPlayer;
			d_writer.newLine();
			d_writer.write("[neutralPlayer]");
			d_writer.newLine();
			d_writer.write("neutralPlayer#1|neutral|");
			d_writer.write(l_neutralPlayer.getContinents().size()+"|"+
					l_neutralPlayer.getCountries().size()+"|"+
					l_neutralPlayer.getNumberOfArmies()+"|"
					);

			d_writer.write("[");
			boolean l_tempFlag = false;
			for(Continent l_continent: l_neutralPlayer.getContinents().values()) {
				if(l_tempFlag) {
					d_writer.write(",");
				}
				l_tempFlag = true;
				d_writer.write(l_continent.getId()+"");
			}
			d_writer.write("]|[");
			l_tempFlag = false;
			for(Country l_country: l_neutralPlayer.getCountries().values()) {
				if(l_tempFlag) {
					d_writer.write(",");
				}
				l_tempFlag = true;
				d_writer.write(l_country.getId()+" "+l_country.getNumberOfArmiesPresent());
			}
			d_writer.write("]|[airlift 0,blockade 0,bomb 0,diplomacy 0]|[]");
			d_writer.newLine();
			
			//Saving orders of all players in round robin fashion below
			d_writer.newLine();
			d_writer.write("[orders]");
			d_writer.newLine();
			
			//saving commands in the list first before writing in the file
			HashMap<String, Queue<String>> l_playerOrderHashMap = new HashMap<>(); 
			for(Player l_currentPlayer : d_gameEngine.d_players.values()) {
				Queue<String> l_currentPlayerQueue = new LinkedList<String>();
				for(Orders l_tempOrders : l_currentPlayer.d_orders) {
					l_currentPlayerQueue.add(l_tempOrders.getOrder());
				}
				l_playerOrderHashMap.put(l_currentPlayer.getName(), l_currentPlayerQueue);
			}
			
			//writing the orders/commands in file in round robin fashion
			boolean l_hasMoreOrders = false;
			do {
				l_hasMoreOrders = false;
				for(String l_currentPlayer : l_playerOrderHashMap.keySet()) {
					if(l_playerOrderHashMap.get(l_currentPlayer).isEmpty()) {
						l_hasMoreOrders = false;
						continue;
					}
					
					l_hasMoreOrders = true;
					String l_commandString = l_playerOrderHashMap.get(l_currentPlayer).remove();
					d_writer.write(l_currentPlayer + " " + l_commandString);
					d_writer.newLine();
				}	
			} while(l_hasMoreOrders);
			
			//committed players
			d_writer.newLine();
			d_writer.write("[committedPlayer]");
			d_writer.newLine();
			for(Player l_currentPlayer : d_gameEngine.d_players.values()) {
				if(l_currentPlayer.getIsCommit()) {
					d_writer.write(l_currentPlayer.getName());
					d_writer.newLine();
				}
			}
			
			//next player
			d_writer.newLine();
			d_writer.write("[nextPlayer]");
			d_writer.newLine();
			d_writer.write(p_callePlayer.getName());
			d_writer.newLine();
			
			d_writer.newLine();
			
			
			d_writer.close();
			l_fw.close();
			return "Game saved successfully in " + p_fileName;
		} catch (Exception p_e) {
			System.out.println("Exception " + p_e.getMessage());
			p_e.printStackTrace();
			return "Saving Game Unsuccessful..";
		}
	} 
}

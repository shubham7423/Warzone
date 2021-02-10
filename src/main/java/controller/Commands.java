package controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * Class to execute user commands
 *
 */
public class Commands {
	
	private Set<String> d_commands = new HashSet<>(Arrays.asList("loadmap", "editcountry", "editcontinent", "editneighbour"));
	GameStarter d_gameStarter = new GameStarter();
	
	/**
	 * Main execution command to run other command types
	 * @param p_splittedCommand splitting of commands to pass to methods
	 * @return l_result result after execution of command
	 */
	public String executeCommand(String[] p_splittedCommand) {
		String l_result = "";
		if(!d_commands.contains(p_splittedCommand[0])) {
			return "Command not found";
		}
		switch(p_splittedCommand[0]) {
			case "loadmap": 
				l_result = loadMap(p_splittedCommand);
				break;
			
			case "editcontinent":
				l_result = editContinent(p_splittedCommand);
				break;
				
			case "editcountry":
				l_result = editCountry(p_splittedCommand);
				break;
				
			case "editneighbour":
				l_result = editNeighbour(p_splittedCommand);
				break;
		}
		
		return l_result;
	}
	
	/**
	 * method to load maps
	 * @param p_splittedCommand splitted commands
	 * @return loaded map(responses positive or negative)
	 */
	public String loadMap(String[] p_splittedCommand) {
		if(p_splittedCommand.length < 2) {
			return "Please enter file name";
		}
		return d_gameStarter.loadMap(p_splittedCommand[1]);
	}
	
	/**
	 * Edit continents 
	 * @param p_splittedCommand splitted commands
	 * @return shows whether continents are added or removed
	 */
	public String editContinent(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int i=1;
		while(i < p_splittedCommand.length) {			
			if(p_splittedCommand[i].equals("-add")) {
				l_commandParts = new String[3];
				l_commandParts[0] = p_splittedCommand[i];
				l_commandParts[1] = p_splittedCommand[i+1];
				l_commandParts[2] = p_splittedCommand[i+2];
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_gameStarter.editContinent(l_commandParts);
				i = i + 3;
			}
			else if (p_splittedCommand[i].equals("-remove")) {
				l_commandParts = new String[2];
				l_commandParts[0] = p_splittedCommand[i];
				l_commandParts[1] = p_splittedCommand[i+1];
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_gameStarter.editContinent(l_commandParts);
				i = i + 2;
			}
			else {
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += "Command needs to have -add or -remove.";
				i++;
			}
		}
		return l_result;
	}
	
	/**
	 * edit countries
	 * @param p_splittedCommand splitted commands
	 * @return shows whether countries are added or removed with respect to their continents
	 */
	public String editCountry(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int i=1;
		while(i < p_splittedCommand.length) {			
			if(p_splittedCommand[i].equals("-add")) {
				l_commandParts = new String[3];
				l_commandParts[0] = p_splittedCommand[i];
				l_commandParts[1] = p_splittedCommand[i+1];
				l_commandParts[2] = p_splittedCommand[i+2];
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_gameStarter.editCountry(l_commandParts);
				i = i + 3;
			}
			else if (p_splittedCommand[i].equals("-remove")) {
				l_commandParts = new String[2];
				l_commandParts[0] = p_splittedCommand[i];
				l_commandParts[1] = p_splittedCommand[i+1];
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_gameStarter.editCountry(l_commandParts);
				i = i + 2;
			}
			else {
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += "Command needs to have -add or -remove.";
				i++;
			}
		}
		return l_result;
	}
	
	/**
	 * edit neighbours
	 * @param p_splittedCommand splitted commands
	 * @return shows neighbours added or removed to the country
	 */
	public String editNeighbour(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int i=1;
		while(i < p_splittedCommand.length) {			
			if(p_splittedCommand[i].equals("-add")) {
				l_commandParts = new String[3];
				l_commandParts[0] = p_splittedCommand[i];
				l_commandParts[1] = p_splittedCommand[i+1];
				l_commandParts[2] = p_splittedCommand[i+2];
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_gameStarter.editNeighbour(l_commandParts);
				i = i + 3;
			}
			else if (p_splittedCommand[i].equals("-remove")) {
				l_commandParts = new String[2];
				l_commandParts[0] = p_splittedCommand[i];
				l_commandParts[1] = p_splittedCommand[i+1];
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_gameStarter.editNeighbour(l_commandParts);
				i = i + 2;
			}
			else {
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += "Command needs to have -add or -remove.";
				i++;
			}
		}
		return l_result;
	}
	
	
	
	public static void main(String[] args) {
		Commands commands = new Commands();
//		String[] newStrings = new String[]{"loadmap", "u.map"};
		String[] editCommandStrings = new String[]{"editcontinent", "-add", "Asia", "2"};
		String[] editCommandStrings1 = new String[]{"editcontinent", "-remove", "Asia"};
		String[] editCommandStrings2 = new String[]{"editcountry", "-add", "india","Asia", "-add", "can", "Asia"};
		String[] editCommandStrings3 = new String[]{"editneighbour", "-add", "india" ,"can"};
		String[] editCommandStrings4 = new String[]{"editcountry", "-remove", "india"};
//		System.out.println(commands.executeCommand(newStrings));
		System.out.println(commands.executeCommand(editCommandStrings));
		//System.out.println(commands.editContinent(editCommandStrings1));
 		System.out.println(commands.executeCommand(editCommandStrings2));
 		//System.out.println(commands.executeCommand(editCommandStrings4));
		System.out.println(commands.editNeighbour(editCommandStrings3));
		System.out.println(commands.d_gameStarter.d_gameMap.getContinents().keySet());
		System.out.println(commands.d_gameStarter.d_gameMap.getCountries().keySet());
//		System.out.println(commands.d_gameStarter.d_gameMap.getCountries().get("india").getNeighbourNames());

	}
}


package controller;

import java.util.Scanner;

/**
 * Class to execute user commands
 *
 */
public class Commands {
	
	GameStarter d_gameStarter = new GameStarter();
	
	/**
	 * Main execution command to run other command types
	 * @param p_splittedCommand splitting of commands to pass to methods
	 * @return l_result result after execution of command
	 */
	public String executeCommand(String[] p_splittedCommand) {
		String l_result = "";
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
				
			case "editmap":
				l_result = editMap(p_splittedCommand);
				break;
				
			case "savemap":
				l_result = saveMap(p_splittedCommand);
				break;
				
			default:
				l_result = "Command not found";		
		}
		
		return l_result;
	}
	
	/**
	 * method to load maps
	 * @param p_splittedCommand splitted commands to extract sub parts
	 * @return loaded map(responses positive or negative)
	 */
	public String loadMap(String[] p_splittedCommand) {
		if(p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		return d_gameStarter.loadMap(p_splittedCommand[1]);
	}
	
	/**
	 * Edit continents 
	 * @param p_splittedCommand splitted commands to extract sub parts
	 * @return shows whether continents are added or removed
	 */
	public String editContinent(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int i=1;
		if(p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
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
	 * @param p_splittedCommand splitted commands to extract sub parts
	 * @return shows whether countries are added or removed with respect to their continents
	 */
	public String editCountry(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int i=1;
		if(p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
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
	 * @param p_splittedCommand splitted commands to extract sub parts
	 * @return shows neighbours added or removed to the country
	 */
	public String editNeighbour(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int i=1;
		if(p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
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
	
	/**
	 * edit map
	 * @param p_splittedCommand splitted commands to extract sub parts
	 * @return shows if map is available to edit or not
	 */
	public String editMap(String[] p_splittedCommand) {
		if(p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		return d_gameStarter.editMap(p_splittedCommand[1]);
	}
	
	
	public String saveMap(String[] p_splittedCommand) {
		if(p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		return d_gameStarter.saveMap(p_splittedCommand[1]);
	}
	
	/**
	 * Game can be started from here
	 * @param args
	 */
	public static void main(String[] args) {
		Commands l_commands = new Commands();
		Scanner l_scannerScanner = new Scanner(System.in);
		String l_userCommand;
		System.out.println("Welcome to Warzone");
		while(true) {
			System.out.print("$ ");
			l_userCommand = l_scannerScanner.nextLine();
			if(l_userCommand.equals("exit()")) {
				break;
			}
			String[] l_splittedCommandString = l_userCommand.split(" ");
			System.out.println(l_commands.executeCommand(l_splittedCommandString));
		}
		System.out.print("\nThank you for playing Warzone :)");
		l_scannerScanner.close();
	}
}


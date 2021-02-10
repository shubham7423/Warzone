package controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.sun.net.httpserver.Authenticator.Result;

public class Commands {
	
//	Contains all the commands available
	private Set<String> d_commands = new HashSet<>(Arrays.asList("loadmap", "editcountry"));
	GameStarter d_gameStarter = new GameStarter();
	
//	execute commands use switch case 
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
		}
		
		return l_result;
	}
	
	public String loadMap(String[] p_splittedCommand) {
		if(p_splittedCommand.length < 2) {
			return "Please enter file name";
		}
		return d_gameStarter.loadMap(p_splittedCommand[1]);
	}
	
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
		String[] newStrings = new String[]{"loadmap", "uk.map"};
		String[] editCommandStrings = new String[]{"editcontinent", "-add", "Asia", "2", "Africa", "2", "-add", "America", "1"};
		//String[] editCommandStrings1 = new String[]{"editcontinent", "-remove", "Asia"};
		String[] editCommandStrings2 = new String[]{"editcountry", "-add", "india","Asia", "-add", "can", "Asia"};
		String[] editCommandStrings3 = new String[]{"editneighbour", "-add", "india" ,"can"};
		System.out.println(commands.executeCommand(newStrings));
		System.out.println(commands.editContinent(editCommandStrings));
		System.out.println(commands.editCountry(editCommandStrings2));
		System.out.println(commands.editNeighbour(editCommandStrings3));
		System.out.println(commands.d_gameStarter.d_gameMap.getContinents().keySet());
		System.out.println(commands.d_gameStarter.d_gameMap.getCountries().keySet());
		System.out.println(commands.d_gameStarter.d_gameMap.getCountries().get("india").getNeighbourNames());

	}
}


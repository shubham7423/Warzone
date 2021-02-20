package controller;

import java.util.Scanner;

/**
 * Main class from where game starts, user commands are taken from here..
 *
 */
public class UserCommand {
	Scanner l_scannerScanner = new Scanner(System.in);
	
	/**
	 * Get commands from user
	 * 
	 * @return array containing command splitted using " ".
	 */
	public String[] getCommand() {
		String l_userCommand;
		System.out.print("$ ");
		l_userCommand = l_scannerScanner.nextLine();
		String[] l_splittedCommandString = l_userCommand.split(" ");
		return l_splittedCommandString;
	}

	/**
	 * Game can be started from here
	 * 
	 * @param args argument to main
	 */
	public static void main(String[] args) {
		Commands l_commands = new Commands();
		UserCommand l_userCommand = new UserCommand();

		System.out.println("Welcome to Warzone");
		while (true) {
			String[] l_splittedCommandString = l_userCommand.getCommand();
			if (l_splittedCommandString[0].equals("exit()")) {
				break;
			}
			System.out.println(l_commands.executeCommand(l_splittedCommandString));
		}
		System.out.print("\nThank you for playing Warzone :)");
		l_userCommand.l_scannerScanner.close();
	}
}

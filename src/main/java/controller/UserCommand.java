package controller;

import java.util.Scanner;

import controller.state.Phase;
import controller.state.edit.PreEdit;

/**
 * Main class from where game starts, user commands are taken from here..
 */
public class UserCommand {
	Scanner d_scannerScanner = new Scanner(System.in);

	public GameEngine d_gameEngine;

	/**
	 * constructor method to the class that sets the phase to the PreEdit phase to
	 * start the initial process of the game
	 */
	public UserCommand() {
		d_gameEngine = new GameEngine();
		d_gameEngine.setPhase(new PreEdit(d_gameEngine));
	}

	/**
	 * function to manually set the phase of the game depending upon the user
	 * 
	 * @param p the phase that has to be set for next steps in the game
	 */
	public void setPhase(Phase p) {
		d_gameEngine.setPhase(p);
	}

	/**
	 * Function the takes in the command provided by user at the console for further
	 * processing
	 * 
	 * @return the result of the execution of the command provided after being
	 *         splitted
	 */
	public String getCommand() {
		String l_userCommand;
		d_gameEngine.setUserCommand(this);
		System.out.print("$ ");
		l_userCommand = d_scannerScanner.nextLine();
		String[] l_splittedCommandString = l_userCommand.split(" ");
		if ("exit()".equals(l_splittedCommandString[0])) {
			return "exit()";
		}
		return d_gameEngine.executeCommand(l_splittedCommandString);
	}

	/**
	 * function that launches the game
	 */
	public void start() {
		System.out.println("Welcome to Warzone");
		new GameEngine().d_logEntryBuffer.setString("Game Started");
		UserCommand l_UserCommand = new UserCommand();
		while (true) {
			String l_commandOpt = l_UserCommand.getCommand();
			if ("exit()".equals(l_commandOpt)) {
				break;
			}
			System.out.println(l_commandOpt);
		}
		System.out.print("\nThank you for playing Warzone :)");
		l_UserCommand.d_scannerScanner.close();
	}

	/**
	 * Game starts from here
	 * 
	 * @param args argument to main
	 */
	public static void main(String[] args) {
		new UserCommand().start();
	}
}

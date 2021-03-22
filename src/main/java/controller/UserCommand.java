package controller;

import java.util.Scanner;

import controller.state.Phase;
import controller.state.edit.PreEdit;

/**
 * Main class from where game starts, user commands are taken from here..
 *
 */
public class UserCommand {
	Scanner l_scannerScanner = new Scanner(System.in);

	public GameEngine d_gameEngine;
	
	/**
	 * constructor method to the class that sets the phase to the PreEdit phase to start the initial process of the game
	 */
	public UserCommand() {
		d_gameEngine = new GameEngine();
		d_gameEngine.setPhase(new PreEdit(d_gameEngine));
//		d_gameStarter.setUserCommand(this);
	}
	
	/**
	 * function to manually set the phase of the game depending upon the user
	 * @param p the phase that has to be set for next steps in the game
	 */
	public void setPhase(Phase p) {
		d_gameEngine.setPhase(p);
	}
	
	/**
	 * Function the takes in the command provided by user at the console for further processing
	 * @return the result of the execution of the command provided after being splitted
	 */
	public String getCommand() {
		String l_userCommand;
//		System.out.println("GS is null: "+ d_gameStarter==null);
//		System.out.println(d_gameStarter.getPhase().getClass().getName());
		d_gameEngine.setUserCommand(this);
		System.out.print("$ ");
		l_userCommand = l_scannerScanner.nextLine();
		String[] l_splittedCommandString = l_userCommand.split(" ");
		if (l_splittedCommandString[0].equals("exit()")) {
			return "exit()";
		}
		return d_gameEngine.executeCommand(l_splittedCommandString);
	}

	/**
	 * Game can be started from here
	 * 
	 * @param args argument to main
	 */
//	public static void main(String[] args) {
//		Commands l_commands = new Commands();
//		UserCommand l_userCommand = new UserCommand();
//
//		System.out.println("Welcome to Warzone");
//		while (true) {
//			String[] l_splittedCommandString = l_userCommand.getCommand();
//			if (l_splittedCommandString[0].equals("exit()")) {
//				break;
//			}
//			System.out.println(l_commands.executeCommand(l_splittedCommandString));
//		}
//		System.out.print("\nThank you for playing Warzone :)");
//		l_userCommand.l_scannerScanner.close();
//	}
//	public static void main(String[] args) {
////		Commands l_commands = new Commands();
//		UserCommand l_userCommand = new UserCommand();
////		GameStarter l_gameStarter = new GameStarter();
////		l_gameStarter.setPhase(new PreEdit(l_gameStarter));
//		System.out.println("Welcome to Warzone");
//		while (true) {
////			String[] l_splittedCommandString = l_userCommand.getCommand(l_gameStarter);
////			if (l_splittedCommandString[0].equals("exit()")) {
////				break;
////			}
//			String l_commandOpt = l_userCommand.getCommand();
//			if(l_commandOpt.equals("exit()")) {
//				break;
//			}
//			System.out.println(l_commandOpt);
//		}
//		System.out.print("\nThank you for playing Warzone :)");
//		l_userCommand.l_scannerScanner.close();
//	}
}

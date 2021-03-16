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

	/**
	 * Get commands from user
	 * 
	 * @return l_splittedCommandString array containing command that is split using " ".
	 */
//	public String[] getCommand() {
//		String l_userCommand;
//		System.out.print("$ ");
//		l_userCommand = l_scannerScanner.nextLine();
//		String[] l_splittedCommandString = l_userCommand.split(" ");
//		return l_splittedCommandString;
//	}
	public GameEngine d_gameEngine;
	
	public UserCommand() {
		d_gameEngine = new GameEngine();
		d_gameEngine.setPhase(new PreEdit(d_gameEngine));
//		d_gameStarter.setUserCommand(this);
	}
	
	public void setPhase(Phase p) {
		d_gameEngine.setPhase(p);
	}
	
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

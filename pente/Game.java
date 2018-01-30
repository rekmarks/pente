package pente;

import java.util.Scanner;

import interfaces.Coordinate;
import interfaces.Player;
import interfaces.Stone;

/**
 * @author rekmarks
 * 
 * User interface with high-level game logic for Pente.
 */
public class Game {
	
	public static void main(String[] args) {
		
		// set up game variables
		MyBoard board = new MyBoard();
		Player red, yellow;
		Coordinate move;
		
		Scanner in = new Scanner(System.in);
		System.out.println("\nWelcome to Pente. Are you playing red or yellow?");
		String response = in.nextLine();
		
		// make human choose red or yellow
		while (true) {
			if (response.equalsIgnoreCase("red") || response.equalsIgnoreCase("r")) {
				red = new HumanPlayer(Stone.RED);
				yellow = new RekmarksPlayer(Stone.YELLOW);
				break;
			} else if (response.equalsIgnoreCase("yellow") || response.equalsIgnoreCase("y")) {
				red = new RekmarksPlayer(Stone.RED);
				yellow = new HumanPlayer(Stone.YELLOW);
				break;
			}
			System.out.println("Are you playing red or yellow?");
			response = in.nextLine();
		}
		
		System.out.println("For reference, the middle of the board is: 9, J");
		
		// main game loop
		// handles IllegalArgumentException from board but not
		// IllegalStateException if a player moves during the wrong turn
		// that shouldn't be possible, though
		do {
			
			System.out.println(board);
			System.out.println("Red's turn");
			
			// loop red move request until valid move is made
			while (true) {
				
				try {
					move = red.getMove(board);
					board.placeStone(Stone.RED, move);					
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid move!");
					continue;
				}
				break;
			}
			
			if (board.gameOver()) break;
			
			System.out.println(board);
			System.out.println("Yellow's turn");
			
			// loop yellow move request until valid move is made
			while (true) {
				
				try {
					move = yellow.getMove(board);
					board.placeStone(Stone.YELLOW, move);					
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid move!");
					continue;
				}
				break;
			}
			
		} while (!board.gameOver());
		
		// This closes System.in, which we really don't want to except at
		// the very end of our program.
		// It took me a while to figure that out.
		// I'm not bitter.
		in.close();
		
		System.out.println(board);
		
		if (board.getWinner() == Stone.RED) {
			System.out.println("Red player wins!");
		} else if (board.getWinner() == Stone.YELLOW) {
			System.out.println("Yellow player wins!");
		} else { // defensive programming
			throw new RuntimeException();
		}
	}
}

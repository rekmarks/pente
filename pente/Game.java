package pente;

import java.util.Scanner;

import interfaces.Coordinate;
import interfaces.Player;
import interfaces.Stone;

public class Game {
	
	public static void main(String[] args) {
		
		// set up game variables
		MyBoard board = new MyBoard();
		Player red, yellow;
		
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
		
		Coordinate move;
		
		// main game loop
		do {
			
			System.out.println(board);
			System.out.println("Red's turn");
			
			move = red.getMove(board);
			board.placeStone(Stone.RED, move);
			
			if (board.gameOver()) break;
			
			System.out.println(board);
			System.out.println("Yellow's turn");
			
			move = yellow.getMove(board);
			board.placeStone(Stone.YELLOW, move);
			
		} while (!board.gameOver());
		
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

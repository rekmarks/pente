package pente;
import java.util.Scanner;

import interfaces.Board;
import interfaces.Coordinate;
import interfaces.Player;
import interfaces.Stone;

public class HumanPlayer implements Player {
	
	private Stone stone;
	private Scanner in;
	
	/**
	 * Constructor
	 * @param s		stone type of player
	 */
	public HumanPlayer(Stone s) {
		
		// no empty stone players
		if (s == Stone.EMPTY) throw new IllegalArgumentException();
		
		stone = s;
	}

	@Override
	public Coordinate getMove(Board b) {
		
		Coordinate m;
		int row = -1, column = -1;
		String response;
		char colChar;
		
		in = new Scanner(System.in);
		
		System.out.println("What's your move, hotshot?\n");
		
		while (true) {
			
			// row
			System.out.print("row: ");
			
			if (in.hasNextInt()) {
				
				row = in.nextInt();
				in.nextLine();
				
			} else {
				
				System.out.println("Try a decimal number from 0 to 18.\n");
				continue;
			}
			
			if (row < 0 || row > 18) {
				System.out.println("Try a decimal number from 0 to 18.\n");
				continue;
			}	
			
			// column
			System.out.print("column: ");
			response = in.nextLine();
			
			if (response.length() > 1) {
				System.out.println("Try a single letter from 'A' to 'S'.\n");
				continue;
			}
			
			response = response.toUpperCase();
			colChar = response.charAt(0);
			column = (int) colChar;
			column = column - 65;
			
			if (column < 0 || column > 18) {
				System.out.println("Try a single letter from 'A' to 'S'.\n");
				continue;
			}
			
			break;
		}
		
		in.close();
		
		// try initializing return coordinates, make recursive call if failure
		try {
			if (row < 0 || column < 0) {
				throw new UnsupportedOperationException();
			}
			m = new MyCoordinate(row, column);
			return m;
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid move! Try again.");
			return this.getMove(b);
		}
		
		
	}

	@Override
	public Stone getStone() {
		
		return stone;
	}

}

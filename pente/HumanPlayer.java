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
		
		in = new Scanner(System.in);
		
		System.out.println("What's your move, hotshot?\n" +
		"(please submit in the format: ROW, COLUMN)\n");
		
		while (true) {
			
			System.out.print("Move: ");
			
			response = in.nextLine();
			
			if (response.length() == 4 || response.length() == 5) {
				
				String[] components = response.split(", ");
				
				if (components.length != 2) {
					System.out.println("Invalid format.");
					continue;
				}
				
				try {
					row = Integer.parseInt(components[0]);
				} catch (NumberFormatException n) {
					System.out.println("Invalid row.");
					continue;
				}
				
				if (row < 0 || row > 18) {
					System.out.println("Invalid row.");
					continue;
				}	
				
				components[1] = components[1].toUpperCase();
				column = (int) components[1].charAt(0);;
				column = column - 65;
				
				if (column < 0 || column > 18) {
					System.out.println("Invalid column.");
					continue;
				}
			}
			
			break;
		}
				
		m = new MyCoordinate(row, column);
		return m;
	}

	@Override
	public Stone getStone() {
		
		return stone;
	}

}

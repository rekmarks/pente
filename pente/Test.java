package pente;

import interfaces.Coordinate;
import interfaces.Stone;

public class Test {
	
	public static void main(String[] args) {
		
		MyBoard b = new MyBoard();
		
		HumanPlayer sune = new HumanPlayer(Stone.RED);
		
		Coordinate c = sune.getMove(b);
		
		System.out.println(c);
		
		int row = c.getRow();
		int col = c.getColumn();
	}

	public static void stones() {
		
		MyBoard board = new MyBoard();
		
		MyCoordinate c;
		
		c = new MyCoordinate(9,9);
		board.placeStone(Stone.RED, c);
		c = new MyCoordinate(1,1);
		board.placeStone(Stone.YELLOW, c);
		c = new MyCoordinate(0,0);
		board.placeStone(Stone.RED, c);
		
//		for (int i = 5; i < 10; i++) {
//			c = new MyCoordinate(6, i);
//			board.placeStone(Stone.RED, c);
//		}
//		for (int i = 5; i < 10; i++) {
//			c = new MyCoordinate(7, i);
//			board.placeStone(Stone.RED, c);
//		}
//		for (int i = 5; i < 10; i++) {
//			c = new MyCoordinate(8, i);
//			board.placeStone(Stone.RED, c);
//		}
		
//		for (int i = 14; i < 19; i++) {
//			c = new MyCoordinate(12, i);
//			board.placeStone(Stone.YELLOW, c);
//		}
		
//		for (int i = 14; i < 19; i++) {
//			c = new MyCoordinate(i, 10);
//			board.placeStone(Stone.RED, c);
//		}
		
//		for (int i = 14; i < 19; i++) {
//			c = new MyCoordinate(i, 18);
//			board.placeStone(Stone.YELLOW, c);
//		}
		

		// diagonals
		
//		c = new MyCoordinate(0,0);
//		board.placeStone(Stone.RED, c);
//		c = new MyCoordinate(0,1);
//		board.placeStone(Stone.RED, c);
//		c = new MyCoordinate(1,0);
//		board.placeStone(Stone.RED, c);
//		c = new MyCoordinate(0,2);
//		board.placeStone(Stone.RED, c);
//		c = new MyCoordinate(1,1);
//		board.placeStone(Stone.RED, c);
		
//		c = new MyCoordinate(8,8);
//		board.placeStone(Stone.RED, c);
//		c = new MyCoordinate(7,7);
//		board.placeStone(Stone.RED, c);
//		c = new MyCoordinate(9,9);
//		board.placeStone(Stone.RED, c);
//		c = new MyCoordinate(10,10);
//		board.placeStone(Stone.RED, c);
//		c = new MyCoordinate(11,11);
//		board.placeStone(Stone.RED, c);
		
		// begin diagonal edge cases
		
//		c = new MyCoordinate(0, 4);
//		board.placeStone(Stone.YELLOW, c);
//		c = new MyCoordinate(1, 3);
//		board.placeStone(Stone.YELLOW, c);
//		c = new MyCoordinate(2,2);
//		board.placeStone(Stone.YELLOW, c);
//		c = new MyCoordinate(3, 1);
//		board.placeStone(Stone.YELLOW, c);
//		c = new MyCoordinate(4, 0);
//		board.placeStone(Stone.YELLOW, c);
		
//		c = new MyCoordinate(0, 14);
//		board.placeStone(Stone.YELLOW, c);
//		c = new MyCoordinate(1, 15);
//		board.placeStone(Stone.YELLOW, c);
//		c = new MyCoordinate(2, 16);
//		board.placeStone(Stone.YELLOW, c);
//		c = new MyCoordinate(3, 17);
//		board.placeStone(Stone.YELLOW, c);
//		c = new MyCoordinate(4, 18);
//		board.placeStone(Stone.YELLOW, c);
		
//		c = new MyCoordinate(14,0);
//		board.placeStone(Stone.RED, c);
//		c = new MyCoordinate(15,1);
//		board.placeStone(Stone.RED, c);
//		c = new MyCoordinate(16,2);
//		board.placeStone(Stone.RED, c);
//		c = new MyCoordinate(17,3);
//		board.placeStone(Stone.RED, c);
//		c = new MyCoordinate(18,4);
//		board.placeStone(Stone.RED, c);
		
		c = new MyCoordinate(14, 18);
		board.placeStone(Stone.YELLOW, c);
		c = new MyCoordinate(15, 17);
		board.placeStone(Stone.YELLOW, c);
		c = new MyCoordinate(16, 16);
		board.placeStone(Stone.YELLOW, c);
		c = new MyCoordinate(17, 15);
		board.placeStone(Stone.YELLOW, c);
		c = new MyCoordinate(18, 14);
		board.placeStone(Stone.YELLOW, c);
		
		System.out.println(board);
		System.out.println("\n");
		
		if (board.gameOver()) System.out.println(board.getWinner());
		
	}

}

package pente;

import interfaces.Stone;

public class Test {

	public static void main(String[] args) {
		
		MyBoard board = new MyBoard();
		
		MyCoordinate c;
		
//		for (int i = 5; i < 10; i++) {
//			c = new MyCoordinate(6, i);
//			board.testPlaceStone(Stone.RED, c);
//		}
//		for (int i = 5; i < 10; i++) {
//			c = new MyCoordinate(7, i);
//			board.testPlaceStone(Stone.RED, c);
//		}
//		for (int i = 5; i < 10; i++) {
//			c = new MyCoordinate(8, i);
//			board.testPlaceStone(Stone.RED, c);
//		}
		
//		for (int i = 14; i < 19; i++) {
//			c = new MyCoordinate(12, i);
//			board.testPlaceStone(Stone.YELLOW, c);
//		}
		
//		for (int i = 14; i < 19; i++) {
//			c = new MyCoordinate(i, 10);
//			board.testPlaceStone(Stone.RED, c);
//		}
		
		for (int i = 14; i < 19; i++) {
			c = new MyCoordinate(i, 18);
			board.testPlaceStone(Stone.YELLOW, c);
		}
		

		// diagonals
		
//		c = new MyCoordinate(0,0);
//		board.testPlaceStone(Stone.RED, c);
//		c = new MyCoordinate(0,1);
//		board.testPlaceStone(Stone.RED, c);
//		c = new MyCoordinate(1,0);
//		board.testPlaceStone(Stone.RED, c);
//		c = new MyCoordinate(0,2);
//		board.testPlaceStone(Stone.RED, c);
//		c = new MyCoordinate(1,1);
//		board.testPlaceStone(Stone.RED, c);
		
//		c = new MyCoordinate(8,8);
//		board.testPlaceStone(Stone.RED, c);
//		c = new MyCoordinate(7,7);
//		board.testPlaceStone(Stone.RED, c);
//		c = new MyCoordinate(9,9);
//		board.testPlaceStone(Stone.RED, c);
//		c = new MyCoordinate(10,10);
//		board.testPlaceStone(Stone.RED, c);
//		c = new MyCoordinate(11,11);
//		board.testPlaceStone(Stone.RED, c);
		
		// begin diagonal edge cases
		
//		c = new MyCoordinate(0, 4);
//		board.testPlaceStone(Stone.YELLOW, c);
//		c = new MyCoordinate(1, 3);
//		board.testPlaceStone(Stone.YELLOW, c);
//		c = new MyCoordinate(2,2);
//		board.testPlaceStone(Stone.YELLOW, c);
//		c = new MyCoordinate(3, 1);
//		board.testPlaceStone(Stone.YELLOW, c);
//		c = new MyCoordinate(4, 0);
//		board.testPlaceStone(Stone.YELLOW, c);
		
//		c = new MyCoordinate(0, 14);
//		board.testPlaceStone(Stone.YELLOW, c);
//		c = new MyCoordinate(1, 15);
//		board.testPlaceStone(Stone.YELLOW, c);
//		c = new MyCoordinate(2, 16);
//		board.testPlaceStone(Stone.YELLOW, c);
//		c = new MyCoordinate(3, 17);
//		board.testPlaceStone(Stone.YELLOW, c);
//		c = new MyCoordinate(4, 18);
//		board.testPlaceStone(Stone.YELLOW, c);
		
//		c = new MyCoordinate(14,0);
//		board.testPlaceStone(Stone.RED, c);
//		c = new MyCoordinate(15,1);
//		board.testPlaceStone(Stone.RED, c);
//		c = new MyCoordinate(16,2);
//		board.testPlaceStone(Stone.RED, c);
//		c = new MyCoordinate(17,3);
//		board.testPlaceStone(Stone.RED, c);
//		c = new MyCoordinate(18,4);
//		board.testPlaceStone(Stone.RED, c);
		
//		c = new MyCoordinate(14, 18);
//		board.testPlaceStone(Stone.RED, c);
//		c = new MyCoordinate(15, 17);
//		board.testPlaceStone(Stone.RED, c);
//		c = new MyCoordinate(16, 16);
//		board.testPlaceStone(Stone.RED, c);
//		c = new MyCoordinate(17, 15);
//		board.testPlaceStone(Stone.RED, c);
//		c = new MyCoordinate(18, 14);
//		board.testPlaceStone(Stone.RED, c);
		
		System.out.println(board);
		System.out.println("\n");
		
		if (board.gameOver()) System.out.println(board.getWinner());
	}

}

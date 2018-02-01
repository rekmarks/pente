package pente;
import interfaces.Board;
import interfaces.Coordinate;
import interfaces.Stone;

/**
 * @author rekmarks
 * 
 * Board implementation. Throws IllegalArgumentException in placeStone
 * for recoverable errors, IllegalStateException otherwise (one case in
 * placeStone and all exceptions in all other methods).
 */
public class MyBoard implements Board {
	
	private Stone[][] board;
	private final int[][] directions = {{-1, 0},{-1, 1},{0, 1},{1, 1},{1, 0},{1, -1},{0, -1},{-1, -1}};
	private int moveNumber, redCaptures, yellowCaptures;
	private Stone winner;
	
	/**
	 * Constructor
	 * 
	 * Initializes instance variables.
	 * No values are arbitrary.
	 */
	public MyBoard() {
		
		moveNumber = 0;
		winner = Stone.EMPTY;
		redCaptures = 0;
		yellowCaptures = 0;
		
		board = new Stone[19][19];
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				
				board[i][j] = Stone.EMPTY;
			}
		}
		
	}

	@Override
	public void placeStone(Stone s, Coordinate c) {
				
		// ensure coordinate is not out of bounds
		if (isOutOfBounds(c)) throw new IllegalArgumentException();
		
		// ensure player is not placing empty stone
		if (s == Stone.EMPTY) throw new IllegalArgumentException();
		
		// ensure player moves during correct turn
		// IllegalStateException
		if (moveNumber % 2 == 0 && s != Stone.RED) throw new IllegalStateException();
		
		// ensure intersection is unoccupied
		if (pieceAt(c) != Stone.EMPTY) throw new IllegalArgumentException();
		
		// ensure red places stone in center on first move
		if (moveNumber == 0 && (c.getRow() != 9 || c.getColumn() != 9) ) 
			throw new IllegalArgumentException();
		
		// ensure red can't place stone within 3 intersections of center on 
		// their second move
		if (moveNumber == 2 && (	(c.getRow() < 13 && c.getRow() > 5 ) 
							 &&	(c.getColumn() < 13 && c.getColumn() > 5 ) ))
			throw new IllegalArgumentException();
		
		// if we get here, move is legal, so make it
		board[c.getRow()][c.getColumn()] = s;
		moveNumber++;
		
		// check for captures
		for (int i = 0; i< directions.length; i++) {
			
			if (capture(c, directions[i][0], directions[i][1])) {
				if (s == Stone.RED) redCaptures++;
				else yellowCaptures++;
			}
		}		
	}
	
	/**
	 * Handles captures, both checking and removal, after placing new stone
	 * @param c			coordinate of newly placed stone
	 * @param rowDir		1 or -1, specifying the row direction to check
	 * @param colDir		1 or -1, specifying the column direction to check
	 * @return			true if capture occurred; false otherwise
	 */
	private boolean capture(Coordinate c, int rowDir, int colDir) {
		
		// input validation
		if (	   (rowDir < -1 || rowDir > 1) 
			|| (colDir < -1 || colDir > 1) 
			|| (rowDir == 0 && colDir == 0) ) throw new IllegalStateException();
		
		// coordinate of stone 3 intersections away  in desired direction
		Coordinate p = new MyCoordinate(c.getRow() + 3 * rowDir, c.getColumn() + 3 * colDir);
		
		// can't capture without sufficient space
		if (isOutOfBounds(p)) return false;
		
		// check if piece three intersections away is the same as newly placed stone
		if (pieceAt(p) == pieceAt(c)) {
			
			p = new MyCoordinate(c.getRow() + 2 * rowDir, c.getColumn() + 2 * colDir);
			
			// check if piece two intersections away is the opposite of newly placed stone
			if (pieceAt(p) != Stone.EMPTY && pieceAt(p) != pieceAt(c)) {
				
				Coordinate q = new MyCoordinate(c.getRow() + rowDir, c.getColumn() + colDir);
				
				if (pieceAt(q) != Stone.EMPTY && pieceAt(q) != pieceAt(c)) {
					
					// capture if stone one intersection away is the opposite of
					// newly placed stone
					removeStone(p);
					removeStone(q);
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * "Places" a Stone.EMPTY at specified coordinated
	 * @param c		intersection to empty
	 */
	private void removeStone(Coordinate c) {
		
		if (isOutOfBounds(c)) throw new IllegalStateException();
		
		board[c.getRow()][c.getColumn()] = Stone.EMPTY;
	}

	@Override
	public Stone pieceAt(Coordinate c) {
		
		if (isOutOfBounds(c)) throw new IllegalStateException();
		
		return board[c.getRow()][c.getColumn()];
	}

	@Override
	public boolean isOutOfBounds(Coordinate c) {
		
		if (c.getRow() < 0 || c.getRow() > 18 || c.getColumn() < 0 || c.getColumn() > 18) {
			return true;
		} 
		
		return false;
	}

	@Override
	public boolean isEmpty(Coordinate c) {
		
		if (isOutOfBounds(c)) throw new IllegalStateException();
		
		return (board[c.getRow()][c.getColumn()] == Stone.EMPTY);		
	}

	@Override
	public int getMoveNumber() {
		
		return moveNumber;
	}

	@Override
	public int getRedCaptures() {
		
		return redCaptures;
	}

	@Override
	public int getYellowCaptures() {
		
		return yellowCaptures;
	}
	
	@Override
	public boolean gameOver() {
		
		// check captures
		if (redCaptures == 5) {
			winner = Stone.RED;
			return true;
		} else if (yellowCaptures == 5) {
			winner = Stone.YELLOW;
			return true;
		}
		
		// check for 5 in a row
		Stone result = Stone.EMPTY;
		
		for (int i = 0; 	i < 19; i++) { // rows			
			for (int j = 0; j < 19; j++) { // columns
				
				// rows
				if (j == 0) result = checkFive(0, i, j);
				if (result != Stone.EMPTY) {
					winner = result;
					return true;
				}
				
				// columns
				if (i == 0) result = checkFive(1, i, j);
				if (result != Stone.EMPTY) {
					winner = result;
					return true;
				}
				
				// ascending diagonals
				if (i > 3 && j < 15) result = checkFive(2, i, j);
				if (result != Stone.EMPTY) {
					winner = result;
					return true;
				}
				
				// descending diagonals
				if (i < 15 && j < 15) result = checkFive(3, i, j);
				if (result != Stone.EMPTY) {
					winner = result;
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Checks rows, columns, or ascending or descending diagonals for five 
	 * stones in a row and immediately returns the type if found
	 * 
	 * Must be initiated for a specific intersection, after which it will scan 
	 * from that intersection per the direction parameter
	 * 
	 * @param direction		0: check row
	 * 						1: check column
	 * 						2: check ascending diagonal
	 * 						3: check descending diagonal
	 * @param r				starting intersection row
	 * @param c				starting intersection column
	 * @return				Stone type of five in a row if found;
	 * 						Stone.EMPTY otherwise
	 */
	private Stone checkFive(int direction, int r, int c) {
		
		// either rows, columns, or ascending or descending diagonals
		if (direction < 0 || direction > 3) throw new IllegalStateException();
				
		Stone current, previous = Stone.EMPTY;
		int count = 0, row = r, column = c;
		
		while (row >= 0 && row < 19 && column >= 0 && column < 19) {
			
			// current intersection
			current = board[row][column];
			
			// check current stone type, increment count as appropriate
			if (current == Stone.EMPTY) {
				
				count = 0;
				
			} else if (current == Stone.RED) {
				
				if (previous != Stone.RED) count = 1;
				else count++;
				
			} else {
				
				if (previous != Stone.YELLOW) count = 1;
				else count++;
				
			}
			
			// if count is 5, return the winner
			if (count == 5) {
				return current;
			}
			
			// if no winner, increment/decrement row and/or column depending on
			// specification
			if (direction < 2) { // rows and columns
				if (direction == 0) {
					column++;
				} else {
					row++;
				}
			}
			else { // diagonals
				if (direction == 2) { // ascending, i.e. from bottom-left to top-right
					row--;
					column++;
				} else { // descending, i.e. from top-left to bottom-right
					row++;
					column++;
				}
			}
			
			previous = current;			
		}
		
		return Stone.EMPTY;
	}
	
	@Override
	public Stone getWinner() {
		
		return winner;
	}
	
	/**
	 * The board is printed as a grid:
	 * Red Stones as an "O" character (capital O)
	 * Yellow Stones as an "X" character (capital X)
	 * Empty Stones as a space character " "
	 * Board demarcated by "-" characters
	 * Rows marked 0-18, columns A-S
	 */	
	@Override
	public String toString() {
		
		StringBuilder b = new StringBuilder("\n");
		
		b.append("      A B C D E F G H I J K L M N O P Q R S   \n");
		
		for (int i = 0; i < 19; i++) {
			
			b.append("   " + i + " ");
			if (i < 10) b.append(' ');
			
			for (int j = 0; j < 19; j++) {
												
				if (board[i][j] == Stone.EMPTY) {
					b.append(' ');
				} else if (board[i][j] == Stone.RED) {
					b.append('O');
				} else {
					b.append('X');
				}
				if (j <18) b.append('-');
			}
			
			b.append('\n');
		}
		
		b.append("\n\n");
		
		return b.toString();
	}
}

package pente;
import interfaces.Board;
import interfaces.Coordinate;
import interfaces.Player;
import interfaces.Stone;

public class MyBoard implements Board {
	
	private Stone[][] board;
	private int moveNumber, redCaptures, yellowCaptures;
//	private Player red, yellow;
	private Stone winner;
	
	public MyBoard() {
		
//		red = r;
//		yellow = y;
		
		winner = Stone.EMPTY;
		
		board = new Stone[19][19];
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				
				board[i][j] = Stone.EMPTY;
			}
		}
		
	}

	@Override
	public void placeStone(Stone s, Coordinate c) {
		
		// TODO: increment moveNumber upon successful placement
		
	}
	
	public void testPlaceStone(Stone s, Coordinate c) {
		
		board[c.getRow()][c.getColumn()] = s;
		
	}

	@Override
	public Stone pieceAt(Coordinate c) {
		
		return board[c.getRow()][c.getColumn()];
	}

	@Override
	public boolean isOutOfBounds(Coordinate c) {
		
		if (c.getRow() < 0 || c.getRow() > 17 || c.getColumn() < 0 || c.getColumn() > 17) {
			return true;
		} 
		
		return false;
	}

	@Override
	public boolean isEmpty(Coordinate c) {
		
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

	
	public boolean gameOverOrig() {
		
		// check captures
		if (redCaptures == 5) {
			winner = Stone.RED;
			return true;
		} else if (yellowCaptures == 5) {
			winner = Stone.YELLOW;
			return true;
		}
		
		// check for 5 in a row
		Stone result;
		
		// check rows and columns
		for (int i = 0; i < 19; i++) {				
			
			// row
			result = scanRowOrColumn(true, i);
			
			if (result != Stone.EMPTY) {
				winner = result;
				return true;
			}
			
			// column
			result = scanRowOrColumn(false, i);
			
			if (result != Stone.EMPTY) {
				winner = result;
				return true;
			}
		}
		
		// check diagonals
		for (int i = 0; 	i < 19; i++) { // rows
			for (int j = 0; j < 19; j++) { // columns
				
				// ascending diagonals too short outside certain indices
				if (i > 3 && j < 15) {
					result = scanDiagonal(true, i, j);
					
					if (result != Stone.EMPTY) {
						winner = result;
						return true;
					}
				}
				
				// descending diagonals too short outside certain indices
				if (i < 15 && j < 15) {
					result = scanDiagonal(false, i, j);
					
					if (result != Stone.EMPTY) {
						winner = result;
						return true;
					}
				}
			}
		}
		
		return false;
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
				
				// ascending diagonals too short outside certain indices
				if (i > 3 && j < 15) result = checkFive(2, i, j);
				if (result != Stone.EMPTY) {
					winner = result;
					return true;
				}
				
				
				// descending diagonals too short outside certain indices
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
	 * Checks row or column for five stones of the same type (not empty) and
	 * returns the type
	 * @param row		true: will scan a row; false: will scan a column
	 * @param index		board index of row or column
	 * @return			Stone of winner's type or Stone.EMPTY if no winner
	 */
	private Stone scanRowOrColumn(boolean row, int index) {
		
		Stone current, previous = Stone.EMPTY;
		int count = 0;
		
		for (int i = 0; i < 19; i++) {
			
			// scan row or column depending on parameter
			if (row) {
				current = board[index][i];
			} else {
				current = board[i][index];
			}
			
			// check current stone type, increment count as appropriate
			if (current == Stone.EMPTY) {
				
				count = 0;
				
			} else if (current == Stone.RED) {
				
				if (previous != Stone.RED) count = 0;
				count++;
				
			} else {
				
				if (previous != Stone.YELLOW) count = 0;
				count++;
				
			}
			
			// if count is 5, return the winner
			if (count == 5) {
				if (current == Stone.RED) {
					
					return Stone.RED;
					
				} else {
					
					return Stone.YELLOW;
				}
			}
			
			previous = current;
		}
		
		return Stone.EMPTY;
	}
	
	/**
	 * Checks diagonal for five stones of the same type (not empty) and
	 * returns the type
	 * @param ascending		true: index increasing; false: index decreasing
	 * @param r			starting tile row
	 * @param c			starting tile column
	 * @return
	 */
	private Stone scanDiagonal(boolean ascending, int r, int c) {
				
		Stone current, previous = Stone.EMPTY;
		int count = 0, row = r, column = c;
		
		while (row >= 0 && row < 19 && column >= 0 && column < 19) {
			
			current = board[row][column];
			
			// check current stone type, increment count as appropriate
			if (current == Stone.EMPTY) {
				
				count = 0;
				
			} else if (current == Stone.RED) {
				
				if (previous != Stone.RED) count = 0;
				count++;
				
			} else {
				
				if (previous != Stone.YELLOW) count = 0;
				count++;
				
			}
			
			// if count is 5, return the winner
			if (count == 5) {
				if (current == Stone.RED) {
					
					return Stone.RED;
					
				} else {
					
					return Stone.YELLOW;
				}
			}
			
			// increment or decrement indices per parameter
			if (ascending) { // e.g. from bottom-left to top-right
				row--;
				column++;
			} else { // e.g. from top-left to bottom-right
				row++;
				column++;
			}
			
			previous = current;			
		}
		
		return Stone.EMPTY;
	}
	
	/**
	 * Checks diagonal for five stones of the same type (not empty) and
	 * returns the type
	 * @param ascending		true: index increasing; false: index decreasing
	 * @param r			starting tile row
	 * @param c			starting tile column
	 * @return
	 */
	private Stone checkFive(int direction, int r, int c) {
		
		// either rows, columns, or ascending or descending diagonals
		if (direction < 0 || direction > 3) throw new IllegalArgumentException();
				
		Stone current, previous = Stone.EMPTY;
		int count = 0, row = r, column = c;
		
		while (row >= 0 && row < 19 && column >= 0 && column < 19) {
			
			// current tile
			current = board[row][column];
			
			// check current stone type, increment count as appropriate
			if (current == Stone.EMPTY) {
				
				count = 0;
				
			} else if (current == Stone.RED) {
				
				if (previous != Stone.RED) count = 0;
				count++;
				
			} else {
				
				if (previous != Stone.YELLOW) count = 0;
				count++;
				
			}
			
			// if count is 5, return the winner
			if (count == 5) {
				if (current == Stone.RED) {
					
					return Stone.RED;
					
				} else {
					
					return Stone.YELLOW;
				}
			}
			
			// increment/decrement row and/or column depending upon specification
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
	 * The board should be printed as a grid:
	 * Red Stones should print as an "O" character (capital O)
	 * Yellow Stones should print as an "X" character (capital X)
	 * Empty Stones should print as a space character " "
	 */	
	@Override
	public String toString() {
		
		StringBuilder b = new StringBuilder("\n");
		
		b.append("      A B C D E F G H I J K L M N O P Q R S   \n");
		
		for (int i = 0; i < 19; i++) {
			
			b.append("   " + i + " ");
			if (i < 10) b.append(' ');
			
			for (int j = 0; j < 19; j++) {
				
//				if (i == 0) b.append(j + " ");
								
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

package pente;
import interfaces.Coordinate;

public class MyCoordinate implements Coordinate {
	
	private int row, column;
	
	/**
	 * @param r
	 * @param c
	 */
	public MyCoordinate(int r, int c) {
		
		row = r;
		column = c;
	}

	@Override
	public int getRow() {
		return row;
	}

	@Override
	public int getColumn() {
		return column;
	}
	
	@Override
	public String toString() {
		
		String out = "(" + Integer.toString(row) + ", " 
					 + Integer.toString(column) + ")";
		
		return out;
	}

}

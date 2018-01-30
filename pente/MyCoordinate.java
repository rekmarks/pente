package pente;
import interfaces.Coordinate;

public class MyCoordinate implements Coordinate {
	
	private int row, column;
	
	/**
	 * @param r
	 * @param c
	 */
	public MyCoordinate(int r, int c) {
		
		// input validation here because boy does that make my life easier
//		if (r < 0 || r > 18 || c < 0 || c > 18) {
//			throw new IllegalArgumentException();
//		} 
		
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
		
		//TODO
		
		return null;
	}

}

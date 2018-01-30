package pente;
import interfaces.Board;
import interfaces.Coordinate;
import interfaces.Player;
import interfaces.Stone;
import java.util.concurrent.ThreadLocalRandom;

public class RekmarksPlayer implements Player {
	
private Stone stone;
	
	/**
	 * Constructor
	 * @param s		stone type of player
	 */
	public RekmarksPlayer(Stone s) {
		
		// no empty stone players
		if (s == Stone.EMPTY) throw new IllegalArgumentException();
		
		stone = s;
	}


	@Override
	public Coordinate getMove(Board b) {
		
		Coordinate move = null;
		int r, c;
		
		if (b.getMoveNumber() == 0) { // only the center on red's first move
			
			move = new MyCoordinate(9 ,9);
			return move;
			
		// choose between two valid locations on red's second move
		} else if (b.getMoveNumber() == 2) {
			
			move = new MyCoordinate(9, 5);
			
			if (b.pieceAt(move) != Stone.EMPTY) {
				move = new MyCoordinate(9, 13);
			}
			
		// pick a random move
		} else {
			
			while (move == null) {
				
				r = ThreadLocalRandom.current().nextInt(0, 19);
				c = ThreadLocalRandom.current().nextInt(0, 19);
				
				move = new MyCoordinate(r, c);
				
				if (b.pieceAt(move) != Stone.EMPTY) {
					move = null;
				}
			}
		}
		
		return move;
	}

	@Override
	public Stone getStone() {
		
		return stone;
	}

}

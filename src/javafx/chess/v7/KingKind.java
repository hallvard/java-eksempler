package javafx.chess.v7;

public class KingKind extends PieceKind {

	@Override
	public String toString() {
		return "King";
	}

	@Override
	public void checkMove(Piece piece, char toLine, int toRow, Chess chess) {
		var adl = Math.abs(toLine - piece.getColumn());
		var adr = Math.abs(toRow - piece.getRow());
		if (adl > 1 || adr > 1) {
			throw new IllegalArgumentException("A King can only move one square in any direction");
		}
	}
}

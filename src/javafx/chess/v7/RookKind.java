package javafx.chess.v7;

public class RookKind extends PieceKind {

	@Override
	public String toString() {
		return "Rook";
	}

	@Override
	public void checkMove(Piece piece, char toLine, int toRow, Chess chess) {
		var adl = Math.abs(toLine - piece.getColumn());
		var adr = Math.abs(toRow - piece.getRow());
		if (adl != 0 && adr != 0) throw new IllegalArgumentException("A Rook must move horizontally or vertically");
		else checkNoPieceInBetween(piece, toLine, toRow, chess);
	}
}

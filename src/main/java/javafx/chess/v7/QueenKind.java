package javafx.chess.v7;

public class QueenKind extends PieceKind {

	@Override
	public String toString() {
		return "Queen";
	}

	@Override
	public void checkMove(Piece piece, char toLine, int toRow, Chess chess) {
		var adl = Math.abs(toLine - piece.getColumn());
		var adr = Math.abs(toRow - piece.getRow());
		if (adl != adr && adl != 0 && adr != 0) throw new IllegalArgumentException("A Queen  must move horizontal, vertically or diagonally");
		else checkNoPieceInBetween(piece, toLine, toRow, chess);
	}
}

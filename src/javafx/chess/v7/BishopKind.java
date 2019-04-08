package javafx.chess.v7;

public class BishopKind extends PieceKind {

	@Override
	public String toString() {
		return "Bishop";
	}

	@Override
	public void checkMove(Piece piece, char toLine, int toRow, Chess chess) {
		var adl = Math.abs(toLine - piece.getColumn());
		var adr = Math.abs(toRow - piece.getRow());
		if (adl != adr) throw new IllegalArgumentException("A Bishop must move diagonally");
		else checkNoPieceInBetween(piece, toLine, toRow, chess);
	}
}

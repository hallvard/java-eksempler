package javafx.chess.v7;

public class KnightKind extends PieceKind {

	@Override
	public String toString() {
		return "kNight";
	}

	@Override
	public void checkMove(Piece piece, char toLine, int toRow, Chess chess) {
		var dl = toLine - piece.getColumn();
		var adl = Math.abs(dl);
		var dr = toRow - piece.getRow();
		var adr = Math.abs(dr);
		if (adl * adr != 2) throw new IllegalArgumentException("A Knight must move one square in one direction and two in the other");
	}
}

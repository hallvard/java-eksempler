package javafx.chess.v7;

public class PawnKind extends PieceKind {

	@Override
	public String toString() {
		return "Pawn";
	}

	@Override
	public void checkMove(Piece piece, char toLine, int toRow, Chess chess) {
		var adl = Math.abs(toLine - piece.getColumn());
		var dr = toRow - piece.getRow();
		var adr = Math.abs(dr);

		if (chess.findPieceAt(toLine, toRow) != null) {
			if ((adl != 1) || adr != 1 || (piece.isWhite() != (dr == 1))) throw new IllegalArgumentException("A Pawn can only capture a piece one square ahead and to either side");
			return;
		} else {
			if (adl != 0) {
				throw new IllegalArgumentException("A Pawn must move straight forward");
			} else if (piece.isWhite()) {
				if (dr != 1 && (piece.getRow() != 2 || dr != 2)) throw new IllegalArgumentException("A Pawn must either move one square ahead or two squares from its initial position");
				return;
			} else {
				if (dr != -1 && (piece.getRow() != 7 || dr != -2)) throw new IllegalArgumentException("A Pawn must either move one square ahead or two squares from its initial position");
				return;
			}
		}
	}
}

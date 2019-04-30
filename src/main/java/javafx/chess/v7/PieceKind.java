package javafx.chess.v7;

import java.util.Collection;
import java.util.List;

public abstract class PieceKind {

	public char getSymbol() {
		String s = toString();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isUpperCase(c)) {
				return c;
			}
		}
		throw new UnsupportedOperationException("getSymbol does not work with the toString method");
	}
	
	public abstract void checkMove(Piece piece, char toLine, int toRow, Chess chess);
	
	protected void checkNoPieceInBetween(Piece piece, char toLine, int toRow, Chess chess) {
		if (chess.isPieceBetween(piece, toLine, toRow, chess.findPieceAt(toLine, toRow))) {
			throw new IllegalArgumentException("A " + this + " cannot jump over other pieces");
		}
	}

	private static Collection<PieceKind> ALL_KINDS = List.of(
			new KingKind(),
			new BishopKind(),
			new QueenKind(),
			new RookKind(),
			new KnightKind(),
			new PawnKind()
	);
	
	public static PieceKind of(char kind) {
		for (PieceKind pieceKind : ALL_KINDS) {
			if (pieceKind.getSymbol() == kind) {
				return pieceKind;
			}
		}
		return null;
	}
}

package javafx.chess.v3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Chess implements Iterable<Piece> {

	private List<Piece> allPieces = new ArrayList<>();

	public final static Iterable<String> startPieces = List.of(
			"bRa8", "bNb8", "bBc8", "bQd8", "bKe8", "bBf8", "bNg8", "bRh8",
			"bPa7", "bPb7", "bPc7", "bPd7", "bPe7", "bPf7", "bPg7", "bPh7",
			"wPa2", "wPb2", "wPc2", "wPd2", "wPe2", "wPf2", "wPg2", "wPh2",
			"wRa1", "wNb1", "wBc1", "wQd1", "wKe1", "wBf1", "wNg1", "wKh1"
	);

	@Override
	public Iterator<Piece> iterator() {
		return allPieces.iterator();
	}

	public Chess() {
		this(startPieces);
	}

	public Chess(Iterable<String> startPieces) {
		for (var s : startPieces) {
			allPieces.add(new Piece(s.charAt(0) == 'w', s.charAt(1), s.charAt(2), s.charAt(3) - '0'));
		}
	}
	
	public String move(char fromLine, int fromRow, char toLine, int toRow, boolean reallyDo) {
		Piece.checkTo(fromLine, fromRow);
		Piece.checkTo(toLine, toRow);
		var fromPiece = findPieceAt(fromLine, fromRow);
		if (fromPiece == null) {
			return "There is no piece at " + fromLine + fromRow;			
		} else if (fromLine == toLine && fromRow == toRow) {
			return "A piece cannot move to the same square";
		} else {
			var toPiece = findPieceAt(toLine, toRow);
			var error = checkMove(fromPiece, toLine, toRow, toPiece);
			if (error != null) {
				return error;
			} else {
				if (toPiece != null) {
					if (fromPiece.isWhite() == toPiece.isWhite()) {
						return "A piece cannot capture one of the same color";
					} else if (reallyDo) {
						allPieces.remove(toPiece);
					}
				}
				if (reallyDo) {
					fromPiece.moveTo(toLine, toRow);
				}
			}
		}
		return null;
	}
	
	private String checkMove(Piece piece, char toLine, int toRow, Piece takes) {
		int dl = toLine - piece.getLine(), adl = Math.abs(dl);
		int dr = toRow - piece.getRow(), adr = Math.abs(dr);
		switch (piece.getKind()) {
		case 'K':
			if (adl > 1 || adr > 1) return "A King can only move one square in any direction";
			else if (isPieceBetween(piece, toLine, toRow, takes)) return "A King cannot jump over other pieces";
			else return null;
		case 'B':
			if (adl != adr) return "A Bishop must move diagonally";
			else if (isPieceBetween(piece, toLine, toRow, takes)) return "A Bishop cannot jump over other pieces";
			else return null;
		case 'R':
			if (adl != 0 && adr != 0) return "A Rook must move horizontally or vertically";
			else if (isPieceBetween(piece, toLine, toRow, takes)) return "A Rook cannot jump over other pieces";
			else return null;
		case 'Q':
			if (adl != adr && adl != 0 && adr != 0) return "A Queen  must move horizontal, vertically or diagonally";
			else if (isPieceBetween(piece, toLine, toRow, takes)) return "A Queen cannot jump over other pieces";
			else return null;
		case 'N':
			if (adl * adr != 2) return "A Knight must move one square in one direction and two in the other";
			else return null;
		case 'P': {
			if (takes != null) {
				if ((adl != 1) || adr != 1 || (piece.isWhite() != (dr == 1))) return "A Pawn can only capture a piece one square ahead and to either side";
				return null;
			} else {
				if (adl != 0) {
					return "A Pawn must move straight forward";
				} else if (piece.isWhite()) {
					if (dr != 1 && (piece.getRow() != 2 || dr != 2)) return "A Pawn must either move one square ahead or two squares from its initial position";
					return null;
				} else {
					if (dr != -1 && (piece.getRow() != 7 || dr != -2)) return "A Pawn must either move one square ahead or two squares from its initial position";
					return null;
				}
			}
		}
		}
		return null;
	}

	private boolean isPieceBetween(Piece piece, char toLine, int toRow, Piece takes) {
		for (Piece other : allPieces) {
			if (other != piece && (takes == null || other != takes) && isBetween(piece.getLine(), piece.getRow(), other.getLine(), other.getRow(), toLine, toRow)) {
				return true;
			}
		}
		return false;
	}

	private boolean isBetween(char line1, int row1, char line, int row, char line2, int row2) {
		return Math.signum(line - line1) == Math.signum(line2 - line) && Math.signum(row - row1) == Math.signum(row2 - row);
	}

	public Piece findPieceAt(char line, int row) {
		for (var piece : allPieces) {
			if (piece.getLine() == line && piece.getRow() == row) {
				return piece;
			}
		}
		return null;
	}
}

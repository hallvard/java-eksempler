package javafx.chess.v4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Chess implements Iterable<Piece> {

	private List<Piece> allPieces = new ArrayList<>();

	private final static Iterable<String> startPieces = List.of(
			"bRa8", "bNb8", "bBc8", "bQd8", "bKe8", "bBf8", "bNg8", "bRh8",
			"bPa7", "bPb7", "bPc7", "bPd7", "bPe7", "bPf7", "bPg7", "bPh7",
			"wPa2", "wPb2", "wPc2", "wPd2", "wPe2", "wPf2", "wPg2", "wPh2",
			"wRa1", "wNb1", "wBc1", "wQd1", "wKe1", "wBf1", "wNg1", "wRh1"
	);

	public Chess() {
		for (var s : startPieces) {
			allPieces.add(new Piece(s.charAt(0) == 'w', s.charAt(1), s.charAt(2), s.charAt(3) - '0'));
		}
	}
	
	@Override
	public Iterator<Piece> iterator() {
		return allPieces.iterator();
	}
	
	private boolean whitesTurn = true;
	
	public boolean isWhitesTurn() {
		return whitesTurn;
	}

	public String move(char fromCol, int fromRow, char toCol, int toRow, boolean reallyDo) {
		Piece.checkTo(fromCol, fromRow);
		Piece.checkTo(toCol, toRow);
		var fromPiece = findPieceAt(fromCol, fromRow);
		if (fromPiece == null) {
			return "There is no piece at " + fromCol + fromRow;			
		} else if (fromPiece.isWhite() != whitesTurn) {
			return "Cannot move the opponent's piece";
		} else if (fromCol == toCol && fromRow == toRow) {
			return "A piece cannot move to the same square";
		} else {
			var toPiece = findPieceAt(toCol, toRow);
			var error = checkMove(fromPiece, toCol, toRow, toPiece);
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
					fromPiece.moveTo(toCol, toRow);
					whitesTurn = ! whitesTurn;
				}
			}
		}
		return null;
	}
	
	private String checkMove(Piece piece, char toCol, int toRow, Piece takes) {
		int dl = toCol - piece.getColumn(), adl = Math.abs(dl);
		int dr = toRow - piece.getRow(), adr = Math.abs(dr);
		switch (piece.getKind()) {
		case 'K':
			if (adl > 1 || adr > 1) return "A King can only move one square in any direction";
			else if (isPieceBetween(piece, toCol, toRow, takes)) return "A King cannot jump over other pieces";
			else return null;
		case 'B':
			if (adl != adr) return "A Bishop must move diagonally";
			else if (isPieceBetween(piece, toCol, toRow, takes)) return "A Bishop cannot jump over other pieces";
			else return null;
		case 'R':
			if (adl != 0 && adr != 0) return "A Rook must move horizontally or vertically";
			else if (isPieceBetween(piece, toCol, toRow, takes)) return "A Rook cannot jump over other pieces";
			else return null;
		case 'Q':
			if (adl != adr && adl != 0 && adr != 0) return "A Queen  must move horizontal, vertically or diagonally";
			else if (isPieceBetween(piece, toCol, toRow, takes)) return "A Queen cannot jump over other pieces";
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

	private boolean isPieceBetween(Piece piece, char toColumn, int toRow, Piece takes) {
		for (Piece other : allPieces) {
			if (other != piece && (takes == null || other != takes) && isBetween(piece.getColumn(), piece.getRow(), other.getColumn(), other.getRow(), toColumn, toRow)) {
				return true;
			}
		}
		return false;
	}

	private boolean isBetween(char col1, int row1, char col, int row, char col2, int row2) {
		int dl = (int) Math.signum(col2 - col1), dr = (int) Math.signum(row2 - row1);
		while (col1 != col2 || row1 != row2) {
			if (col1 == col && row1 == row) {
				return true;
			}
			col1 += dl;
			row1 += dr;
		}
		return false;
	}

	public Piece findPieceAt(char col, int row) {
		for (var piece : allPieces) {
			if (piece.getColumn() == col && piece.getRow() == row) {
				return piece;
			}
		}
		return null;
	}
}

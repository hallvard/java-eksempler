package javafx.chess.v5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Chess implements Iterable<Piece> {

	private List<Piece> allPieces = new ArrayList<>();

	public final static Iterable<String> startPieces = List.of(
			"bRa8", "bNb8", "bBc8", "bQd8", "bKe8", "bBf8", "bNg8", "bRh8",
			"bPa7", "bPb7", "bPc7", "bPd7", "bPe7", "bPf7", "bPg7", "bPh7",
			"wPa2", "wPb2", "wPc2", "wPd2", "wPe2", "wPf2", "wPg2", "wPh2",
			"wRa1", "wNb1", "wBc1", "wQd1", "wKe1", "wBf1", "wNg1", "wRh1"
	);

	@Override
	public Iterator<Piece> iterator() {
		return allPieces.iterator();
	}

	public Chess() {
		for (var s : startPieces) {
			allPieces.add(new Piece(s.charAt(0) == 'w', s.charAt(1), s.charAt(2), s.charAt(3) - '0'));
		}
	}
	
	public boolean isWhitesTurn() {
		return undoStack.size() % 2 == 0;
	}

	private Stack<Move> undoStack = new Stack<>();
	private Stack<Move> redoStack = new Stack<>();

	public Move move(char fromColumn, int fromRow, char toColumn, int toRow, boolean reallyDo) {
		Piece.checkTo(fromColumn, fromRow);
		Piece.checkTo(toColumn, toRow);
		var fromPiece = findPieceAt(fromColumn, fromRow);
		if (fromPiece == null) {
			throw new IllegalArgumentException("There is no piece at " + fromColumn + fromRow);			
		} else if (fromPiece.isWhite() != isWhitesTurn()) {
			throw new IllegalArgumentException("Cannot move the opponent's piece");
		} else if (fromColumn == toColumn && fromRow == toRow) {
			throw new IllegalArgumentException("A piece cannot move to the same square");
		} else {
			var toPiece = findPieceAt(toColumn, toRow);
			checkMove(fromPiece, toColumn, toRow, toPiece);
			if (toPiece != null) {
				if (fromPiece.isWhite() == toPiece.isWhite()) {
					throw new IllegalArgumentException("A piece cannot capture one of the same color");
				} else if (reallyDo) {
					allPieces.remove(toPiece);
				}
			}
			if (reallyDo) {
				fromPiece.moveTo(toColumn, toRow);
				undoStack.push(new Move(fromPiece, fromColumn, fromRow, toColumn, toRow, toPiece));
				redoStack.clear();
			}
		}
		return null;
	}
	
	public void undoMove() {
		if (canUndoMove()) {
			Move move = undoStack.pop();
			move.getMovedPiece().moveTo(move.getFromColumn(), move.getFromRow());
			Piece takenPiece = move.getTakenPiece();
			if (takenPiece != null) {
				allPieces.add(takenPiece);
			}
			redoStack.push(move);
		}
	}

	public boolean canUndoMove() {
		return ! undoStack.isEmpty();
	}

	public void redoMove() {
		if (canRedoMove()) {
			Move move = redoStack.pop();
			move.getMovedPiece().moveTo(move.getToColumn(), move.getToRow());
			Piece takenPiece = move.getTakenPiece();
			if (takenPiece != null) {
				allPieces.remove(takenPiece);
			}
			undoStack.push(move);
		}
	}

	public boolean canRedoMove() {
		return ! redoStack.isEmpty();
	}
	
	private void checkMove(Piece piece, char toColumn, int toRow, Piece takes) {
		int dl = toColumn - piece.getColumn(), adl = Math.abs(dl);
		int dr = toRow - piece.getRow(), adr = Math.abs(dr);
		switch (piece.getKind()) {
		case 'K':
			if (adl > 1 || adr > 1) throw new IllegalArgumentException("A King can only move one square in any direction");
			else if (isPieceBetween(piece, toColumn, toRow, takes)) throw new IllegalArgumentException("A King cannot jump over other pieces");
			else return;
		case 'B':
			if (adl != adr) throw new IllegalArgumentException("A Bishop must move diagonally");
			else if (isPieceBetween(piece, toColumn, toRow, takes)) throw new IllegalArgumentException("A Bishop cannot jump over other pieces");
			else return;
		case 'R':
			if (adl != 0 && adr != 0) throw new IllegalArgumentException("A Rook must move horizontally or vertically");
			else if (isPieceBetween(piece, toColumn, toRow, takes)) throw new IllegalArgumentException("A Rook cannot jump over other pieces");
			else return;
		case 'Q':
			if (adl != adr && adl != 0 && adr != 0) throw new IllegalArgumentException("A Queen  must move horizontal, vertically or diagonally");
			else if (isPieceBetween(piece, toColumn, toRow, takes)) throw new IllegalArgumentException("A Queen cannot jump over other pieces");
			else return;
		case 'N':
			if (adl * adr != 2) throw new IllegalArgumentException("A Knight must move one square in one direction and two in the other");
			else return;
		case 'P': {
			if (takes != null) {
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

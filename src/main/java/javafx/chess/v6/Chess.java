package javafx.chess.v6;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Chess implements Iterable<Piece> {

	private File location;
	
	public File getLocation() {
		return location;
	}
	
	public void setLocation(File location) {
		this.location = location;
	}
	
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

	private MoveFormat moveFormat;

	public MoveFormat getMoveFormat() {
		return moveFormat;
	}
	
	public Chess(Iterable<String> lines) {
		this();
		doLines(lines);
	}

	private void doLines(Iterable<String> lines) {
		for (var line : lines) {
			int pos = line.indexOf('#');
			if (pos >= 0) {
				line = line.substring(0, pos);
			}
			line = line.trim();
			if (line.length() > 0) {
				Move move = moveFormat.parse(line);
				doMove(move);
			}
		}
	}

	public Chess() {
		for (var s : startPieces) {
			allPieces.add(new Piece(s.charAt(0) == 'w', s.charAt(1), s.charAt(2), s.charAt(3) - '0'));
		}
		moveFormat = new MoveFormat(this);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("# Chess game with " + undoStack.size() + " moves");
		for (var move : undoStack) {
			builder.append("\n");
			builder.append(moveFormat.toString(move));
		}
		return builder.toString();
	}

	public boolean isWhitesTurn() {
		return undoStack.size() % 2 == 0;
	}

	private Stack<Move> undoStack = new Stack<>();
	private Stack<Move> redoStack = new Stack<>();

	public Move move(char fromColumn, int fromRow, char toColumn, int toRow, boolean reallyDo) {
		Piece.checkTo(fromColumn, fromRow);
		Piece.checkTo(toColumn, toRow);
		return move(findPieceAt(fromColumn, fromRow), toColumn, toRow, reallyDo);
	}

	public Move move(Piece fromPiece, char toColumn, int toRow, boolean reallyDo) {
		if (fromPiece.isWhite() != isWhitesTurn()) {
			throw new IllegalArgumentException("Cannot move the opponent's piece");
		}
		char fromColumn = fromPiece.getColumn();
		int fromRow = fromPiece.getRow();
		if (fromColumn == toColumn && fromRow == toRow) {
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
			var move = new Move(fromPiece, fromColumn, fromRow, toColumn, toRow, toPiece);
			if (reallyDo) {
				fromPiece.moveTo(toColumn, toRow);
				undoStack.push(move);
				redoStack.clear();
			}
			return move;
		}
	}

	public boolean move(char fromColumn, int fromRow, char toColumn, int toRow) {
		try {
			return move(fromColumn, fromRow, toColumn, toRow, true) != null;
		} catch (Exception e) {
			return false;
		}
	}
	
	public void undoMove() {
		if (canUndoMove()) {
			var move = undoStack.pop();
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

	private void doMove(Move move) {
		move.getMovedPiece().moveTo(move.getToColumn(), move.getToRow());
		var takenPiece = move.getTakenPiece();
		if (takenPiece != null) {
			allPieces.remove(takenPiece);
		}
		undoStack.push(move);
	}
	
	public void redoMove() {
		if (canRedoMove()) {
			doMove(redoStack.pop());
		}
	}

	public boolean canRedoMove() {
		return ! redoStack.isEmpty();
	}
	
	private void checkMove(Piece piece, char toColumn, int toRow, Piece takes) {
		var dl = toColumn - piece.getColumn();
		var adl = Math.abs(dl);
		var dr = toRow - piece.getRow();
		var adr = Math.abs(dr);
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
	
	//

	public boolean canMove(Piece piece, char toColumn, int toRow) {
		try {
			return move(piece.getColumn(), piece.getRow(), toColumn, toRow, false) != null;
		} catch (Exception e) {
			return false;
		}
	}

	public Collection<Piece> canMove(char toColumn, int toRow, Character kind) {
		var result = new ArrayList<Piece>();
		for (var piece : allPieces) {
			if ((kind == null || kind == piece.getKind()) && canMove(piece, toColumn, toRow)) {
				result.add(piece);
			}
		}
		return result;
	}
}

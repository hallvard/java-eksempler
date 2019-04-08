package javafx.chess.v7;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

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
			allPieces.add(new Piece(s.charAt(0) == 'w', PieceKind.of(s.charAt(1)), s.charAt(2), s.charAt(3) - '0'));
		}
		moveFormat = new MoveFormat(this);
	}

	public boolean isWhitesTurn() {
		return undoStack.size() % 2 == 0;
	}

	private Stack<Move> undoStack = new Stack<>();
	private Stack<Move> redoStack = new Stack<>();

	public Move move(char fromLine, int fromRow, char toLine, int toRow, boolean reallyDo) {
		Piece.checkTo(fromLine, fromRow);
		Piece.checkTo(toLine, toRow);
		return move(findPieceAt(fromLine, fromRow), toLine, toRow, reallyDo);
	}
	
	public Move move(Piece fromPiece, char toLine, int toRow, boolean reallyDo) {
		if (fromPiece.isWhite() != isWhitesTurn()) {
			throw new IllegalArgumentException("Cannot move the opponent's piece");
		}
		char fromLine = fromPiece.getColumn();
		int fromRow = fromPiece.getRow();
		if (fromLine == toLine && fromRow == toRow) {
			throw new IllegalArgumentException("A piece cannot move to the same square");
		} else {
			fromPiece.getKind().checkMove(fromPiece, toLine, toRow, this);
			var toPiece = findPieceAt(toLine, toRow);
			if (toPiece != null) {
				if (fromPiece.isWhite() == toPiece.isWhite()) {
					throw new IllegalArgumentException("A piece cannot capture one of the same color");
				} else if (reallyDo) {
					allPieces.remove(toPiece);
				}
			}
			var move = new Move(fromPiece, fromLine, fromRow, toLine, toRow, toPiece);
			if (reallyDo) {
				fromPiece.moveTo(toLine, toRow);
				undoStack.push(move);
				redoStack.clear();
			}
			return move;
		}
	}

	public boolean move(char fromLine, int fromRow, char toLine, int toRow) {
		try {
			return move(fromLine, fromRow, toLine, toRow, true) != null;
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

	boolean isPieceBetween(Piece piece, char toColumn, int toRow, Piece takes) {
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

	public boolean canMove(Piece piece, char toLine, int toRow) {
		try {
			return move(piece.getColumn(), piece.getRow(), toLine, toRow, false) != null;
		} catch (Exception e) {
			return false;
		}
	}

	public Collection<Piece> canMove(char toLine, int toRow, PieceKind kind) {
		var result = new ArrayList<Piece>();
		for (var piece : allPieces) {
			if ((kind == null || kind == piece.getKind()) && canMove(piece, toLine, toRow)) {
				result.add(piece);
			}
		}
		return result;
	}
}

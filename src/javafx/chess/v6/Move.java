package javafx.chess.v6;

public class Move {

	private Piece movedPiece;
	private char fromColumn;
	private int fromRow;
	private char toColumn;
	private int toRow;
	private Piece takenPiece;

	public Move(Piece movedPiece, char fromColumn, int fromRow, char toColumn, int toRow, Piece takenPiece) {
		super();
		this.movedPiece = movedPiece;
		this.fromColumn = fromColumn;
		this.fromRow = fromRow;
		this.toColumn = toColumn;
		this.toRow = toRow;
		this.takenPiece = takenPiece;
	}
	
	public Piece getMovedPiece() {
		return movedPiece;
	}
	
	public char getFromColumn() {
		return fromColumn;
	}
	
	public int getFromRow() {
		return fromRow;
	}
	
	public char getToColumn() {
		return toColumn;
	}
	
	public int getToRow() {
		return toRow;
	}
	
	public Piece getTakenPiece() {
		return takenPiece;
	}
}

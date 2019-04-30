package javafx.chess.v7;

public class Move {

	private Piece movedPiece;
	private char fromColumn;
	private int fromRow;
	private char toLine;
	private int toRow;
	private Piece takenPiece;

	public Move(Piece movedPiece, char fromCol, int fromRow, char toCol, int toRow, Piece takenPiece) {
		super();
		this.movedPiece = movedPiece;
		this.fromColumn = fromCol;
		this.fromRow = fromRow;
		this.toLine = toCol;
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
		return toLine;
	}
	
	public int getToRow() {
		return toRow;
	}
	
	public Piece getTakenPiece() {
		return takenPiece;
	}
}

package javafx.chess.v4;

public class Piece {
	
	public final static char ROOK = 'R', BISHOP = 'B', KNIGHT = 'N', QUEEN = 'Q', KING = 'K', PAWN = 'P';
	
	// color
	private final boolean white;
	// one of the constants above
	private final char kind;
	// 'a'-'h'
	private char column;
	// 1-8
	private int row;

	public Piece(boolean white, final char kind, final char column, final int row) {
		this.white = white;
		this.kind = kind;
		moveTo(column, row);
	}

	public boolean isWhite() {
		return white;
	}
	public boolean isBlack() {
		return ! isWhite();
	}
	
	public char getKind() {
		return kind;
	}
	
	public char getColumn() {
		return column;
	}
	
	public int getRow() {
		return row;
	}
	
	public void moveTo(char column, int row) {
		checkTo(column, row);
		this.column = column;
		this.row = row;
	}

	public static void checkTo(char column, int row) {
		if (column < 'a' || column > 'h') {
			throw new IllegalArgumentException("The column must be between 'a' and 'h' (inclusive), but was " + column);
		}
		if (row < 1 || row > 8) {
			throw new IllegalArgumentException("The row must be between 1 and 8 (inclusive), but was " + row);
		}
	}

	@Override
	public String toString() {
		return "" + kind + "@" + column + row;
	}
}

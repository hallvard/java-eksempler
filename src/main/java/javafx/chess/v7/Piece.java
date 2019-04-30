package javafx.chess.v7;

public class Piece {
	
	// color
	private final boolean white;
	// one of the constants above
	private final PieceKind kind;
	// 'a'-'h'
	private char column;
	// 1-8
	private int row;

	public Piece(boolean white, final PieceKind kind, final char col, final int row) {
		this.white = white;
		this.kind = kind;
		moveTo(col, row);
	}

	public boolean isWhite() {
		return white;
	}
	public boolean isBlack() {
		return ! isWhite();
	}
	
	public PieceKind getKind() {
		return kind;
	}

	public char getColumn() {
		return column;
	}
	
	public int getRow() {
		return row;
	}
	
	public void moveTo(char col, int row) {
		checkTo(col, row);
		this.column = col;
		this.row = row;
	}

	public static void checkTo(char col, int row) {
		if (col < 'a' || col > 'h') {
			throw new IllegalArgumentException("The column must be between 'a' and 'h' (inclusive), but was " + col);
		}
		if (row < 1 || row > 8) {
			throw new IllegalArgumentException("The row must be between 1 and 8 (inclusive), but was " + row);
		}
	}

	@Override
	public String toString() {
		return "" + kind.getSymbol() + "@" + column + row;
	}
}

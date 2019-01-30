package javafx.chess.v3;

public class Piece {
	
	public final static char ROOK = 'R', BISHOP = 'B', KNIGHT = 'N', QUEEN = 'Q', KING = 'K', PAWN = 'P';
	
	// color
	private final boolean white;
	// one of the constants above
	private final char kind;
	// 'a'-'h'
	private char line;
	// 1-8
	private int row;

	public Piece(boolean white, final char kind, final char line, final int row) {
		this.white = white;
		this.kind = kind;
		moveTo(line, row);
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
	
	public char getLine() {
		return line;
	}
	
	public int getRow() {
		return row;
	}
	
	public void moveTo(char line, int row) {
		checkTo(line, row);
		this.line = line;
		this.row = row;
	}

	public static void checkTo(char line, int row) {
		if (line < 'a' || line > 'h') {
			throw new IllegalArgumentException("The line must be between 'a' and 'h' (inclusive), but was " + line);
		}
		if (row < 1 || row > 8) {
			throw new IllegalArgumentException("The row must be between 1 and 8 (inclusive), but was " + row);
		}
	}

	@Override
	public String toString() {
		return "" + kind + "@" + line + row;
	}
}

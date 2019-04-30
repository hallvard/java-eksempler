package javafx.chess.v1;

public class Piece {
	// R(ook), B(ishop), (k)N(ight), Q(ueen), K(ing), P(awn)
	char kind;
	// a-h
	char x;
	// 1-8
	int y;
	// use unicode symbols, see https://en.wikipedia.org/wiki/Chess_symbols_in_Unicode
	char symbol;

	public Piece(final char kind, final char x, final int y, final char symbol) {
		this.kind = kind;
		this.x = x;
		this.y = y;
		this.symbol = symbol;
	}

	@Override
	public String toString() {
		return "" + kind + "@" + x + y;
	}
}

package javafx.chess.v7;

public class MoveFormat {

	private Chess chess;
	
	public MoveFormat(Chess chess) {
		this.chess = chess;
	}

	public String toString(Move move) {
		String result = "" + move.getMovedPiece().getKind().getSymbol() + move.getFromColumn() + move.getFromRow();
		if (move.getTakenPiece() != null) {
			result += "x";
		}
		return result + move.getToColumn() + move.getToRow();
	}

	public Move parse(String move) {
		// Syntax: [<kind>][<columnAndOrRow>][x]<toCol><toRow>
		// decode backwards, since optional parts come first
		var toCol = move.charAt(move.length() - 2);
		var toRow = move.charAt(move.length() - 1) - '0';
		// firstPos points to first undecoded character
		var lastPos = move.length() - 3;
		boolean isTake = false;
		if (lastPos >= 0 && move.charAt(lastPos) == 'x') {
			isTake = true;
			lastPos--;
		}
		Character col = null, row = null;
		PieceKind kind = null;
		if (lastPos >= 0) {
			var next = move.charAt(lastPos);
			kind = PieceKind.of(next);
			if (kind != null) {
				lastPos--;
			} else if (Character.isDigit(next)) {
				row = next;
				lastPos--;
			}
		}		
		if (lastPos >= 0) {
			var next = move.charAt(lastPos);
			kind = PieceKind.of(next);
			if (kind != null) {
				lastPos--;
			} else {
				col = next;
				lastPos--;
			}
		}
		if (lastPos >= 0) {
			kind = PieceKind.of(move.charAt(lastPos));
			if (kind != null) {
				lastPos--;
			}
		}
		// are all characters decoded?
		if (lastPos >= 0) {
			throw new RuntimeException("Illegal format: " + move);
		}
		Piece moved = null, taken = null;
		for (var piece : chess.canMove(toCol, toRow, kind)) {
			// check correct kind 
			if (piece.isWhite() == chess.isWhitesTurn() && (kind == null || piece.getKind() == kind)) {
				// check line or row constraint 
				if ((col == null || piece.getColumn() == col) && (row == null || piece.getRow() == row - '0')) {
					// check take
					taken = chess.findPieceAt(toCol, toRow);
					if (isTake == (taken != null)) {
						if (moved != null) {
							throw new RuntimeException("Ambiguous move: " + move);							
						} else {
							moved = piece;
						}
					}
				}
			}
		}
		if (moved == null) {
			throw new RuntimeException("Impossible move: " + move);
		}
		return new Move(moved, moved.getColumn(), moved.getRow(), toCol, toRow, taken);
	}
}

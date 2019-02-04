package javafx.chess.v1;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChessController {

	List<Piece> allPieces = new ArrayList<>();

	List<String> startPieces = List.of(
			"Ra8♜", "Nb8♞", "Bc8♝", "Qd8♛", "Ke8♚", "Bf8♝", "Ng8♞", "Rh8♜",
			"Pa7♟", "Pb7♟", "Pc7♟", "Pd7♟", "Pe7♟", "Pf7♟", "Pg7♟", "Ph7♟",
			"Pa2♙", "Pb2♙", "Pc2♙", "Pd2♙", "Pe2♙", "Pf2♙", "Pg2♙", "Ph2♙",
			"Ra1♖", "Nb1♘", "Bc1♗", "Qd1♕", "Ke1♔", "Bf1♗", "Ng1♘", "Kh1♖"
	);

	public ChessController() {
		for (var s : startPieces) {
			var pieceKind = s.charAt(0);
			var x = s.charAt(1);
			var y = s.charAt(2) - '0';
			var symbol = s.charAt(3);
			var piece = new Piece(pieceKind, x, y, symbol);
			allPieces.add(piece);
			// koden over tilsvarer følgende én-linjer:
			// allPieces.add(new Piece(s.charAt(0), s.charAt(1), s.charAt(2) - '0', s.charAt(3)))
		}
	}
	
	@FXML Label a1, a2, a3, a4, a5, a6, a7, a8;
	@FXML Label b1, b2, b3, b4, b5, b6, b7, b8;
	@FXML Label c1, c2, c3, c4, c5, c6, c7, c8;
	@FXML Label d1, d2, d3, d4, d5, d6, d7, d8;
	@FXML Label e1, e2, e3, e4, e5, e6, e7, e8;
	@FXML Label f1, f2, f3, f4, f5, f6, f7, f8;
	@FXML Label g1, g2, g3, g4, g5, g6, g7, g8;
	@FXML Label h1, h2, h3, h4, h5, h6, h7, h8;
	
	List<Label> allLabels;
	
	@FXML
	void initialize() {
		allLabels = List.of(
				a1, a2, a3, a4, a5, a6, a7, a8,
				b1, b2, b3, b4, b5, b6, b7, b8,
				c1, c2, c3, c4, c5, c6, c7, c8,
				d1, d2, d3, d4, d5, d6, d7, d8,
				e1, e2, e3, e4, e5, e6, e7, e8,
				f1, f2, f3, f4, f5, f6, f7, f8,
				g1, g2, g3, g4, g5, g6, g7, g8,
				h1, h2, h3, h4, h5, h6, h7, h8
		);
		updateBoard();
	}

	void updateBoard() {
		for (var label : allLabels) {
			label.setText("");
		}
		for (var piece : allPieces) {
			var label = allLabels.get((piece.x - 'a') * 8 + piece.y - 1);
			label.setText(String.valueOf(piece.symbol));
		}
	}

	@FXML TextField from;
	@FXML TextField to;
	
	@FXML
	void handleMove() {
		var from = this.from.getText();
		var fromX = from.charAt(0);
		var fromY = from.charAt(1) - '0';
		var fromPiece = findPieceAt(fromX, fromY);
		if (fromPiece != null) {
			var to = this.to.getText();
			var toX = to.charAt(0);
			var toY = to.charAt(1) - '0';
			var toPiece = findPieceAt(toX, toY);
			if (toPiece != null) {
				allPieces.remove(toPiece);
			}
			fromPiece.x = toX;
			fromPiece.y = toY;
		}
		updateBoard();
		this.from.setText("");
		this.to.setText("");
		this.from.requestFocus();
	}
	
	Piece findPieceAt(char x, int y) {
		for (var piece : allPieces) {
			if (piece.x == x && piece.y == y) {
				return piece;
			}
		}
		return null;
	}
}

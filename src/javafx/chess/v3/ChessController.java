package javafx.chess.v3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ChessController {
	
	private Chess chess = new Chess();

	@FXML private Label a1, a2, a3, a4, a5, a6, a7, a8;
	@FXML private Label b1, b2, b3, b4, b5, b6, b7, b8;
	@FXML private Label c1, c2, c3, c4, c5, c6, c7, c8;
	@FXML private Label d1, d2, d3, d4, d5, d6, d7, d8;
	@FXML private Label e1, e2, e3, e4, e5, e6, e7, e8;
	@FXML private Label f1, f2, f3, f4, f5, f6, f7, f8;
	@FXML private Label g1, g2, g3, g4, g5, g6, g7, g8;
	@FXML private Label h1, h2, h3, h4, h5, h6, h7, h8;

	private List<Label> allLabels;

	private Map<Character, Character> whiteSymbols = new HashMap<>(), blackSymbols = new HashMap<>();
	
	private static String unicodeSymbols = "R♜♖N♞♘B♝♗Q♛♕K♚♔P♟♙";
	
	@FXML
	void initialize() {
		for (var i = 0; i < unicodeSymbols.length(); i += 3) {
			var pieceKind = unicodeSymbols.charAt(i);
			blackSymbols.put(pieceKind, unicodeSymbols.charAt(i + 1));
			whiteSymbols.put(pieceKind, unicodeSymbols.charAt(i + 2));
		}
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

	private char getPieceSymbol(Piece piece) {
		return (piece.isBlack() ? blackSymbols : whiteSymbols).get(piece.getKind());
	}
	
	private void updateBoard() {
		for (var label : allLabels) {
			label.setText("");
		}
		for (var piece : chess) {
			var label = allLabels.get((piece.getColumn() - 'a') * 8 + piece.getRow() - 1);
			label.setText("" + getPieceSymbol(piece));
		}
	}

	@FXML TextField from;
	@FXML TextField to;
	
	@FXML Label message;
	
	@FXML
	void handleMove() {
		var from = this.from.getText();
		var to = this.to.getText();
		String error = null;
		try {
			error = chess.move(from.charAt(0), from.charAt(1) - '0', to.charAt(0), to.charAt(1) - '0', true);
		} catch (RuntimeException e) {
			error = e.getMessage();
		}
		message.setText(error != null ? error : "");
		updateBoard();
		this.from.setText("");
		this.to.setText("");
		this.from.requestFocus();
	}
}

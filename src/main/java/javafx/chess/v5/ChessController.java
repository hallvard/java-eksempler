package javafx.chess.v5;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

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

	@FXML private Rectangle a1s, a2s, a3s, a4s, a5s, a6s, a7s, a8s;
	@FXML private Rectangle b1s, b2s, b3s, b4s, b5s, b6s, b7s, b8s;
	@FXML private Rectangle c1s, c2s, c3s, c4s, c5s, c6s, c7s, c8s;
	@FXML private Rectangle d1s, d2s, d3s, d4s, d5s, d6s, d7s, d8s;
	@FXML private Rectangle e1s, e2s, e3s, e4s, e5s, e6s, e7s, e8s;
	@FXML private Rectangle f1s, f2s, f3s, f4s, f5s, f6s, f7s, f8s;
	@FXML private Rectangle g1s, g2s, g3s, g4s, g5s, g6s, g7s, g8s;
	@FXML private Rectangle h1s, h2s, h3s, h4s, h5s, h6s, h7s, h8s;
	
	private List<Rectangle> allSquares;
	
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

		allSquares = List.of(
				a1s, a2s, a3s, a4s, a5s, a6s, a7s, a8s,
				b1s, b2s, b3s, b4s, b5s, b6s, b7s, b8s,
				c1s, c2s, c3s, c4s, c5s, c6s, c7s, c8s,
				d1s, d2s, d3s, d4s, d5s, d6s, d7s, d8s,
				e1s, e2s, e3s, e4s, e5s, e6s, e7s, e8s,
				f1s, f2s, f3s, f4s, f5s, f6s, f7s, f8s,
				g1s, g2s, g3s, g4s, g5s, g6s, g7s, g8s,
				h1s, h2s, h3s, h4s, h5s, h6s, h7s, h8s
				);

		chess = new Chess();
		updateAll();
	}

	private char getPieceSymbol(Piece piece) {
		return (piece.isBlack() ? blackSymbols : whiteSymbols).get(piece.getKind());
	}
	
	private Piece selectedPiece = null;
	
	private void setSelectedPiece(Piece piece) {
		selectedPiece = piece;
		if (piece != null) {
			from.setText("" + piece.getColumn() + piece.getRow());
		}
		updateBoard();
	}
	
	private void updateBoard() {
		for (var label : allLabels) {
			label.setText("");
		}
		for (var piece : chess) {
			var label = getPieceLabel(piece, allLabels);
			label.setText("" + getPieceSymbol(piece));
			label.setStyle(null);
		}
		if (selectedPiece != null) {
			var label = getPieceLabel(selectedPiece, allLabels);
			label.setStyle("-fx-background-color: #c8c4e3;");
		}
	}

	private Label getPieceLabel(Piece piece, List<Label> fxs) {
		return fxs.get((piece.getColumn() - 'a') * 8 + piece.getRow() - 1);
	}

	@FXML private TextField from;
	@FXML private TextField to;
	
	@FXML private Label message;
	@FXML private Label status;
	
	@FXML
	void handleMove() {
		var from = this.from.getText();
		var to = this.to.getText();
		message.setText("");
		try {
			if (from.length() < 2 || to.length() < 2) {
				throw new IllegalArgumentException("Illegal input format");
			}
			chess.move(from.charAt(0), from.charAt(1) - '0', to.charAt(0), to.charAt(1) - '0', true);
		} catch (RuntimeException e) {
			message.setText(e.getMessage());
		}
		setSelectedPiece(null);
		updateAll();
	}

	@FXML private Button undoButton, redoButton;
	@FXML private MenuItem undoMenuItem, redoMenuItem;

	public void updateAll() {
		updateBoard();
		from.setText("");
		to.setText("");
		from.requestFocus();
		status.setText((chess.isWhitesTurn() ? "White" : "Black") + "'s turn");
		undoButton.setDisable(! chess.canUndoMove());
		redoButton.setDisable(! chess.canRedoMove());
		undoMenuItem.setDisable(! chess.canUndoMove());
		redoMenuItem.setDisable(! chess.canRedoMove());
	}

	@FXML
	void handleClickPiece(MouseEvent event) {
		var pos = allLabels.indexOf(event.getSource());
		if (pos < 0) {
			return;
		}
		var col = (char) ('a' + pos / 8);
		var row = (pos % 8) + 1;
		var piece = chess.findPieceAt(col, row);
		if (piece != null) {
			if (piece.isWhite() == chess.isWhitesTurn()) {
				setSelectedPiece(piece);
				to.requestFocus();
			} else {
				to.setText("" + col + row);			
				handleMove();
			}
		}
	}

	@FXML
	void handleClickSquare(MouseEvent event) {
		int pos = allSquares.indexOf(event.getSource());
		if (pos < 0) {
			return;
		}
		var col = (char) ('a' + pos / 8);
		var row = (pos % 8) + 1;
		to.setText("" + col + row);
		handleMove();
	}
	
	@FXML void handleUndoMove() {
		chess.undoMove();
		updateAll();
	}

	@FXML void handleRedoMove() {
		chess.redoMove();
		updateAll();
	}
}

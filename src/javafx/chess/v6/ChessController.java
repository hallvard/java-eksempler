package javafx.chess.v6;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

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
		updateAll();
	}

	private char getPieceSymbol(Piece piece) {
		return (piece.isBlack() ? blackSymbols : whiteSymbols).get(piece.getKind());
	}
	
	private Piece selectedPiece = null;
	
	private void setSelectedPiece(Piece piece) {
		selectedPiece = piece;
		moveField.setText("");
		updateBoard();
	}

	private Map<Piece, String> highlightedPieces = new HashMap<>();
	
	private void highlightPiece(Piece piece, String color) {
		highlightedPieces.put(piece, color);
	}
	
	private void clearHightlightedPieces() {
		highlightedPieces.clear();
	}

	private Map<String, String> highlightedSquares = new HashMap<>();
	
	private void highlightSquare(char line, int row, String color) {
		highlightedSquares.put("" + line + row, color);
	}
	
	private void clearHightlightedSquares() {
		highlightedSquares.clear();
	}
	
	private void updateBoard() {
		for (var label : allLabels) {
			label.setText("");
		}
		for (var line = 'a'; line <= 'h'; line++) {
			for (var row = 1; row <= 8; row++) {
				var rect = square2fx(line, row, allSquares);
				var key = "" + line + row;
				rect.setStroke(highlightedSquares.containsKey(key) ? Color.web(highlightedSquares.get(key)) : Color.BLACK);
				rect.setStrokeWidth(highlightedSquares.containsKey(key) ? 5 : 1);
			}
		}
		for (var piece : chess) {
			var label = piece2fx(piece, allLabels);
			label.setText("" + getPieceSymbol(piece));
			String style = null;
			if (piece == selectedPiece) {
				style = "-fx-background-color: #c8c4e3;";
			} else if (highlightedPieces.containsKey(piece)) {
				style = "-fx-background-color: " + highlightedPieces.get(piece);				
			}
			label.setStyle(style);
		}
	}

	private <T> T piece2fx(Piece piece, List<T> fxs) {
		return square2fx(piece.getColumn(), piece.getRow(), fxs);
	}
	private <T> T square2fx(char line, int row, List<T> fxs) {
		return fxs.get((line - 'a') * 8 + row - 1);
	}

	@FXML private TextField moveField;
	
	@FXML private Label messageLabel;
	@FXML private Label statusLabel;

	@FXML
	void handleMove() {
		messageLabel.setText("");
		try {
			Move move = chess.getMoveFormat().parse(moveField.getText());
			handleMove(move.getMovedPiece(), move.getToColumn(), move.getToRow());
		} catch (RuntimeException e) {
			messageLabel.setText(e.getMessage());
		}
		setSelectedPiece(null);
		updateAll();

	}

	private void handleMove(Piece piece, char toLine, int toRow) {
		messageLabel.setText("");
		try {
			chess.move(piece, toLine, toRow, true);
		} catch (RuntimeException e) {
			messageLabel.setText(e.getMessage());
		}
		setSelectedPiece(null);
		clearHightlightedPieces();
		clearHightlightedSquares();
		updateAll();
	}

	@FXML private Button undoButton, redoButton;
	@FXML private MenuItem undoMenuItem, redoMenuItem;

	public void updateAll() {
		updateBoard();
		moveField.setText("");
		statusLabel.setText((chess.isWhitesTurn() ? "White" : "Black") + "'s turn");
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
		var line = (char) ('a' + pos / 8);
		var row = (pos % 8) + 1;
		var piece = chess.findPieceAt(line, row);
		if (piece != null) {
			if (piece.isWhite() == chess.isWhitesTurn()) {
				setSelectedPiece(piece);
				highlightSquares(piece, "green", "red");
				updateBoard();
			} else if (selectedPiece != null) {
				handleMove(selectedPiece, line, row);
			}
		}
	}

	private void highlightSquares(Piece piece, String moveColor, String attackColor) {
		clearHightlightedPieces();
		clearHightlightedSquares();
		for (var line = 'a'; line <= 'h'; line++) {
			for (var row = 1; row <= 8; row++) {
				if (chess.canMove(piece, line, row)) {
					var other = chess.findPieceAt(line, row);
//					System.out.println(piece + " can move to " + line + row + " and attack " + other);
					if (attackColor != null && other != null) {
						highlightPiece(other, attackColor);
					} else if (moveColor != null && other == null) {
						highlightSquare(line, row, moveColor);
					}
				}
			}
		}
	}

	@FXML
	void handleClickSquare(MouseEvent event) {
		int pos = allSquares.indexOf(event.getSource());
		if (pos < 0) {
			return;
		}
		var line = (char) ('a' + pos / 8);
		var row = (pos % 8) + 1;
		if (selectedPiece != null) {
			handleMove(selectedPiece, line, row);
		}
	}
	
	// undo & redo
	
	@FXML void handleUndoMove() {
		chess.undoMove();
		updateAll();
	}

	@FXML void handleRedoMove() {
		chess.redoMove();
		updateAll();
	}
	
	// File handling
	
	@FXML void handleNew() {
		chess = new Chess();
		updateAll();
	}
	
	private FileChooser fileChooser;

	private FileChooser getFileChooser() {
		if (fileChooser == null) {
			fileChooser = new FileChooser();
		}
		return fileChooser;
	}

	@FXML void handleOpen() {
		FileChooser fileChooser = getFileChooser();
		File selection = fileChooser.showOpenDialog(null);
		if (selection != null) {
			try {
				chess = new Chess(Files.readAllLines(selection.toPath()));
				chess.setLocation(selection);
				statusLabel.setText("Sucecssfully read " + selection);
				updateAll();
			} catch (Exception e) {
				statusLabel.setText(e.getMessage());
			}
		}
	}

	private boolean handleSave(File location) {
		try (var output = new PrintStream(new FileOutputStream(location))) {
			output.print(chess);
			return true;
		} catch (Exception e) {
			statusLabel.setText(e.getMessage());
			return false;
		}
	}
	
	@FXML void handleSave() {
		if (chess.getLocation() == null) {
			handleSaveAs();
		} else {
			handleSave(chess.getLocation());
		}
	}
	
	private void handleSaveAs(boolean setLocation) {
		FileChooser fileChooser = getFileChooser();
		File selection = fileChooser.showSaveDialog(null);
		if (selection != null) {
			var ok = handleSave(selection);
			if (ok && setLocation) {
				chess.setLocation(selection);
			}
		}
	}
	
	@FXML void handleSaveAs() {
		handleSaveAs(true);
	}
	
	@FXML void handleSaveCopyAs() {
		handleSaveAs(false);
	}
}

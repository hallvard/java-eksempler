package javafx.editor.v2;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class EdController {

	private Ed ed;
	
	@FXML
	Text edText;

	@FXML
	Text edStatus;
	
	private String statusLineFormat = "%s";
	
	@FXML
	void initialize() {
		ed = new Ed(edText.getText());
		statusLineFormat = edStatus.getText();
		updateView();
	}

	private void updateView() {		
		edText.setText(ed.toString());
		edStatus.setText(String.format(statusLineFormat, ed.getCursor()));
	}

	@FXML
	void handleKeyTyped(KeyEvent event) {
		ed.insert(event.getCharacter());
		event.consume();
		updateView();
	}

	@FXML
	void handleKeyPressed(KeyEvent event) {
		switch (event.getCode()) {
		case LEFT: {
			ed.left();
			break;
		}
		case RIGHT: {
			ed.right();
			break;
		}
		case DELETE: {
			ed.deleteRight();
			break;
		}
		case BACK_SPACE: {
			ed.deleteLeft();
			break;
		}
		default:
			return;
		}
		event.consume();
		updateView();
	}
}

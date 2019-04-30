package javafx.editor.v1;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
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
	}

	private void updateView() {		
		edText.setText(ed.toString());
		edStatus.setText(String.format(statusLineFormat, ed.getCursor()));
	}

	@FXML
	void handleKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.LEFT) {
			ed.left();
		} else if (event.getCode() == KeyCode.RIGHT) {
			ed.right();
		} else {
			return;
		}
		event.consume();
		updateView();
	}
}

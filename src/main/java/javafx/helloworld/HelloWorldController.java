package javafx.helloworld;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloWorldController {

	@FXML
	private Button helloWorldButton;

	@FXML
	private void handleHelloWorldButton() {
		helloWorldButton.setText("Hello to you!");
	}
}

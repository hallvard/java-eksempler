package javafx.shopping.v0;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShoppingApp extends Application{

	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setTitle("ShoppingApp");
		primaryStage.setScene(new Scene(FXMLLoader.load(ShoppingApp.class.getResource("ShoppingApp.fxml"))));
		primaryStage.show();
	}

	public static void main(final String[] args) {
		Application.launch(args);
	}
}
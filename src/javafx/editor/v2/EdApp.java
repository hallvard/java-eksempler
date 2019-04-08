package javafx.editor.v2;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EdApp extends Application{

	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setTitle("Ed");
		Parent parent = FXMLLoader.load(getClass().getResource("Ed.fxml"));
		var scene = new Scene(parent);
		scene.setOnKeyTyped(event -> handleEvent(event, parent.onKeyTypedProperty()));
		scene.setOnKeyPressed(event -> handleEvent(event, parent.onKeyPressedProperty()));
		scene.setOnKeyReleased(event -> handleEvent(event, parent.onKeyReleasedProperty()));
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private <T> void handleEvent(T event, ObjectProperty<EventHandler<? super T>> eventHandler) {
		if (eventHandler.get() != null) {
			eventHandler.get().handle(event);
		}
	}

	public static void main(final String[] args) {
		Application.launch(args);
	}
}

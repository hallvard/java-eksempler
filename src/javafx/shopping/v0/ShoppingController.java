package javafx.shopping.v0;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class ShoppingController {
	
	//The shopping model
	Shopping shopping = new Shopping();
	
	//FXML-elements
	@FXML Text welcomeText, total;
	@FXML AnchorPane availablePane, cartPane;
	
	//Mapping buttons to items and vice versa, quite similar to dictionaries in Python
	private Map<Button, Item> cartButtonToItem = new HashMap<>();
	private Map<Button, Item> availButtonToItem = new HashMap<>();
	private Map<Item, Button> itemToCartButton = new HashMap<>();
	
	//This method is executed automatically when the app starts
	@FXML public void initialize() {
		updateBalanceText();
		initButtons();
		total.toFront();	
	}
	
	//addToCart is executed when a button in the availablePane is clicked
	@FXML public void addToCart(ActionEvent event) {
		Button availBtn = (Button) event.getSource();
		Item item = availButtonToItem.get(availBtn);
		Button cartBtn = itemToCartButton.get(item);
		shopping.addToCart(item);
		cartBtn.setText(item.toString());
		updateBalanceText();
		cartBtn.setStyle("-fx-background-color: #b5ffb8; -fx-border-color: darkgray; -fx-border-width: 1;");
	}
	
	//removeFromCart is executed when a button in the cartPane is clicked.
	@FXML public void removeFromCart(ActionEvent event) {
		Button cartBtn = (Button) event.getSource();
		Item item = cartButtonToItem.get(cartBtn);
		shopping.removeFromCart(item);
		cartBtn.setText(item.toString());
		updateBalanceText();
		cartBtn.setStyle("");
	}
	
	//Method to map items to buttons
	public void initButtons() {
		int counter = 0;
		Item item = shopping.getAvailableItem(counter);
		while (item != null) {
			Button availBtn = (Button) availablePane.getChildren().get(counter);
			availBtn.setText(item.getName() + "\n" + item.getPrice() + ",-");
			availButtonToItem.put(availBtn, item);
			
			Button cartBtn = (Button) cartPane.getChildren().get(counter);
			cartBtn.setText(item.toString());
			cartButtonToItem.put(cartBtn, item);
			itemToCartButton.put(item, cartBtn);
			
			counter++;
			item = shopping.getAvailableItem(counter);
		}
	}
	
	//Updates the total-textfield with the cart total
	private void updateBalanceText() {
		total.setText(String.valueOf("Total: " + shopping.getCartTotal() + ",-"));
	}
	
}

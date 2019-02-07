package javafx.shopping.v1;

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
		shopping.addNToCart(item, 1);
		cartBtn.setText(item.toString());
		updateBalanceText();
		cartBtn.setStyle("-fx-background-color: #b5ffb8; -fx-border-color: darkgray; -fx-border-width: 1;");
		save();
	}
	
	//removeFromCart is executed when a button in the cartPane is clicked.
	@FXML public void removeFromCart(ActionEvent event) {
		Button cartBtn = (Button) event.getSource();
		Item item = cartButtonToItem.get(cartBtn);
		shopping.removeFromCart(item);
		cartBtn.setText(item.toString());
		updateBalanceText();
		cartBtn.setStyle("");
		save();
	}
	
	//Method to map items to buttons
	public void initButtons() {
		
		int availableItemsSize = shopping.getAvailableItemsSize();
		for (int i = 0; i < availableItemsSize; i++) {
			Item item = shopping.getAvailableItem(i);
			
			//Checks if item is in shopping-cart, and makes item point to the cart item if it is
			Item cartItem = shopping.findItem(item);
			String style = "";
			if (cartItem != null) {
				item = cartItem;
				style = "-fx-background-color: #b5ffb8; -fx-border-color: darkgray; -fx-border-width: 1;";
			}
			
			Button availBtn = (Button) availablePane.getChildren().get(i);
			availBtn.setText(item.getName() + "\n" + item.getPrice() + ",-");
			availButtonToItem.put(availBtn, item);
			
			Button cartBtn = (Button) cartPane.getChildren().get(i);
			cartButtonToItem.put(cartBtn, item);
			itemToCartButton.put(item, cartBtn);
			cartBtn.setText(item.toString());
			cartBtn.setStyle(style);
			
			item = shopping.getAvailableItem(i);
		}
	}
	
	//Updates the total-textfield with the cart total
	private void updateBalanceText() {
		total.setText(String.valueOf("Total: " + shopping.getCartTotal() + ",-"));
	}
	
	//Method for saving state
	private void save() {
		shopping.saveState();
	}
	
}

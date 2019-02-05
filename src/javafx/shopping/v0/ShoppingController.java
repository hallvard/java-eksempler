package javafx.shopping.v0;

import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ShoppingController {
	
	Shopping shopping;
	
	//Felter for elementer i FXML
	@FXML Text welcomeText, total;
	@FXML AnchorPane availablePane, cartPane;
	@FXML TextField nameNew, priceNew;
	@FXML Pane newPane;
	
	//Tilstandsvariabler for � holde styr p� ledige x- og y-koordinater for Buttons
	private int availX = 0;
	private int availY = 0;
	private int cartX = 0;
	private int cartY = 0;
	
	//HashMap (omtrent som en dictionary i Python) for � holde oversikt over hvilken Item som tilh�rer en Button
	private HashMap<Button, Item> cartButtonToItem = new HashMap<>();
	private HashMap<Button, Item> availButtonToItem = new HashMap<>();
	
	//Metode som kj�res automatisk ved oppstart (fordi den heter initialize)
	@FXML
	public void initialize() {
		shopping = new Shopping();
		updateBalanceText();
		generateItemButtons();
		updateCartButtons();
		
		//Setter total-tekstfeltet f�rst av alle elementer for � gj�re det synlig
		total.toFront();	
	}
	
	//Genererer knapper for tilgjengelige varer/Items
	public void generateItemButtons() {
		//Generer knapper for hver tilgjengelige Item i angitte x- og y-koordinater
		for (Item item : shopping.getAvailableItems()) {
			generateItemButton(item, availX, availY);	
			
			//Oppdaterer hvilke koordinater som er ledige
			this.availX += 69;
			if (availX >= 269) {
				this.availX = 0;
				this.availY += 69;
			}
		}
	}
	
	//Genererer �n knapp for angitt Item-objekt og koordinater
	public void generateItemButton(Item item, int x, int y) {
		String btnText = item.getName() + "\n" + String.valueOf(item.getPrice()) + ",-";
		Button btn = new Button(btnText);
		
		//Angir hvor p� brukergrensesnittet knappen skal v�re og hvor stor den er
		btn.setLayoutX(x);
	    btn.setLayoutY(y);
	    btn.setMinWidth(69);
	    btn.setMinHeight(69);
	    btn.setMaxWidth(69);
	    btn.setMaxHeight(69);
	    
	    //Setter oppf�rsel for knappen n�r man har pekeren over knappen (endrer tekst)
	    btn.hoverProperty().addListener((ov, oldValue, newValue) -> {
	        if (newValue) {
	            btn.setText("Legg i\nkurv");
	            btn.setCursor(Cursor.HAND);
	        } else {
	            btn.setText(btnText);
	        }
	    });
	    availButtonToItem.put(btn, item);		//Mapper Button til Item i HashMap
	    availablePane.getChildren().add(btn);		//Legger knappen til i brukergrensesnittet'
	    
	    //Setter hva som skal skje n�r knappen trykkes p�
	    btn.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	Button btn = ((Button)e.getSource());			//Henter knappen som har blitt trykket
	        	Item item = availButtonToItem.get(btn);
				shopping.addToCart(item);			//Oppdaterer modellen (shopping-objektet)
				updateCartButtons();
				total.setText(String.valueOf("Total: " + shopping.getCartTotal() + ",-"));
	        }
	    });
	}
	
	//Fjerner alle knapper og legger dem til p� nytt slik at man ikke f�r hull n�r man fjerner en knapp
	public void updateCartButtons() {
		cartPane.getChildren().clear();
		cartButtonToItem.clear();
		this.cartX = 0;
		this.cartY = 0;
		for (Item item : shopping.getCart()) {
			generateCartButton(item, cartX, cartY);
			this.cartX += 69;
			if (cartX >= 269) {
				this.cartX = 0;
				this.cartY += 69;
			}
		}
	}
	
	//Genererer Buttons for Items som er i handlekurven
	public void generateCartButton(Item item, int x, int y) {
		String btnText = String.valueOf(item.getQuanity()) + " " + item.getName() + "\n" + String.valueOf(item.getTotalPrice()) + ",-";
		Button btn = new Button(btnText);
		btn.setLayoutX(x);
	    btn.setLayoutY(y);
	    btn.setMinWidth(69);
	    btn.setMinHeight(69);
	    btn.setMaxWidth(69);
	    btn.setMaxHeight(69);
	    cartButtonToItem.put(btn, item);
	    btn.hoverProperty().addListener((ov, oldValue, newValue) -> {
	        if (newValue) {
	            btn.setText("Fjern fra\nkurv");
	            btn.setCursor(Cursor.HAND);
	        } else {
	            btn.setText(btnText);
	        }
	    });
	    cartPane.getChildren().add(btn);
	    btn.setOnAction(new EventHandler<ActionEvent>() {
	        @Override public void handle(ActionEvent e) {
	        	Button btn = ((Button)e.getSource());
				Item item = cartButtonToItem.get(btn);
				shopping.removeFromCart(item);
				updateBalanceText();
	        	updateCartButtons();
	        }
	    });
	}
	
	//Endrer teksten for verdien av handlekurven
	private void updateBalanceText() {
		total.setText(String.valueOf("Total: " + shopping.getCartTotal() + ",-"));
	}
	
	
	//Legger til tilgjengelig vare
	@FXML public void addAvailable() {
		var itemName = nameNew.getText();
		
		//Validerer at tekstfeltet har blitt skrevet i
		if (itemName.length() == 0) {
			toggleView();
			throw new IllegalArgumentException("Navnefelt mangler verdi");
		}
		var itemPrice = priceNew.getText();
		
		//Legger til Item-objekt i kurv, og utl�ser unntak om pris-feltet ikke inneholder gyldig heltall
		try {
			Item item = new Item(itemName, Integer.valueOf(itemPrice));
			shopping.addAvailableItem(item);
			generateItemButton(item, this.availX, this.availY);
			toggleView();
		} catch (NumberFormatException e) {
			toggleView();
			throw new NumberFormatException("Ugyldig heltall: " + itemPrice);
		}
		
		//Fjerner tekst fra tekstfelt n�r Item er lagt til i tilgjengelige varer
		nameNew.setText("");
		priceNew.setText("");
	}
	
	//Endrer hva som er synlig og mulig � trykke p�
	@FXML public void toggleView() {
		if (!newPane.isVisible()) {
			newPane.setVisible(true);
			newPane.toFront();
			
			//forEach g�r gjennom hvert element (her (key, value)-par) i listen den kalles p�
			cartButtonToItem.forEach((key, value) -> key.setDisable(true));
			availButtonToItem.forEach((key, value) -> key.setDisable(true));
		} else {
			newPane.setVisible(false);
			newPane.toBack();
			cartButtonToItem.forEach((key, value) -> key.setDisable(false));
			availButtonToItem.forEach((key, value) -> key.setDisable(false));
		}
	}
	
}

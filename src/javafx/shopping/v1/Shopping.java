package javafx.shopping.v1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shopping {

	private List<Item> availableItems;
	private Cart cart;

	public Shopping() {
		this.cart = new Cart();
		this.availableItems = loadItems();
		loadState();
	}
	
	public double getCartTotal() {
		return this.cart.getTotal();
	}
	
	public List<Item> getAvailableItems() {
		return availableItems;
	}
	
	public List<Item> getCart() {
		return this.cart.getCart();
	}
	
	public void addToCart(Item item) {
		this.cart.addToCart(item);
	}
	
	public void removeFromCart(Item item) {
		this.cart.removeFromCart(item);
	}
	
	public void addAvailableItem(Item item) {
		availableItems.add(item);
	}
	
	public List<Item> loadItems() {
		try {
			Scanner in = new Scanner(new FileReader("src/javafx/shopping/v1/items.txt"));
			List<Item> items = new ArrayList<Item>();
			while (in.hasNext()) {
				String line = in.next();
				String[] parts = line.split(",");
				Item item = new Item(parts[0], Integer.valueOf(parts[1]));
				items.add(item);
			}
			in.close();
			return items;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new ArrayList<Item>();
		}
	}
	
	public void saveItems() {
		PrintWriter writer;
		try {
			writer = new PrintWriter("src/javafx/shopping/v1/items.txt", "UTF-8");
			for (Item item : availableItems) {
				writer.println(item.getName() + "," + String.valueOf(item.getPrice()));
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void saveState() {
		PrintWriter writer;
		try {
			writer = new PrintWriter("src/javafx/shopping/v1/cart.txt", "UTF-8");
			for (Item item : getCart()) {
				writer.println(item.getName() + "," + String.valueOf(item.getPrice()) + "," + item.getQuanity());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public void loadState() {
		try {
			Scanner in = new Scanner(new FileReader("src/javafx/shopping/v1/cart.txt"));
			while (in.hasNext()) {
				String line = in.next();
				String[] parts = line.split(",");
				Item item = new Item(parts[0], Integer.valueOf(parts[1]));
				item.setQuantity(Integer.valueOf(parts[2]));
				addToCart(item);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}

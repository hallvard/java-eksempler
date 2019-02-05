package javafx.shopping.v1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Scanner;

public class Shopping {

	private Cart cart = new Cart();
	private List<Item> availableItems = List.of(
			new Item("Banan",7),
			new Item("Melk", 20),
			new Item("Sukker", 30),
			new Item("Loff", 15),
			
			new Item("Juice", 25),
			new Item("Laks", 50),
			new Item("Eple",7),
			new Item("Brus", 20),
			
			new Item("BigJava", 800),
			new Item("Appelsin", 7),
			new Item("Kaffe", 20),
			new Item("Kjeks", 15),
			
			new Item("Biff", 40),
			new Item("Torsk", 40),
			new Item("Ris", 15),
			new Item("Pesto",20)
			);
	
	public Shopping() {
		loadState();
	}
	
	public double getCartTotal() {
		return this.cart.getTotal();
	}
	
	public Item getAvailableItem(int index) {
		if (index >= availableItems.size()) return null;
		return availableItems.get(index);
	}
	
	public void addNToCart(Item item, int n) {
		this.cart.addNToCart(item, n);
	}
	
	public void removeFromCart(Item item) {
		this.cart.removeFromCart(item);
	}
	
	public Item findItem(Item item) {
		return this.cart.findItem(item);
	}
	
	public void saveState() {
		PrintWriter writer;
		try {
			writer = new PrintWriter("src/javafx/shopping/v1/cart.txt", "UTF-8");
			int counter = 0;
			Item item = cart.getItem(counter);
			while (item != null) {
				writer.println(item.getName() + "," + String.valueOf(item.getPrice()) + "," + item.getQuanity());
				counter++;
				item = cart.getItem(counter);
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
				int qty = Integer.valueOf(parts[2]);
				addNToCart(item, qty);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
}

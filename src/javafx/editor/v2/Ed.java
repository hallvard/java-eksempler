package javafx.editor.v2;

public class Ed {

	private String line;
	private int cursor = 0;
	
	public Ed(String line) {
		this.line = line;
	}
	
	@Override
	public String toString() {
		String result = line.substring(0, cursor) + "|";
		if (cursor < line.length()) {
			result = result + line.substring(cursor);
		}
		return result;
	}
	
	public int getCursor() {
		return cursor;
	}

	public void insert(String s) {
		var pre = "";
		if (cursor > 0 && cursor <= line.length()) {
			pre = line.substring(0, cursor);
		}
		var suff = "";
		if (cursor < line.length()) {
			suff = line.substring(cursor);
		}
		line = pre + s + suff;
		cursor += s.length();
	}

	public void deleteLeft() {
		var pre = "";
		if (cursor > 1 && cursor <= line.length()) {
			pre = line.substring(0, cursor - 1);
		}
		var suff = "";
		if (cursor < line.length()) {
			suff = line.substring(cursor);
		}
		line = pre + suff;
		if (cursor > 0) {
			cursor--;
		}
	}
	
	public void deleteRight() {
		var pre = "";
		if (cursor > 0 && cursor <= line.length()) {
			pre = line.substring(0, cursor);
		}
		var suff = "";
		if (cursor < line.length() - 1) {
			suff = line.substring(cursor + 1);
		}
		line = pre + suff;
	}
	
	public void left() {
		if (cursor > 0) {
			cursor--;
		}
	}

	public void right() {
		if (cursor < line.length()) {
			cursor++;
		}
	}
}

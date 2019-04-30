package javafx.editor.v1;

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

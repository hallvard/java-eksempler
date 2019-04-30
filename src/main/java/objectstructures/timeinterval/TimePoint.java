package objectstructures.timeinterval;

public class TimePoint {

	private int hour;
	private int min;

	public TimePoint(int hour, int min) {
		checkInt(hour, 0, 24);
		checkInt(min, 0, 60);
		this.hour = hour;
		this.min = min;
	}

	@Override
	public String toString() {
		return String.format("[TimePoint %02d:%02d]", getHour(), getMinutes());
	}
	
	public int getHour() {
		return hour;
	}

	// hjelpemetode for å sjekke om et tall er i riktig intervall
	private void checkInt(int i, int min, int max) {
		if (i < min || i >= max) {
			throw new IllegalArgumentException(String.format("%d isn't between %d (inclusive) and %d (exclusive)", i, min, max));
		}
	}

	public void setHour(int hour) {
		checkInt(hour, 0, 24);
		this.hour = hour;
	}

	public int getMinutes() {
		return min;
	}

	public void setMinutes(int minutes) {
		checkInt(minutes, 0, 60);
		min = minutes;
	}
}

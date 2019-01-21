package encapsulation.timeinterval;

public class TimeInterval3 {

	private int start;
	private int end;

	public TimeInterval3(int startHour, int startMin, int endHour, int endMin) {
		checkInt(startHour, 0, 24);
		checkInt(startMin, 0, 60);
		checkInt(minutes(startHour, startMin, endHour, endMin), 0, 24 * 60);
		this.start = minutes(startHour, startMin);
		this.end = minutes(endHour, endMin);
	}

	@Override
	public String toString() {
		return String.format("[TimeInterval3 %02d:%02d-%02d:%02d]", getStartHour(), getStartMinutes(), getEndHour(), getEndMinutes());
	}
	
	public int getStartHour() {
		return start / 60;
	}

	// hjelpemetode for å sjekke om et tall er i riktig intervall
	private void checkInt(int i, int min, int max) {
		if (i < min || i >= max) {
			throw new IllegalArgumentException(String.format("%d isn't between %d (inclusive) and %d (exclusive)", i, min, max));
		}
	}

	public void setStartHour(int hour) {
		checkInt(hour, 0, 24);
		// husk den gamle intervall-lengden
		int intervalLength = getIntervalLength();
		// sjekk om den nye kombinasjon av start og lengde er gyldig
		int newStart = minutes(hour, getStartMinutes());
		checkInt(intervalLength, 0, 24 * 60 - newStart);

		start = newStart;
		// juster endHour og endMin vha. setIntervalLength
		setIntervalLength(intervalLength);;
	}

	public int getStartMinutes() {
		return start % 60;
	}

	public void setStartMinutes(int minutes) {
		checkInt(minutes, 0, 60);
		// husk den gamle intervall-lengden
		int intervalLength = getIntervalLength();
		// sjekk om den nye kombinasjon av start og lengde er gyldig
		int newStart = minutes(getStartHour(), minutes);
		checkInt(intervalLength, 0, 24 * 60 - newStart);

		start = newStart;
		// juster endHour og endMin vha. setIntervalLength
		setIntervalLength(intervalLength);;
	}

	public int getEndHour() {
		return end / 60;
	}

	public void setEndHour(int hour) {
		setIntervalLength(minutes(hour, getEndMinutes()) - start);
	}

	public int getEndMinutes() {
		return end % 60;
	}

	private int minutes(int hour, int min) {
		return hour * 60 + min;
	}
	
	private int minutes(int startHour, int startMin, int endHour, int endMin) {
		return minutes(endHour, endMin) - minutes(startHour, startMin);
	}

	public void setEndMinutes(int minutes) {
		setIntervalLength(minutes(getEndHour(), minutes) - start);
	}

	public int getIntervalLength() {
		return end - start;
	}

	public void setIntervalLength(int minutes) {
		// sjekk om den nye kombinasjon av start og lengde er gyldig
		checkInt(minutes, 0, 24 * 60 - start);
		end = start + minutes;
	}

	//

	public static void main(String[] args) {
		TimeInterval3 ti = new TimeInterval3(12, 15, 14, 0);
		System.out.println(ti);
		ti.setStartHour(14);
		System.out.println(ti);
		ti.setStartMinutes(0);
		System.out.println(ti);
		try {
			ti.setStartHour(23);
			System.out.println("Forventet feil ble ikke fanget opp");
		} catch (IllegalArgumentException e) {
			System.out.println("Forventet feil ble fanget opp");
		}
		System.out.println(ti);
	}
}

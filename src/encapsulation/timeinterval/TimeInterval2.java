package encapsulation.timeinterval;

public class TimeInterval2 {

	// tag::variables[]
	private int startHour;
	private int startMin;
	private int intervalLength;
	// end::variables[]

	// tag::constructor[]
	public TimeInterval2(int startHour, int startMin, int endHour, int endMin) {
		checkInt(startHour, 0, 24);
		checkInt(startMin, 0, 60);
		checkInt(minutes(startHour, startMin, endHour, endMin), 0, 24 * 60);
		this.startHour = startHour;
		this.startMin = startMin;
		this.intervalLength = minutes(startHour, startMin, endHour, endMin);
	}
	// end::constructor[]

	@Override
	public String toString() {
		return String.format("[TimeInterval2 %02d:%02d-%02d:%02d]", getStartHour(), getStartMinutes(), getEndHour(), getEndMinutes());
	}
	
	public int getStartHour() {
		return startHour;
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
		checkInt(intervalLength, 0, 24 * 60 - minutes(hour, startMin));

		startHour = hour;
		// juster endHour og endMin vha. setIntervalLength
		setIntervalLength(intervalLength);;
	}

	public int getStartMinutes() {
		return startMin;
	}

	public void setStartMinutes(int minutes) {
		checkInt(minutes, 0, 60);
		// husk den gamle intervall-lengden
		int intervalLength = getIntervalLength();
		// sjekk om den nye kombinasjon av start og lengde er gyldig
		checkInt(intervalLength, 0, 24 * 60 - minutes(startHour, minutes));

		startMin = minutes;
		// juster endHour og endMin vha. setIntervalLength
		setIntervalLength(intervalLength);;
	}
	
	private int minutes(int hour, int min) {
		return hour * 60 + min;
	}
	
	private int minutes(int startHour, int startMin, int endHour, int endMin) {
		return minutes(endHour, endMin) - minutes(startHour, startMin);
	}

	// tag::end-methods[]
	public int getEndHour() {
		return startHour + (startMin + intervalLength) / 60;
	}

	public void setEndHour(int hour) {
		setIntervalLength(minutes(startHour, startMin, hour, getEndMinutes()));
	}

	public int getEndMinutes() {
		return (startMin + intervalLength) % 60;
	}

	public void setEndMinutes(int minutes) {
		setIntervalLength(minutes(startHour, startMin, getEndHour(), minutes));
	}
	// end::end-methods[]

	// start::interval-methods[]
	public int getIntervalLength() {
		return intervalLength;
	}

	public void setIntervalLength(int minutes) {
		// sjekk om den nye kombinasjon av start og lengde er gyldig
		checkInt(minutes, 0, 24 * 60 - minutes(startHour, startMin));
		intervalLength = minutes;
	}
	// end::interval-methods[]

	//

	// tag::main-method[]
	public static void main(String[] args) {
		TimeInterval2 ti = new TimeInterval2(12, 15, 14, 0);
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
	// end::main-method[]
}

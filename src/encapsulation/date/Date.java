package encapsulation.date;

public class Date {

	// tag::variabler[]
	private int year;  // ingen begrensninger
	private int month; // 1 til (og med) 12
	private int day;   // 1 til (og med) antall dager i måneden
	// end::variabler[]
	
	// tag::constructor[]
	public Date(int year, int month, int day) {
		setDate(year, month, day); // <2>
	}
	// end::constructor[]

	// tag::multi-setter[]
	public void setDate(int year, int month, int day) {
		checkDate(year, month, day); //<1>
		this.year = year;
		this.month = month;
		this.day = day;
	}
	// end::multi-setter[]
	
	private boolean isLeapYear(int year) {
		return (year % 4 == 0 && (! (year % 100 == 0))) || year % 400 == 0;
	}

	private int daysInMonth(int year, int month) {
		int daysInMonth = 31;
		if (month == 2) {
			daysInMonth = isLeapYear(year) ? 29 : 28;
		} else if (month == 2 || month == 4 || month == 6 || month == 9 || month == 11) {
			daysInMonth = 30;
		}
		return daysInMonth;
	}
	
	// tag::getters[]
	public int getYear() {
		return year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}
	// end::getters[]
	
	// tag::setters[]
	private void checkDate(int year, int month, int day) {// <1>
		if (month < 1 || month > 12) {
			throw new IllegalArgumentException("The month must be in the range [1, 12]");
		}
		int dayLimit = daysInMonth(year, month);
		if (day < 1 || day > dayLimit) {
			throw new IllegalArgumentException(String.format("The day must be in the range [1, %s], when the month is %s and the year is %s", dayLimit, month, year));
		}
	}
	
	public void setYear(int year) {
		checkDate(year, this.month, this.day); // <2>
		this.year = year; // <5>
	}
	
	public void setMonth(int month) {
		checkDate(this.year, month, this.day); // <3>
		this.month = month; // <5>
	}
	
	public void setDay(int day) {
		checkDate(this.year, this.month, day); // <4>
		this.day = day; // <5>
	}
	// end::setters[]
	
	// bekvemmelighetsmetoder
	
	// tag::convenience-methods[]
	public void setToPreviousDay() {
		day = day - 1;
		if (day < 1) { // <1>
			month = month - 1;
			if (month < 1) { // <2>
				year = year - 1;
				month = 12;
			}
			day = daysInMonth(year, month);
		}
	}

	public void setToNextDay() {
		day = day + 1;
		if (day > daysInMonth(year, month)) { // <1>
			month = month + 1;
			if (month > 12) { // <2>
				year = year + 1;
				month = 1;
			}
			day = 1;
		}		
	}
	// end::convenience-methods[]

	//
	
	public static void main(String[] args) {
	}
}

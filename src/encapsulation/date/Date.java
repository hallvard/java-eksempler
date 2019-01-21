package encapsulation.date;

public class Date {

	private int year;
	// month er 1-12
	private int month;
	// day er 1-28/29/30/31, avhengig av måneden og året
	private int day;
	
	public Date(int year, int month, int day) {
		setDate(year, month, day);
	}

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
	
	private void checkDate(int year, int month, int day) {
		if (month < 1 || month > 12) {
			throw new IllegalArgumentException("The month must be in the range [1, 12]");
		}
		int dayLimit = daysInMonth(year, month);
		if (day < 1 || day > dayLimit) {
			throw new IllegalArgumentException(String.format("The day must be in the range [1, %s], when the month is %s and the year is %s", dayLimit, month, year));
		}
	}
	
	public int getYear() {
		return year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setYear(int year) {
		checkDate(year, month, day);
		this.year = year;
	}
	
	public void setMonth(int month) {
		checkDate(year, month, day);
		this.month = month;
	}
	
	public void setDay(int day) {
		checkDate(year, month, day);
		this.day = day;
	}
	
	public void setDate(int year, int month, int day) {
		checkDate(year, month, day);
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	// bekvemmelighetsmetoder
	
	public void setToPreviousDay() {
		day = day - 1;
		if (day < 1) {
			month = month - 1;
			if (month < 1) {
				year = year - 1;
				month = 12;
			}
			day = daysInMonth(year, month);
		}
	}

	public void setToNextDay() {
		day = day + 1;
		if (day > daysInMonth(year, month)) {
			month = month + 1;
			if (month > 12) {
				year = year + 1;
				month = 1;
			}
			day = 1;
		}
		
	}

	//
	
	public static void main(String[] args) {
	}
}

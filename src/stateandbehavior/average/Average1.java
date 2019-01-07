package stateandbehavior.average;

public class Average1 {

	double sum = 0.0;
	int count = 0;
	
	void acceptValue(double value) {
		sum += value;
		// alternativ: sum = sum + value
		count++;
		// alternativ 1: count = count + 1
		// alternativ 2: count += 1
	}
	
	double getMean() {
		return sum / count;
	}
	
	public static void main(String[] args) {
		Average1 average = new Average1();
		average.acceptValue(4.0);
		average.acceptValue(5.0);
		System.out.println("Gjennomsnitt: " + average.getMean());
		average.acceptValue(6.0);
		System.out.println("Gjennomsnitt: " + average.getMean());
	}
}

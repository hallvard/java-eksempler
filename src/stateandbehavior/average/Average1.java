package stateandbehavior.average;

public class Average1 {

	// tag::variables[]
	double sum = 0.0;
	int count = 0;
	// end::variables[]
	
	// tag::methods[]
	void acceptValue(double value) {
		sum += value; // <1>
		count++; // <2>
	}
	
	double getMean() {
		return sum / count;
	}
	// end::methods[]
	
	// tag::main-method[]
	public static void main(String[] args) {
		Average1 average = new Average1(); // <1>
		average.acceptValue(4.0); // <3>
		average.acceptValue(5.0); // <3>
		System.out.println("Gjennomsnitt: " + average.getMean()); // <2>
		average.acceptValue(6.0); // <3>
		System.out.println("Gjennomsnitt: " + average.getMean()); // <2>
	}
	// end::main-method[]
}

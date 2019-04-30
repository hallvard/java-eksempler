package stateandbehavior.average;

import java.util.ArrayList;
import java.util.List;

public class Average2 {

	// tag::variables[]
	List<Double> values = new ArrayList<Double>();
	double sum = 0.0;
	// end::variables[]
	
	// tag::methods[]
	double getMean() {
		return sum / values.size(); // <1>
	}

	double getMedian() {
		// hjelpevariabler
		int count = values.size();
		int middle = count / 2;

		if (count % 2 == 0) // <2>
			return (values.get(middle - 1) + values.get(middle)) / 2; // <3>
		else
			return values.get(middle); // <4>
	}

	void acceptValue(double value) {
		int i = 0; // <5>
		while (i < values.size()) { // <6>
			if (values.get(i) > value) { // <7>
				break; // <8>
			}
			i++; // <9>
		}
		values.add(i, value); // <10>
		sum += value; // <11>
	}
	// end::methods[]
	
	// tag::main-method[]
	public static void main(String[] args) {
		Average2 average = new Average2();
		average.acceptValue(4.0);
		average.acceptValue(5.0);
		System.out.println("Verdier: " + average.values);
		System.out.println("Gjennomsnitt: " + average.getMean());
		System.out.println("Middelverdi: " + average.getMedian());
		average.acceptValue(3.0);
		System.out.println("Verdier: " + average.values);
		System.out.println("Gjennomsnitt: " + average.getMean());
		System.out.println("Middelverdi: " + average.getMedian());
		average.acceptValue(0.0);
		System.out.println("Verdier: " + average.values);
		System.out.println("Gjennomsnitt: " + average.getMean());
		System.out.println("Middelverdi: " + average.getMedian());
	}
	// end::main-method[]
}

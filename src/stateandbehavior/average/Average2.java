package stateandbehavior.average;

import java.util.ArrayList;
import java.util.List;

public class Average2 {

	List<Double> values = new ArrayList<Double>();
	double sum = 0.0;
	
	void acceptValue(double value) {
		int i = 0;
		while (i < values.size()) {
			if (values.get(i) > value) {
				break;
			}
			i++;
		}
		values.add(i, value);			
		sum += value;
		// alternativ: sum = sum + value
	}
	
	double getMean() {
		return sum / values.size();
	}

	double getMedian() {
		int count = values.size(), middle = count / 2;
		if (count % 2 == 0)
			return (values.get(middle - 1) + values.get(middle)) / 2;
		else
			return values.get(middle);
	}
	
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
}

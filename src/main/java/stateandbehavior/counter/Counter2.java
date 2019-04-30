package stateandbehavior.counter;

public class Counter2 {

	// først kommer variabel-deklarasjoner

	// tag::variables[]
	int start; // <1>
	int end; // <2>
	int counter; // <2>
	// end::variables[]
	
	// så konstruktører

	// tag::constructor[]
	Counter2(int start, int end) { // <1>
		this.start = start;
		this.end = end;
		restart();
	}
	// end::constructor[]

	// deretter metoder

	int getCounter() {
		return counter;
	}
	
	boolean isFinished() {
		return counter >= end;
	}
	
	void count() {
		if (! isFinished()) {
			counter = counter + 1; // alternatively: counter += 1 or counter++
		}
	}
	
	// tag::restart-method[]
	void restart() { // <2>
		this.counter = start;
	}
	// end::restart-method[]
	
	// tag::main-method[]
	public static void main(String[] args) {
		Counter2 counter = new Counter2(2, 3); // <1>
		System.out.println("Counter is: " + counter.getCounter()); // <2>
		System.out.println("isFinished? " + counter.isFinished()); // <2>
		counter.count(); // <3>
		System.out.println("Counter is: " + counter.getCounter()); // <2>
		System.out.println("isFinished? " + counter.isFinished()); // <2>
		counter.restart(); // <3>
		System.out.println("Counter is: " + counter.getCounter()); // <2>
		System.out.println("isFinished? " + counter.isFinished()); // <2>
		counter.count(); // <3>
		System.out.println("Counter is: " + counter.getCounter()); // <2>
		System.out.println("isFinished? " + counter.isFinished()); // <2>
	}
	// end::main-method[]
}

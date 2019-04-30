package stateandbehavior.counter;

public class Counter1 {

	// først kommer variabel-deklarasjoner

	// tag::variables[]
	int counter;
	int end;
	// end::variables[]
	
	// så konstruktører

	// tag::constructor[]
	Counter1(int start, int end) { // <1>
		this.counter = start; // <2>
		this.end = end;
	}
	// end::constructor[]

	// deretter metoder

	// tag::getCounter-method[]
	int getCounter() { // <1>
		return counter; // <2>
	}
	// end::getCounter-method[]
	
	// tag::isFinished-method[]
	boolean isFinished() { // <1>
		return counter >= end; // <2>
	}
	// end::isFinished-method[]
	
	// tag::count-method[]
	void count() { // <1>
		if (! isFinished()) { // <2>
			counter = counter + 1; // <3>
		}
	}
	// end::count-method[]
	
	// tag::main-method[]
	public static void main(String[] args) {
		Counter1 counter = new Counter1(2, 3); // <1>
		System.out.println("Counter is: " + counter.getCounter()); // <2>
		System.out.println("isFinished? " + counter.isFinished()); // <2>
		counter.count(); // <3>
		System.out.println("Counter is: " + counter.getCounter()); // <2>
		System.out.println("isFinished? " + counter.isFinished()); // <2>
	}
	// end::main-method[]
}

package stateandbehavior.counter;

public class Counter1 {

	// først kommer variabel-deklarasjoner

	int counter;
	int end;
	
	// så konstruktører

	Counter1(int start, int end) {
		this.counter = start;
		this.end = end;
	}

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
	
	public static void main(String[] args) {
		Counter1 counter = new Counter1(2, 5);
		System.out.println("Counter is: " + counter.getCounter());
		System.out.println("isFinished? " + counter.isFinished());
		counter.count();
		System.out.println("Counter is: " + counter.getCounter());
		System.out.println("isFinished? " + counter.isFinished());
		counter.count();
		System.out.println("Counter is: " + counter.getCounter());
		System.out.println("isFinished? " + counter.isFinished());
		counter.count();
		System.out.println("Counter is: " + counter.getCounter());
		System.out.println("isFinished? " + counter.isFinished());
		counter.count();
		System.out.println("Counter is: " + counter.getCounter());
		System.out.println("isFinished? " + counter.isFinished());
	}
}

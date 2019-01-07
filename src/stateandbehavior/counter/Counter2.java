package stateandbehavior.counter;

public class Counter2 {

	// først kommer variabel-deklarasjoner

	int start;
	int end;
	int counter;
	
	// så konstruktører

	Counter2(int start, int end) {
		this.start = start;
		this.end = end;
		restart();
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
	
	void restart() {
		this.counter = start;
	}
	
	public static void main(String[] args) {
		Counter2 counter = new Counter2(2, 3);
		System.out.println("Counter is: " + counter.getCounter());
		System.out.println("isFinished? " + counter.isFinished());
		counter.count();
		System.out.println("Counter is: " + counter.getCounter());
		System.out.println("isFinished? " + counter.isFinished());
		counter.restart();
		System.out.println("Counter is: " + counter.getCounter());
		System.out.println("isFinished? " + counter.isFinished());
		counter.count();
		System.out.println("Counter is: " + counter.getCounter());
		System.out.println("isFinished? " + counter.isFinished());
	}
}

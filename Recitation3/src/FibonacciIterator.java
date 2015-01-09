import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is a class that will allow you to iterate through the first n
 * Fibonacci elements
 * @author kushagramansingh
 *
 */
public class FibonacciIterator implements Iterator<Integer> {
	private Integer n;
	private Integer current;
	private Integer runningValue = 1;
	private Integer previousValue = 0;
	
	public FibonacciIterator(Integer n) {

        this.n = n;
        current = 1;

	}
	
	@Override
	public boolean hasNext() {

		return (current <= n);

	}

	@Override
	public Integer next() {

        Integer fibonacciValue;

        if(!hasNext()) {

            throw new NoSuchElementException();

        } else if (current == 1) {

            fibonacciValue = 1;

        } else {

            fibonacciValue = previousValue + runningValue;
            previousValue = runningValue;
            runningValue = fibonacciValue;

        }

        current++;

        return fibonacciValue;

	}

    public static void main(String[] args) {

        FibonacciIterator it = new FibonacciIterator(10);

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}

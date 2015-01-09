/**
 * Created by omarmujahid on 9/14/14.
 */

public interface StackADT<T> {
    /**
     * Pushes an item onto the top of this stack.
     * Throw java.lang.RuntimeException if the array is full.
     * Do not increase the physical size of the array.
     */
    public void push(T item);
    /**
     * Removes the item at the top of this stack and returns that object.
     * Throws java.util.EmptyStackException if the stack is empty.
     */
    public T pop();
    /**
     * Tests if this stack is empty.
     */
    public boolean isEmpty();
}

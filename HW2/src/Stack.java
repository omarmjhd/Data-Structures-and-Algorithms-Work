/**
 * Your stack implementation. Don't add any new public methods.
 *
 * @author Omar Mujahid
 * @version 1.0
 */
public class Stack<T> implements StackInterface<T> {

    private LinkedListInterface<T> stack;
    private int size = 0;

    public Stack() {
        stack = new DoublyLinkedList<T>();
    }

    @Override
    public void push(T t) {

        stack.addToFront(t);
        size++;

    }

    @Override
    public T pop() {

        size--;
        return stack.removeFromFront();

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {

        return (size == 0);

    }



}

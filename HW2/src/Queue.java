/**
 * Your queue implementation. Don't add any new public methods.
 *
 * @author Omar Mujahid
 * @version 1.0
 */
public class Queue<T> implements QueueInterface<T> {

    private LinkedListInterface<T> queue;
    private int size = 0;

    public Queue() {
        queue = new DoublyLinkedList<T>();
    }

    @Override
    public void enqueue(T t) {

        queue.addToFront(t);
        size++;
    }

    @Override
    public T dequeue() {

        size--;
        return queue.removeFromBack();
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

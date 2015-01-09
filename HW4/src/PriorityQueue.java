public class PriorityQueue<T extends Comparable<? super T>> implements
       PriorityQueueInterface<T>, Gradable<T> {

    private Heap<T> heap = new Heap<T>();

    @Override
    public void insert(T item) {

        if (item == null) {

            throw new IllegalArgumentException();

        }

        heap.add(item);

    }

    @Override
    public T findMin() {

        return heap.peek();

    }

    @Override
    public T deleteMin() {

        return heap.remove();

    }

    @Override
    public boolean isEmpty() {

        return heap.isEmpty();

    }

    @Override
    public void makeEmpty() {

        Heap<T> newHeap = new Heap<T>();

        heap = newHeap;


    }

    @Override
    public T[] toArray() {

        return heap.toArray();
    }

}

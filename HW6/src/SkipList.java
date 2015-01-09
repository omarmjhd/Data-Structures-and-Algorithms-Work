import java.util.TreeSet;
import java.util.Set;

public class SkipList<T extends Comparable<? super T>>
    implements SkipListInterface<T> {
    private CoinFlipper coinFlipper;
    private int size;
    private Node<T> head;


    /**
     * constructs a SkipList object that stores data in ascending order
     * when an item is inserted, the flipper is called until it returns a tails
     * if for an item the flipper returns n heads, the corresponding node has
     * n + 1 levels
     *
     * @param coinFlipper the source of randomness
     */
    public SkipList(CoinFlipper coinFlipper) {

        this.coinFlipper = coinFlipper;

        size = 0;

        Node<T> leftSentinel = new Node<T>(null, 0);
        //Makes left sentinel of root level
        head = leftSentinel;

    }

    @Override
    public T first() {

        if (size == 0) {

            return null;

        }

        Node<T> worker = head;

        while (worker.getDown() != null) {

            worker = worker.getDown();

        }

        return worker.getNext().getData();

    }

    @Override
    public T last() {

        if (size == 0) {

            return null;

        }

        Node<T> mover = head;

        while (mover.getDown() != null) {

            mover = mover.getDown();

        }

        while (mover.getNext() != null) {

            mover = mover.getNext();

        }

        return mover.getData();

    }

    @Override
    public boolean contains(T data) {

        if (data == null) {

            throw new IllegalArgumentException();

        }

        return get(data) != null;

    }

    @Override
    public void put(T data) {

        if (data == null) {

            throw new IllegalArgumentException();

        }

        int placementLevel = 0;

        while (coinFlipper.flipCoin() == CoinFlipper.Coin.HEADS) {

            placementLevel++;

        }

        while (placementLevel >= head.getLevel()) {

            Node<T> newHead = new Node<T>(null,
                    head.getLevel() + 1, null, null, head);

            head.setUp(newHead);
            head = newHead;

        }

        Node<T> worker = head;
        Node<T> up = null;

        while (worker != null) {

            while (worker.getNext() != null
                    && worker.getNext().getData().compareTo(data) < 0) {

                worker = worker.getNext();
            }

            Node<T> newNode = new Node<T>(data, worker.getLevel(),
                    worker.getNext(), up, null);
            worker.setNext(newNode);

            if (up != null) {
                up.setDown(newNode);
            }

            up = newNode;
            worker = worker.getDown();


        }

        size++;

    }

    @Override
    public T remove(T data) {

        if (data == null) {

            throw new IllegalArgumentException();

        }

        if (size == 0) {

            return null;

        }

        Node<T> worker = head;

        while (worker != null) {

            if (worker.getNext() == null
                    || worker.getNext().getData().compareTo(data) > 0) {

                worker = worker.getDown();

            } else if (worker.getNext().getData().compareTo(data) < 0) {

                worker = worker.getNext();

            } else {

                T remove = worker.getNext().getData();

                Node<T> removalNode = worker.getNext();

                worker.setNext(removalNode.getNext());

                if (worker.getLevel() > 0) {

                    worker = worker.getDown();

                } else {

                    size--;

                    return remove;

                }

            }

        }

        return null;

    }

    @Override
    public T get(T data) {

        if (data == null) {

            throw new IllegalArgumentException();

        }

        if (size == 0) {

            return null;

        }

        Node<T> worker = head;

        while (worker != null) {


            if (worker.getNext() == null) {

                worker = worker.getDown();

            } else if (worker.getNext().getData().compareTo(data) < 0) {

                worker = worker.getNext();

            } else if (worker.getNext().getData().compareTo(data) == 0) {

                return worker.getNext().getData();

            } else if (worker.getNext().getData().compareTo(data) > 0) {

                worker = worker.getDown();
            }

        }

        return null;

    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public void clear() {

        size = 0;

        head.setNext(null);

    }

    @Override
    public Set<T> dataSet() {

        Set<T> dataSet = new TreeSet<T>();

        if (size == 0) {

            return dataSet;

        }

        Node<T> mover = head;

        while (mover.getDown() != null) {

            mover = mover.getDown();

        }

        mover = mover.getNext();

        while (mover.getNext() != null) {

            dataSet.add(mover.getData());
            mover = mover.getNext();

        }

        dataSet.add(mover.getData());

        return dataSet;

    }

}

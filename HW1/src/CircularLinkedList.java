/**
 * CircularLinkedList implementation
 * @author Omar Mujahid
 * @version 1.0
 */
public class CircularLinkedList<T> implements LinkedListInterface<T> {

    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    @Override
    public void addAtIndex(int index, T data) {

        if (index < 0 || index > size) {

            throw new IndexOutOfBoundsException();

        }

        Node<T> newNode = new Node<T>(data);

        if (size == 0) {

            head = newNode;
            tail = head;
            head.setNext(head);
            size++;

        } else if (index == 0) {

            addToFront(data);

        } else if (index == size) {

            addToBack(data);

        } else {

            Node<T> iteratorNode = head;

            for (int i = 0; i < index - 1; i++) {

                iteratorNode = iteratorNode.getNext();

            }

            newNode.setNext(iteratorNode.getNext());
            iteratorNode.setNext(newNode);
            size++;

        }

        
    }

    @Override
    public T get(int index) {

        if (index < 0 || index > size) {

            throw new IndexOutOfBoundsException();

        }

        if (index == 0) {

            return head.getData();

        } else if (index == size - 1) {

            return tail.getData();

        } else {

            Node<T> tempNode = head;
            int i = 0;

            while (i != index) {

                tempNode = tempNode.getNext();
                i++;

            }

            return tempNode.getData();

        }
    }

    @Override
    public T removeAtIndex(int index) {

        if (index < 0 || index >= size) {

            throw new IndexOutOfBoundsException();

        }

        if (index == 0) {


            return removeFromFront();

        } else {

            Node<T> iteratorNode = head;

            for (int i = 0; i < index - 1; i++) {

                iteratorNode = iteratorNode.getNext();

            }

            Node<T> removalNode = iteratorNode.getNext();
            iteratorNode.setNext(iteratorNode.getNext().getNext());

            size--;

            return removalNode.getData();

        }

    }

    @Override
    public void addToFront(T t) {

        Node<T> newNode = new Node<T>(t, head);

        if (head == null) {

            head = newNode;
            tail = head;
            head.setNext(head);

        } else {

            head = newNode;
            tail.setNext(newNode);

        }

        size++;

    }

    @Override
    public void addToBack(T t) {

        Node<T> newNode = new Node<T>(t, head);

        if (head == null) {

            head = newNode;
            tail = head;
            head.setNext(head);
        } else {

            tail.setNext(newNode);
            tail = newNode;

        }

        size++;
        
    }

    @Override
    public T removeFromFront() {

        if (head == null) {

            return null;

        } else if (size == 1) {

            T oldHeadData = head.getData();
            clear();
            return oldHeadData;

        }

        T oldHeadData = head.getData();
        Node<T> newHead = head.getNext();
        head = newHead;
        tail.setNext(head);

        if (head.getNext() == head) {

            tail = head;

        }

        size--;

        return oldHeadData;

    }

    @Override
    public T removeFromBack() {

        if (tail == null) {

            return null;

        } else if (size == 1) {

            T headData = head.getData();
            clear();
            return headData;

        }

        Node<T> tempNode = head;

        while (tempNode.getNext() != tail) {

            tempNode = tempNode.getNext();

        }

        Node<T> returnNode = tempNode.getNext();
        tempNode.setNext(head);
        tail = tempNode;

        size--;

        return returnNode.getData();

    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toList() {

        Object[] listArray = (T[]) new Object[size];

        Node<T> iteratorNode = head;

        for (int i = 0; i < size; i++) {

            listArray[i] = iteratorNode.getData();
            iteratorNode = iteratorNode.getNext();

        }

        return (T[]) listArray;
        
    }

    @Override
    public boolean isEmpty() {

        return (size == 0);
        
    }

    @Override
    public int size() {

        return size;

    }

    @Override
    public void clear() {

        head = null;
        tail = null;
        size = 0;
        
    }

    /**
     * Reference to the head node of the linked list.
     * Normally, you would not do this, but we need it
     * for grading your work.
     *
     * @return Node representing the head of the linked list
     */
    public Node<T> getHead() {

        return head;
        
    }

    /**
     * Reference to the tail node of the linked list.
     * Normally, you would not do this, but we need it
     * for grading your work.
     *
     * @return Node representing the tail of the linked list
     */
    public Node<T> getTail() {

        return tail;
        
    }

    /**
     * This method is for your testing purposes.
     * You may choose to implement it if you wish.
     */
    @Override
    public String toString() {
        String answer = "";
        Node<T> current = head;
        T[] data = toList();
        for (T t: data) {
            answer += t + ", ";
        }

        return answer;

    }


}


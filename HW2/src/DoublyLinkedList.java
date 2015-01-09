/**
 * Doubly linked list implementation
 * @author Omar Mujahid
 * @version 1.1
 */

import java.util.Iterator;
import java.lang.Iterable;

public class DoublyLinkedList<T> implements LinkedListInterface<T> {

    private Node<T> head = null;
    private Node<T> tail = null;
    private int size = 0;

    @Override
    public void addAtIndex(int index, T data) {

        if (index < 0 || index > size) {

            throw new IndexOutOfBoundsException();

        }

        Node<T> newNode = new Node<T>(data); //newNode == temp

        if (index == 0) {

            addToFront(data);

        } else if (index == size) {

            addToBack(data);

        } else {

            Node<T> previousNode = head;

            for (int i = 0; i < index - 1; i++) {

                previousNode = previousNode.getNext();

            }

            Node<T> postNode = previousNode.getNext();
            previousNode.setNext(newNode);
            newNode.setPrevious(previousNode);
            newNode.setNext(postNode);
            postNode.setPrevious(newNode);
            size++;

        }

    }

    @Override
    public T get(int index) {

        if (index < 0 || index >= size) {

            throw new IndexOutOfBoundsException();

        }

        if (index == 0) {

            return head.getData();

        } else if (index == size - 1) {

            return tail.getData();

        } else {

            Node<T> tempNode = head;

            for (int i = 0; i < index; i++) {

                tempNode = tempNode.getNext();


            }

            return tempNode.getData();

        }
    }

    @Override
    public T removeAtIndex(int index) {

        if (size == 0) {

            return null;

        }

        if (index < 0 || index >= size) {

            throw new IndexOutOfBoundsException();

        }

        if (index == 0) {

            return removeFromFront();

        } else if (index == size - 1) {

            return removeFromBack();

        } else if (size == 1) {

            return removeFromBack();

        } else {

            Node<T> iteratorNode = head;


            for (int i = 0; i < index; i++) {

                iteratorNode = iteratorNode.getNext();

            }

            Node<T> previousNode = iteratorNode.getPrevious();
            Node<T> nextNode = iteratorNode.getNext();

            T oldData = iteratorNode.getData();

            previousNode.setNext(nextNode);
            nextNode.setPrevious(previousNode);

            size--;
            return oldData;

        }

    }

    @Override
    public void addToFront(T t) {

        Node<T> newNode = new Node<T>(t);

        if (size == 0) {

            head = newNode;
            tail = head;

        } else {

            head.setPrevious(newNode);
            newNode.setNext(head);
            head = newNode;

        }

        size++;

    }

    @Override
    public void addToBack(T t) {

        Node<T> newNode = new Node<T>(t);

        if (size == 0) {

            head = newNode;
            tail = newNode;

        } else {

            tail.setNext(newNode);
            newNode.setPrevious(tail);
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

        } else {

            T oldHeadData = head.getData();
            Node<T> newHead = head.getNext();
            head = newHead;
            head.setPrevious(null);

            size--;

            return oldHeadData;
        }
    }

    @Override
    public T removeFromBack() {

        if (size == 0) {
            return null;
        } else if (size == 1) {
            T data = tail.getData();
            clear();
            return data;
        } else {
            T data = tail.getData();
            tail = tail.getPrevious();
            tail.setNext(null);
            size--;
            return data;
        }


    }

    @Override
    public Object[] toArray() {

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
     * You will get a 0 if you do not implement this method.
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
     * You will get a 0 if you do not implement this method.
     *
     * @return Node representing the tail of the linked list
     */
    public Node<T> getTail() {

        return tail;

    }

    public void reverse() {

        Node current = head;
        Node holder;
        if (head == null || head.getNext() == null) {
            return;
        } else {
            while (current.getNext() != null) {
                current = current.getNext();
            }

            head = current;

            while (current.getPrevious() != null) {
                holder = current.getPrevious();
                Node prev = current.getPrevious();
                Node next = current.getNext();
                current.setNext(prev);
                current.setPrevious(next);
                current = holder;
            }

            //current.setPrevious(holder);
            //current.setNext(null);

        }
    }

    public static void main(String[] args) {

        DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();

        list.addToBack(0);
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);
        list.addToBack(4);
        list.addToBack(5);
        list.addToBack(6);
        list.addToBack(7);
        list.addToBack(8);
        list.addToBack(9);
        list.addToBack(10);

        Object[] arr = list.toArray();
        for (int i = 0; i < arr.length; i++ ) {
            System.out.println(arr[i]);
        }
        System.out.println("Head: " + list.getHead().getData());

        System.out.println();
        list.reverse();
        Object[] arr1 = list.toArray();
        for (int i = 0; i < arr1.length; i++ ) {
            System.out.println(arr1[i]);
        }
        System.out.println("Head: " + list.getHead().getData());
    }
}

/**
 * Doubly linked list implementation
 * @author Omar Mujahid
 * @version 1.1
 */

import java.util.EmptyStackException;
import java.util.Iterator;
import java.lang.Iterable;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements StackADT<T>, LinkedListInterface<T> {

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

    @Override
    public void push(T item) {

        addToFront(item);

    }

    @Override
    public T pop() {

        if (size == 0) {

            throw new EmptyStackException();

        } else {

            T headData = head.getData();
            head = head.getNext();
            size--;

            return headData;
        }


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

    public static void main(String[] args) {

        DoublyLinkedList<Integer> stack = new DoublyLinkedList<Integer>();
        stack.push(0);
        stack.push(1);
        stack.push(2);
        System.out.println(stack.pop());

        System.out.println(stack.isEmpty());
        stack.push(2);

        System.out.println(stack.pop());

        System.out.println(stack.isEmpty());
        stack.push(2);

        System.out.println(stack.pop());

        System.out.println(stack.isEmpty());
        System.out.println(stack.pop());

        System.out.println(stack.isEmpty());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
        //System.out.println(stack.pop()); // This checks if the EmptyStackException is being thrown correctly



    }
}

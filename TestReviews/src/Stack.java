import java.util.EmptyStackException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by omarmujahid on 9/14/14.
 */

/*Implement with:
1. Array: Done
2. ArrayList: Done
3. LinkedList: Done
4. DoublyLinkedList: Done

 */
public class Stack<T> /*implements StackADT<T>*/{
    int size = 0;




    /*
    LINKEDLIST IMPLEMENTATION
    LinkedList<T> list;


    public Stack() {

        list = new LinkedList<T>();

    }

    @Override
    public void push(T item) {

        list.add(item);
        size++;
    }

    @Override
    public T pop() {

        if (size == 0) {

            throw new EmptyStackException();

        } else {

            T data = list.remove();
            size--;

            return data;
        }
    }



    /*

    ARRAYLIST IMPLEMENTATION

    ArrayList<T> backingArrayList;

    public Stack() {

        backingArrayList = new ArrayList<T>();

    }

    @Override
    public void push(T item) {

        backingArrayList.add(item);
        size++;

    }

    @Override
    public T pop() {

        if (size == 0) {

            throw new EmptyStackException();

        }

        T data = backingArrayList.remove(size - 1);
        size--;

        return data;

    }



    ARRAY IMPLEMENTATION

    T[] backingArray;

    public Stack() {

        backingArray = (T[]) new Object[100];

    }

    @Override
    public void push(T item) {
        if (size < 100) {
            backingArray[size] = item;

            size++;

        } else {

            throw new RuntimeException();

        }


    }

    @Override
    public T pop() {

        if (size == 0) {

            throw new EmptyStackException();

        }

        T returnData = backingArray[size-1];
        size--;

        return returnData;


    }*/

    //@Override
    public boolean isEmpty() {

       return (size == 0);

    }


    /*public static void main(String[] args) {

        Stack<Integer> stack = new Stack();
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



    }*/
}

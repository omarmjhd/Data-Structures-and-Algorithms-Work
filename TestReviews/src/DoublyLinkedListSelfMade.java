import java.util.Iterator;
import java.lang.Iterable;
import java.util.NoSuchElementException;

/**
 * Created by omarmujahid on 9/14/14.
 */

public class DoublyLinkedListSelfMade {
    protected Node head;

    public void addToFront(String data) {
        Node newNode = new Node(data, head, null);

        if (head == null) {

            head = newNode;

        } else {

            head.previous = newNode;
            newNode.next = head;
            head = newNode;

        }

    }

    public void removeFirstOccurrence(String data) { // complete the code
        // It removes the node having the first occurrence of the target data.
        // The search starts at the head. If the target data is not in the list, then
        // the list remains unchanged.  The code should not fail.
        // The next field of the last node has value null.  There is no tail reference.
        // Write your code here:
        Node iteratorNode = head.next;

        if (head == null) {

            return;

        } else if (head.next == null && head.data.equals(data)) {

            head = null;
            return;

        } else if (head.data.equals(data)) {

            head = head.next;
            head.previous = null;

        } else {

            while (!(iteratorNode.data.equals(data)) /*&& (iteratorNode.next != null)*/) {
                System.out.println("In While");

                iteratorNode = iteratorNode.next;
                System.out.println("Moved iterator node");

            }

            Node previous = iteratorNode.previous;
            Node next = iteratorNode.previous;
            next.previous = previous;
            previous.next = next;

            return;

        }
    }


    protected class Node {
            protected String data;
            protected Node next;
            protected Node previous;

            private Node(String data, Node next, Node previous) {

                this.data = data;
                this.next = next;
                this.previous = previous;

            }
    }

    @Override
    public String toString() {
        String answer = "";
        String longanswer = "";
        Node current = head;

        while (current.next != null) {
            //longanswer += "Data: " + current.data + " Next: " + current.next.data + " Previous: " + current.previous.data;
            answer += current.data + " ";
            current = current.next;
        }

        answer += current.data + " ";

        return answer;

    }

    class IterableDoublyLinkedList extends DoublyLinkedList implements Iterable {

        @Override
        public Iterator iterator() {

            return new DoublyLinkedListIterator();

        }

        private class DoublyLinkedListIterator implements Iterator {

            private Node currentNode = head;

            @Override
            public boolean hasNext() {

                if (currentNode == null) {

                    return false;

                } else if (currentNode.next == null) {

                    return false;

                } else {

                    return true;

                }

            }

            @Override
            public String next() {

                if (this.hasNext()) {

                    String currentNodeData = currentNode.data;
                    currentNode = currentNode.next;

                    return currentNodeData;
                } else {
                    throw new NoSuchElementException();
                }

            }

        }

    }

    public static void main(String[] args) {

       DoublyLinkedList list = new DoublyLinkedList();
        list.addToFront("Third");
        list.addToFront("Second");
        list.addToFront("First");
        list.addToFront("Second");
        // Order: Second First Second Thirde



        System.out.println(list.toString());


        //list.removeFirstOccurrence("Third"); //infinite while loop
        //list.removeFirstOccurrence("Second"); // works correctly
        //list.removeFirstOccurrence("First"); infinite while loop

        System.out.println(list.toString());

    }
}

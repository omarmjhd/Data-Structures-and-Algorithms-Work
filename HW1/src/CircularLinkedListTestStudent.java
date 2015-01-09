import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;


public class CircularLinkedListTestStudent {

    private LinkedListInterface<Integer> list;



    @Before
    public void setup() {
       list = new CircularLinkedList<Integer>();
    }

    @SuppressWarnings("deprecation")
    @Test
    public void stupidHardTest1() {
        assertEquals(0, list.size());

        list.clear();
        assertEquals(0, list.size());

        list.addToFront(new Integer(1));
        assertEquals(1, list.size());
        assertEquals(new Integer(1), list.get(0));
        assertEquals(new Integer[]{1}, list.toList());
        assertSame(((CircularLinkedList<Integer>) list).getHead(),
                ((CircularLinkedList<Integer>) list).getTail());
        assertSame(((CircularLinkedList<Integer>) list).getHead().getNext(),
                ((CircularLinkedList<Integer>) list).getHead());
        assertSame(((CircularLinkedList<Integer>) list).getTail().getNext(),
                ((CircularLinkedList<Integer>) list).getTail());
        assertSame(((CircularLinkedList<Integer>) list).getTail().getNext(),
                ((CircularLinkedList<Integer>) list).getHead());
        assertSame(((CircularLinkedList<Integer>) list).getHead().getNext(),
                ((CircularLinkedList<Integer>) list).getTail());

        list.removeFromBack();
        assertEquals(0, list.size());
        assertEquals(new Integer[0], list.toList());


        list.addToBack(new Integer(2));
        assertEquals(1, list.size());
        assertSame(((CircularLinkedList<Integer>) list).getHead(),
                ((CircularLinkedList<Integer>) list).getTail());
        assertSame(((CircularLinkedList<Integer>) list).getHead().getNext(),
                ((CircularLinkedList<Integer>) list).getHead());
        assertSame(((CircularLinkedList<Integer>) list).getTail().getNext(),
                ((CircularLinkedList<Integer>) list).getTail());
        assertSame(((CircularLinkedList<Integer>) list).getTail().getNext(),
                ((CircularLinkedList<Integer>) list).getHead());
        assertSame(((CircularLinkedList<Integer>) list).getHead().getNext(),
                ((CircularLinkedList<Integer>) list).getTail());

        list.removeFromFront();
        assertEquals(0, list.size());

        list.clear();
        list.addAtIndex(0, new Integer(3));
        assertEquals(1, list.size());
        assertSame(((CircularLinkedList<Integer>) list).getHead(),
                ((CircularLinkedList<Integer>) list).getTail());
        assertSame(((CircularLinkedList<Integer>) list).getHead().getNext(),
                ((CircularLinkedList<Integer>) list).getHead());
        assertSame(((CircularLinkedList<Integer>) list).getTail().getNext(),
                ((CircularLinkedList<Integer>) list).getTail());
        assertSame(((CircularLinkedList<Integer>) list).getTail().getNext(),
                ((CircularLinkedList<Integer>) list).getHead());
        assertSame(((CircularLinkedList<Integer>) list).getHead().getNext(),
                ((CircularLinkedList<Integer>) list).getTail());

        list.addToFront(new Integer(4));
        assertEquals(new Integer[]{4, 3}, list.toList());
        assertEquals(2, list.size());

        list.removeFromBack();
        assertEquals(new Integer[]{4}, list.toList());
        assertEquals(1, list.size());
        assertSame(((CircularLinkedList<Integer>) list).getHead(), ((CircularLinkedList<Integer>) list).getTail());
        assertSame(((CircularLinkedList<Integer>) list).getHead().getNext(),
                ((CircularLinkedList<Integer>) list).getHead());
        assertSame(((CircularLinkedList<Integer>) list).getTail().getNext(),
                ((CircularLinkedList<Integer>) list).getTail());
        assertSame(((CircularLinkedList<Integer>) list).getTail().getNext(),
                ((CircularLinkedList<Integer>) list).getHead());
        assertSame(((CircularLinkedList<Integer>) list).getHead().getNext(),
                ((CircularLinkedList<Integer>) list).getTail());

        list.removeFromFront();
        assertEquals(new Integer[0], list.toList());
        assertEquals(0, list.size());

        list.clear();
        assertEquals(0, list.size());


        Integer[] data = new Integer[100];
        for (int i = 0; i < 100; i++) {
            assertEquals(i, list.size());
            list.addAtIndex(i, new Integer(i));
            data[i] = new Integer(i);
        }
        assertEquals(data, list.toList());
        assertEquals(100, list.size());

        data = new Integer[] { 1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14, 15, 16,
                17, 18 };
        list.clear();
        for (int i = 0; i < 20; i++) {
            list.addToBack(new Integer(i));
        }
        list.removeFromBack();
        list.removeFromFront();
        list.removeAtIndex(2);
        list.removeAtIndex(10);
        assertEquals(data, list.toList());
        assertEquals(new Integer(11), list.get(9));

        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test(timeout = 200)
    public void testAddFrontSingle() {
        list.addToFront(new Integer(1));
        assertEquals(1, list.size());
        assertSame(((CircularLinkedList<Integer>) list).getHead(), ((CircularLinkedList<Integer>) list).getTail());
        assertSame(((CircularLinkedList<Integer>) list).getHead().getNext(), ((CircularLinkedList<Integer>) list).getHead());
        assertEquals(new Integer(1), ((CircularLinkedList<Integer>) list).getHead().getData());
    }
    
    @Test(timeout = 200)
    public void testAddBackSingle() {
        list.addToBack(new Integer(1));
        assertEquals(1, list.size());
        assertSame(((CircularLinkedList<Integer>) list).getHead(), ((CircularLinkedList<Integer>) list).getTail());
        assertSame(((CircularLinkedList<Integer>) list).getHead().getNext(), ((CircularLinkedList<Integer>) list).getHead());
        assertEquals(new Integer(1), ((CircularLinkedList<Integer>) list).getHead().getData());
    }

    
    @Test(timeout = 200)
    public void testRemoveFront() {
        list.addToBack(new Integer(1));
        list.addToBack(new Integer(2));
        list.addToBack(new Integer(3));
        assertEquals(3, list.size());
        assertEquals(new Integer(1), ((CircularLinkedList<Integer>) list).getHead().getData());
        list.removeFromFront();
        assertEquals(2, list.size());
        assertSame(((CircularLinkedList<Integer>) list).getTail().getNext(), ((CircularLinkedList<Integer>) list).getHead());
        assertEquals(new Integer(2), ((CircularLinkedList<Integer>) list).getHead().getData());
    }

    @Test(timeout = 200)
    public void testRemoveBackNoData() {
        assertNull(list.removeFromBack());
    }

    @Test(timeout = 200)
    public void testToListMany() {
        Integer[] nums = addManyElementsToBack();
        assertArrayEquals(nums, list.toList());
    }

    @Test(timeout = 200)
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.addToFront(new Integer(0));
        assertFalse(list.isEmpty());
        list.addToBack(new Integer(1));
        assertFalse(list.isEmpty());
        list.removeFromFront();
        assertFalse(list.isEmpty());
        list.removeFromBack();
        assertTrue(list.isEmpty());
    }

    @Test(timeout = 200)
    public void testClearWithSize() {
        assertEquals(0, list.size());
        list.addToFront(new Integer(5));
        list.addToFront(new Integer(14));
        list.addToFront(new Integer(2));
        assertEquals(3, list.size());
        list.clear();
        assertEquals(0, list.size());
    }

    @Test(timeout = 200)
    public void testAddAtIndex() {
        list.addAtIndex(0, new Integer(0));
        list.addAtIndex(1, new Integer(2));
        list.addAtIndex(1, new Integer(1));

        Node<Integer> currentNode = ((CircularLinkedList<Integer>) list).getHead();
        assertEquals(new Integer(0), currentNode.getData());
        currentNode = currentNode.getNext();
        assertEquals(new Integer(1), currentNode.getData());
        currentNode = currentNode.getNext();
        assertEquals(new Integer(2), currentNode.getData());
        assertSame(currentNode.getNext(), ((CircularLinkedList<Integer>) list).getHead());
        assertSame(currentNode, ((CircularLinkedList<Integer>) list).getTail());
    }

    
    @Test(timeout = 200, expected = IndexOutOfBoundsException.class)
    public void testAddAtNegativeIndex() {
        list.addAtIndex(-1, new Integer(1));
    }

    
    @Test(timeout = 200)
    public void testRemoveAtIndex() {
        list.addAtIndex(0, new Integer(0));
        list.addAtIndex(1, new Integer(2));
        list.addAtIndex(1, new Integer(1));

        assertEquals(new Integer(1), list.removeAtIndex(1));
        assertEquals(new Integer(2), list.removeAtIndex(1));
        assertEquals(new Integer(0), list.removeAtIndex(0));
        assertTrue(list.isEmpty());
    }

    @Test(timeout = 200)
    public void testGet() {
        list.addToBack(new Integer(0));
        list.addToBack(new Integer(1));
        list.addToBack(new Integer(2));

        assertEquals(new Integer(0), list.get(0));
        assertEquals(new Integer(1), list.get(1));
        assertEquals(new Integer(2), list.get(2));
    }

    @Test(timeout = 200)
    public void testRemoveAtIndexFromFront() {
        list.addAtIndex(0, new Integer(0));
        list.addAtIndex(1, new Integer(2));
        list.addAtIndex(1, new Integer(1));
        assertEquals(new Integer(0), list.removeAtIndex(0));
        assertEquals(new Integer(1), list.removeAtIndex(0));
        assertEquals(new Integer(2), list.removeAtIndex(0));
        assertEquals(0, list.size());
    }

    @Test(timeout = 500)
    public void testAddToIndexAtSize() {
        for(int i =0; i < 100000; i++)
        {
            list.addAtIndex(list.size(), new Integer(i));
        }
    }

    
    @Test(timeout = 200, expected = IndexOutOfBoundsException.class)
    public void testRemoveAtLargeIndex() {
        list.removeAtIndex(100);
    }

    private Integer[] addManyElementsToBack() {
        Integer[] nums = new Integer[1000];
        for (int i = 0; i < 1000; i++) {
            list.addToBack(new Integer(i));
            nums[i] = new Integer(i);
        }
        return nums;
    }
}

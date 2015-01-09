import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;


public class AdditionalTests {
    private SkipListInterface<Integer> list;
    private CoinFlipper randomness;

    /*
     * Sets up a skiplist with 1 object
     */
    private void setupOne() {
        list.put(new Integer(10));
        /*
         * Postconditions
         */
        assertEquals(1, list.size());
        assertArrayEquals(new Integer[] { 10 },
                        list.dataSet().toArray(new Integer[1]));
    }

    private void setupMany() {
        list.put(new Integer(10));
        list.put(new Integer(12));
        list.put(new Integer(8));
        /*
         * Postconditions
         */
        assertEquals(3, list.size());
        assertArrayEquals(new Integer[] { 8, 10, 12 },
                        list.dataSet().toArray(new Integer[3]));
    }

    @Before
    public void setup() {
        randomness = new CoinFlipper(new Random(10));
        list = new SkipList<Integer>(randomness);
    }

    @Test
    public void testFirstEmpty() {
        /*
         * Preconditions
         */
        assertEquals(0, list.size());
        /*
         * Assertion
         */
        assertNull(list.first());
    }

    @Test
    public void testFirstOne() {
        setupOne();
        /*
         * Assertion
         */
        assertEquals(new Integer(10), list.first());
    }

    @Test
    public void testFirstMany() {
        setupMany();
        /*
         * Assertion
         */
        assertEquals(new Integer(8), list.first());
    }

    @Test
    public void testLastEmpty() {
        /*
         * Preconditions
         */
        assertEquals(0, list.size());
        /*
         * Assertion
         */
        assertNull(list.last());
    }

    @Test
    public void testLastOne() {
        setupOne();
        /*
         * Assertion
         */
        assertEquals(new Integer(10), list.last());
    }

    @Test
    public void testLastMany() {
        setupMany();
        /*
         * Assertion
         */
        assertEquals(new Integer(12), list.last());
    }

    @Test
    public void testLastIsFirstIfSizeOne() {
        setupOne();
        /*
         * Assertion
         */
        assertEquals(list.first(), list.last()); //value equality
        assertTrue(list.first() == list.last()); //reference equality
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetIllegalArgs() {
        list.get(null);
    }

    @Test
    public void testGetEmpty() {
        /*
         * Preconditions
         */
        assertEquals(0, list.size());
        /*
         * Assertion
         */
        assertNull(list.get(10));
    }

    @Test
    public void testGetOne() {
        setupOne();
        /*
         * Assertion
         */
        assertEquals(new Integer(10), list.get(10));
        assertNull(list.get(new Integer(11)));
    }

    @Test
    public void testGetMany() {
        setupMany();
        /*
         * Assertions
         */
        assertEquals(new Integer(8), list.get(8));
        assertEquals(new Integer(10), list.get(10));
        assertEquals(new Integer(12), list.get(12));
        assertNull(list.get(new Integer(13)));
        assertNull(list.get(9));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testPutIllegalArgs() {
        list.put(null);
    }

    @Test
    public void testPutEmpty() {
        /*
         * Preconditions
         */
        assertEquals(0, list.size());
        /*
         * Operation
         */
        list.put(new Integer(7));
        /*
         * Postconditions
         */
        assertEquals(1, list.size());
        assertEquals(new Integer(7), list.first());
        assertEquals(new Integer(7), list.last());
        assertEquals(new Integer(7), list.get(7));

    }

    @Test
    public void testPutOne() {
        setupOne();
        /*
         * Operation
         */
        list.put(new Integer(17));
        /*
         * Postconditions
         */
        assertEquals(2, list.size());
        assertEquals(new Integer(10), list.first());
        assertEquals(new Integer(17), list.last());
        assertEquals(new Integer(17), list.get(17));
        assertEquals(new Integer(10), list.get(10));
    }

    @Test
    public void testPutMany() {
        setupMany();
        int sizeBefore = list.size();
        /*
         * Operation
         */
        list.put(new Integer(11));
        /*
         * Postconditions
         */
        assertEquals(sizeBefore + 1, list.size());
        assertEquals(new Integer(8), list.first());
        assertEquals(new Integer(12), list.last());
        assertEquals(new Integer(11), list.get(11));
    }


    @Test(expected = IllegalArgumentException.class)
    public void testRemoveIllegalArgs() {
        list.remove(null);
    }

    @Test
    public void testRemoveEmpty() {
        /*
         * Preconditions
         */
        assertEquals(0, list.size());
        /*
         * Assertion
         */
        assertNull(list.remove(new Integer(15)));
    }

    @Test
    public void testRemoveOne() {
        setupOne();
        /*
         * Operation
         */
        assertEquals(new Integer(10), list.remove(10));
        /*
         * Postconditions
         */
        assertEquals(0, list.size());
        assertNull(list.first());
        assertNull(list.last());
        assertNull(list.get(10));
    }

    @Test
    public void testRemoveMany() { //this doesnt work because of remove
        setupMany();
        int sizeBefore = list.size();
        /*
         * Operation
         */
        assertEquals(new Integer(10), list.remove(new Integer(10)));
        /*
         * Postconditions
         */
        assertEquals(sizeBefore - 1, list.size());
        assertNull(list.get(10));
    }


}

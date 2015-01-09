/**
 * Created by omarmujahid on 9/26/14.
 */
public class binaryHeapTest {


    public static boolean isBinaryHeap(int[] array, int size) {
        int i = size;
        while (i > 1) {
            if (array[i/2] > array[i]) {
                return false;
            } else {
                i--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] array = {0, 2, 50, 15, 51, 53, 17};
        int size = 6;

        System.out.println(isBinaryHeap(array, size));

    }

}

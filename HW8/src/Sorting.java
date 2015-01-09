import java.util.LinkedList;
import java.util.Random;

/**
  * Sorting implementation
  * CS 1332 : Fall 2014
  * @author Omar Mujahid
  * @version 1.0
  */
public class Sorting implements SortingInterface {

    // Do not add any instance variables.

    @Override
    public <T extends Comparable<? super T>> void bubblesort(T[] arr) {

        boolean swap = true;
        int j = 0;

        while (swap) {

            swap = false;
            j++;

            for (int i = 0; i < arr.length - j; i++) {

                if (arr[i].compareTo(arr[i + 1]) > 0) {

                    swap(arr, i, i + 1);
                    swap = true;

                }

            }

        }

    }

    @Override
    public <T extends Comparable<? super T>> void insertionsort(T[] arr) {

        int i;
        int j;

        for (i = 1; i < arr.length; i++) {
            j = i;

            while (j > 0 && arr[j - 1].compareTo(arr[j]) > 0) {

                swap(arr, j, j - 1);

                j--;

            }

        }

    }

    @Override
    public <T extends Comparable<? super T>> void selectionsort(T[] arr) {

        int i;
        int j;
        int minIndex;

        for (i = 0; i < arr.length - 1; i++) {

            minIndex = i;

            for (j = i + 1; j < arr.length; j++) {

                if (arr[j].compareTo(arr[minIndex]) < 0) {

                    minIndex = j;

                }

            }

            if (minIndex != i) {

                swap(arr, i, minIndex);

            }

        }

    }

    @Override
    public <T extends Comparable<? super T>> void quicksort(T[] arr, Random r) {

        quicksort(arr, 0, arr.length - 1, r);

    }

    /**
     *
     * Method to get the index where an array will be partitioned for quicksort
     *
     * @param arr
     * @param beginIndex
     * @param endIndex
     * @param r
     * @param <T>
     * @return
     */
    private <T extends Comparable<? super T>> int partition(T[] arr,
            int beginIndex, int endIndex, Random r) {

        int pivotIndex = beginIndex + r.nextInt(endIndex - beginIndex + 1);
        T pivot = arr[pivotIndex];

        swap(arr, pivotIndex, endIndex);

        pivotIndex = beginIndex;

        for (int i = pivotIndex; i < endIndex; ++i) {

            if (arr[i].compareTo(pivot) <= 0) {

                swap(arr, pivotIndex, i);
                pivotIndex++;

            }

        }

        swap(arr, pivotIndex, endIndex);

        return pivotIndex;

    }

    /**
     *
     * Helper method that makes quicksort work and facilitate use of recursion
     *
     * @param arr
     * @param begin
     * @param end
     * @param r
     * @param <T>
     */
    private <T extends Comparable<? super T>> void quicksort(T[] arr,
         int begin, int end, Random r) {

        if (end > begin) {

            int pivotIndex = partition(arr, begin, end, r);

            quicksort(arr, begin, pivotIndex - 1, r);
            quicksort(arr, pivotIndex + 1, end, r);

        }

    }

    @Override
    public <T extends Comparable<? super T>> void mergesort(T[] arr) {

        T[] tempArray = (T[]) new Comparable[arr.length];
        mergeSort(arr, tempArray, 0, arr.length - 1);

    }

    /**
     *
     * Helper method to make mergesort work better & facilitate use of recursion
     *
     * @param arr
     * @param temp
     * @param left
     * @param right
     * @param <T>
     */
    private <T extends Comparable<? super T>> void mergeSort(T[] arr,
            T[] temp, int left, int right) {

        if (left < right) {

            int center = (left + right) / 2;
            mergeSort(arr, temp, left, center);
            mergeSort(arr, temp, center + 1, right);
            merge(arr, temp, left, center + 1, right);

        }

    }

    /**
     *
     * Helper method that merges arrays back together
     *
     * @param arr
     * @param temp
     * @param left
     * @param right
     * @param rightEnd
     * @param <T>
     */
    private <T extends Comparable<? super T>> void merge(T[] arr,
             T[] temp, int left, int right, int rightEnd) {

        int leftEnd = right - 1;
        int swapIndex = left;
        int num = rightEnd - left + 1;

        while (left <= leftEnd && right <= rightEnd) {

            if (arr[left].compareTo(arr[right]) <= 0) {

                temp[swapIndex] = arr[left];
                swapIndex++;
                left++;

            } else {

                temp[swapIndex] = arr[right];
                swapIndex++;
                right++;

            }
        }

        while (left <= leftEnd) {

            temp[swapIndex] = arr[left];
            swapIndex++;
            left++;

        }

        while (right <= rightEnd) {

            temp[swapIndex] = arr[right];
            swapIndex++;
            right++;

        }

        for (int i = 0; i < num; i++, rightEnd--) {

            arr[rightEnd] = temp[rightEnd];

        }

    }

    @Override
    public int[] radixsort(int[] arr) {

        LinkedList<Integer>[] bucket = new LinkedList[19]; //changed from 10

        for (int i = 0; i < bucket.length; i++) {

            bucket[i] = new LinkedList<Integer>();

        }

        boolean maxLength = false;
        int temp;
        int place = 1;


        while (!maxLength) {

            maxLength = true;

            for (Integer i : arr) {

                temp = (i / place);

                bucket[((temp % 10) + 9)].add(i);

                if (maxLength && temp > 0) {

                    maxLength = false;

                }

            }

            int m = 0;

            for (int j = 0; j < bucket.length; j++) {

                Integer value = null;

                while ((value = bucket[j].poll()) != null) {

                    arr[m] = value;
                    m++;

                }

            }

            place *= 10;

        }

        return arr;

    }


    /**
     * private helper method that harrs logic for swapping items in the arrays
     * @param arr the array harring the items
     * @param a the first item to swap
     * @param b the secon item to swap
     *
     */
    private <T extends Comparable<? super T>> void swap(T[] arr, int a, int b) {

        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;

    }

    public static void arrayPrinter(int[] arr) {

        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i] + ", ");

        }

        System.out.println();

    }

    public static void main(String[] args) {
        int five = 500;
        System.out.println(-2 % 10);
    }

}

public class Heap<T extends Comparable<? super T>> implements HeapInterface<T>,
       Gradable<T> {

    private int size = 1;
    private T[] backingArray = (T[]) new Comparable[10];

    @Override
    public void add(T item) {

        if (size() >= (backingArray.length - 1)) {

            resizeBackingArray();

        }

        backingArray[size] = item;

        size++;

        heapifyAfterAdd();

    }

    @Override
    public boolean isEmpty() {

        return (size == 1);

    }

    @Override
    public T peek() {

        return backingArray[1];

    }

    @Override
    public T remove() {

        T removalData = peek();

        backingArray[1] = backingArray[size()];
        backingArray[size()] = null;

        size--;

        heapifyAfterRemove();

        return removalData;

    }

    @Override
    public int size() {

        return size - 1;
    }

    @Override
    public T[] toArray() {

        return backingArray;
    }

    /**
     * Private helper method to fix heap after
     * a node is removed
     */
    private void heapifyAfterRemove() {

        int index = 1;

        while (index * 2 <= size()) {

            int smallChild = index * 2;

            if (index * 2 + 1 <= size() && backingArray[index * 2].compareTo(
                    backingArray[index * 2 + 1]) > 0) {

                smallChild = index * 2 + 1;

            }

            if (backingArray[index].compareTo(backingArray[smallChild]) > 0) {

                swapNodes(index, smallChild);

            } else {

                break;

            }

            index = smallChild;

        }



    }

    /**
     * Private helper method to fix heap after
     * a node is added
     */
    private void heapifyAfterAdd() {

        int index = size();

        while (index > 1 && backingArray[index / 2].compareTo(
                backingArray[index]) > 0) {

            swapNodes(index, index / 2);

            index = index / 2;

        }

    }

    /**
     * Private helper method to swap Nodes
     * when needed
     */
    private void swapNodes(int index1, int index2) {

        T temp = backingArray[index1];
        backingArray[index1] = backingArray[index2];
        backingArray[index2] = temp;

    }

    /**
     * Private helper method to resize the backing
     * array when it gets full
     */
    private void resizeBackingArray() {

        int currentSize = backingArray.length;


        T[] newArray = (T[]) new Comparable[currentSize * 2];

        for (int i = 0; i < currentSize; i++) {

            newArray[i] = backingArray[i];

        }

        backingArray = newArray;

    }

    @Override
    public String toString() {

        String returnString = "";
        int arraySize = backingArray.length;
        for (int i = 0; i < arraySize; i++) {
            returnString = returnString + backingArray[i] + ", ";
        }

        return returnString;
    }

}

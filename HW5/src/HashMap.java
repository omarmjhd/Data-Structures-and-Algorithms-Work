import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

public class HashMap<K, V> implements HashMapInterface<K, V>, Gradable<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    public HashMap() {
        // Initialize your hashtable here.

        table = new MapEntry[STARTING_SIZE];

        size = 0;

    }

    @Override
    public V add(K key, V value) {


        if (key == null || value == null) { //Case 1: if key or value is null

            throw new IllegalArgumentException();

        }

        int index = Math.abs(key.hashCode()) % table.length;
        MapEntry<K, V> newMapEntry = new MapEntry<K, V>(key, value);

        if ((size + 1) / (double) table.length > MAX_LOAD_FACTOR) {
        // Extra Check: If load factor is breached

            resize();

            add(key, value);

            return null;

        } else if (table[index] == null || table[index].isRemoved()) {
        // Case 2: if slot is empty, place MapEntry in slot

            table[index] = newMapEntry;

            size++;

            return null;

        } else if (table[index].getKey().equals(key)) {
        // Case 3: If slot is taken, but key.equals(key) returns true, overwrite


            V returnValue = table[index].getValue();
            table[index].setValue(value);

            return returnValue;

        } else if (!table[index].getKey().equals(key)) {

            //Case 4: If it needs to be linear probed

            for (int i = index; i < (index + table.length); i++) {

                if (table[i % table.length] == null
                        || table[i % table.length].isRemoved()) {

                    table[i % table.length] = newMapEntry;
                    size++;

                    return null;

                }

            }

            return null;

        }

        return null;

    }

    @Override
    public V remove(K key) {


        if (key == null) {

            throw new IllegalArgumentException();

        }

        if (size == 0) {

            return null;

        }

        int index = Math.abs(key.hashCode()) % table.length;

        if (table[index] != null && !table[index].isRemoved()
                && table[index].getKey().equals(key)) {

            V returnValue = table[index].getValue();

            table[index].setRemoved(true);

            size--;

            return returnValue;

        }

        return null;

    }

    @Override
    public V get(K key) {


        if (key == null) {

            throw new IllegalArgumentException();

        }

        int index = Math.abs(key.hashCode()) % table.length;

        if (size == 0) {

            return null;

        } else if (table[index] != null && !table[index].isRemoved()
                && table[index].getKey().equals(key)) {

            return table[index].getValue();

        } else {


            for (int i = index; i < (index + table.length); i++) {

                if (table[i % table.length] != null
                        && !table[i % table.length].isRemoved()
                        && table[i % table.length].getKey().equals(key)) {

                    return table[i % table.length].getValue();

                }

            }

        }

        return null;

    }

    @Override
    public boolean contains(K key) {


        if (key == null) {

            throw new IllegalArgumentException();

        }

        int index = Math.abs(key.hashCode()) % table.length;

        if (size == 0) {

            return false;

        } else if (table[index] != null && !table[index].isRemoved()
                && table[index].getKey().equals(key)) {

            return true;

        } else {

            for (int i = index; i < (index + table.length); i++) {

                if (table[i % table.length] != null
                        && !table[i % table.length].isRemoved()
                        && table[i % table.length].getKey().equals(key)) {

                    return true;

                }

            }

        }

        return false;

    }

    @Override
    public void clear() {

        table = (MapEntry<K, V>[]) new MapEntry[STARTING_SIZE];
        size = 0;

    }

    @Override
    public int size() {

        return size;

    }

    @Override
    public MapEntry<K, V>[] toArray() {

        return table;

    }

    @Override
    public Set<K> keySet() {


        Set<K> keySet = new HashSet<K>();

        for (int i = 0; i < table.length; i++) {


            if (table[i] != null && !table[i].isRemoved()) {

                keySet.add(table[i].getKey());

            }

        }

        return keySet;

    }

    @Override
    public List<V> values() {

        List<V> valueArrayList = new ArrayList<V>();


        for (int i = 0; i < table.length; i++) {

            if (table[i] != null && !table[i].isRemoved()) {

                valueArrayList.add(table[i].getValue());

            }

        }

        return valueArrayList;

    }


    /**
     * Private helper method to resize the backing array
     */
    private void resize() {

        MapEntry<K, V>[] tempTable = table;

        size = 0;

        table = (MapEntry<K, V>[]) new MapEntry[table.length * 2];

        for (int i = 0; i < tempTable.length; i++) {

            if (tempTable[i] != null && !tempTable[i].isRemoved()) {

                add(tempTable[i].getKey(), tempTable[i].getValue());

            }

        }

    }



}

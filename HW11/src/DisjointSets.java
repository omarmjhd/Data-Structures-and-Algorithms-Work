import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DisjointSets<T> implements DisjointSetsInterface<T> {
    //Should be a map of data to its parent; root data maps to itself.
    private Map<T, Node> set;

    /**
     * @param setItems the initial items (sameSet and merge will never be called
     * with items not in this set, this set will never contain null elements).
     */
    public DisjointSets(Set<T> setItems) {

        set = new HashMap<T, Node>();

        for (T u : setItems) {

            set.put(u, new Node(u, 0));

        }

    }

    @Override
    public boolean sameSet(T u, T v) {

        if (u == null || v == null) {

            throw new IllegalArgumentException();

        }

        return findRoot(u).equals(findRoot(v));

    }

    @Override
    public void merge(T u, T v) {

        if (u == null || v == null) {

            throw new IllegalArgumentException();

        }

        T uRoot = findRoot(u);
        T vRoot = findRoot(v);

        if (!uRoot.equals(vRoot)) {

            Node uNode = set.get(uRoot);
            Node vNode = set.get(vRoot);

            if (uNode.rank < vNode.rank) {

                uNode.parent = vRoot;

            } else if (uNode.rank > vNode.rank) {

                vNode.parent = uRoot;

            } else {

                vNode.parent = uRoot;
                uNode.rank++;

            }
        }

    }

    private class Node {
        //Fill in whatever methods or variables you believe are needed by node
        //here.  Should be O(1) space. This means no arrays, data structures,
        //etc.

        private T parent;
        private int rank;

        public Node(T parent, int rank) {

            this.parent = parent;
            this.rank = rank;

        }


    }

    /**
     *
     * Method to help find the root of a node in a set
     *
     * @param find node that you need the root for
     * @return find's root node
     */
    private T findRoot(T find) {

        Node node = set.get(find);

        if (node == null) {

            return null;

        }

        if (find.equals(node.parent)) {

            return find;

        }

        node.parent = findRoot(node.parent);

        return node.parent;

    }

}

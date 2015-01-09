import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * My AVL implementation.
 *
 * @author Omar Mujahid
 */
public class AVL<T extends Comparable<T>> implements AVLInterface<T>,
       Gradable<T> {

    // Do not add additional instance variables
    private Node<T> root;
    private int size;

    @Override
    public void add(T data) {

        if (data == null) {

            throw new IllegalArgumentException();

        }

        if (contains(data)) { //can I do this?

            return;

        }

        root = addRecursive(root, data);

        size++;

    }

    /**
     * private helper function using recursion to help add(T data).
     *
     * @param node the root node of the addition
     * @param data data being added
     * @return the Node that was passed in
     */
    private Node<T> addRecursive(Node<T> node, T data) {

        if (node == null) {

            Node<T> returnNode = new Node<T>(data);


            returnNode.setHeight(0);
            returnNode.setBalanceFactor(0);

            return returnNode;

        }

        int compareResult = data.compareTo(node.getData());

        if (compareResult < 0) {

            node.setLeft(addRecursive(node.getLeft(), data));
            heightHelper(node);
            fixBalanceFactor(node);

        } else if (compareResult > 0) {

            node.setRight(addRecursive(node.getRight(), data));
            heightHelper(node);
            fixBalanceFactor(node);

        }

        int balanceFactor = node.getBalanceFactor();

        if (balanceFactor < 2 && balanceFactor > -2) {

            return node;

        } else if (balanceFactor < -1
                && node.getRight().getBalanceFactor() > 0) {

            node = rotateRightLeft(node);

        } else if (balanceFactor > 1
                && node.getLeft().getBalanceFactor() < 0) {

            node = rotateLeftRight(node);

        } else if (balanceFactor < -1) {

            node = rotateLeft(node);

        } else if (balanceFactor > 1) {

            node = rotateRight(node);
        }

        return node;

    }

    @Override
    public T remove(T data) {

        if (data == null) {

            throw new IllegalArgumentException();

        }

        T removed = get(data);

        if (removed != null) {

            root = removeRecursive(root, data);

            size--;

        }

        return removed;

    }

    /**
     * private helper function using recursion to help remove(T data).
     *
     * @param node the root node of the removal
     * @param data data being removed
     * @return the Node passed in
     */
    private Node<T> removeRecursive(Node<T> node, T data) {

        if (node == null) {

            return null;

        }

        int compareResult = data.compareTo(node.getData());

        if (compareResult < 0) {

            node.setLeft(
                    removeRecursive(node.getLeft(), data));

        } else if (compareResult > 0) {

            node.setRight(
                    removeRecursive(node.getRight(), data));

        } else {

            if (node.getLeft() == null
                    && node.getRight() == null) {

                return null;

            } else if (node.getLeft() == null) {

                return node.getRight();

            } else if (node.getRight() == null) {

                return node.getLeft();

            } else {

                T smallest = smallestNode(node.getRight());

                node.setData(smallest);
                node.setRight(
                        removeRecursive(node.getRight(), smallest));

                if (node.getRight() != null) {

                    heightHelper(node.getRight());
                    fixBalanceFactor(node.getRight());
                }

                heightHelper(node);
                fixBalanceFactor(node);

            }

        }

        int balanceFactor = node.getBalanceFactor();

        if (balanceFactor < 2 && balanceFactor > -2) {

            return node;

        } else if (balanceFactor < -1
                && node.getRight().getBalanceFactor() > 0) {

            node = rotateRightLeft(node);

        } else if (balanceFactor > 1
                && node.getLeft().getBalanceFactor() < 0) {

            node = rotateLeftRight(node);

        } else if (balanceFactor < -1) {

            node = rotateLeft(node);

        } else if (balanceFactor > 1) {

            node = rotateRight(node);

        }

        return node;

    }

    @Override
    public T get(T data) {

        if (data == null) {

            throw new IllegalArgumentException();

        } else {

            return getRecursive(root, data);

        }

    }

    /**
     * private helper function using recursion to help get(T data).
     *
     * @param node the root node
     * @param data the data being searched for
     * @return the data of the node to get
     */
    private T getRecursive(Node<T> node, T data) {

        if (node == null) {

            return null;

        }

        int compareResult = data.compareTo(node.getData());

        if (compareResult < 0) {

            return getRecursive(node.getLeft(), data);

        } else if (compareResult > 0) {

            return getRecursive(node.getRight(), data);

        } else {

            return node.getData();

        }

    }

    @Override
    public boolean contains(T data) {

        return get(data) != null;

    }

    @Override
    public boolean isEmpty() {

        return size == 0;

    }

    @Override
    public int size() {

        return size;

    }

    @Override
    public List<T> preorder() {

        ArrayList<T> preorder = new ArrayList<T>(size);

        preOrderRecursive(root, preorder);

        return preorder;

    }

    /**
     * private helper method to facilitate preorder method
     *
     * @param node the root node
     * @param list the list created in preorder
     */
    private void preOrderRecursive(Node<T> node, ArrayList<T> list) {

        if (node == null) {

            return;

        }

        list.add(node.getData());
        preOrderRecursive(node.getLeft(), list);
        preOrderRecursive(node.getRight(), list);

    }

    @Override
    public List<T> postorder() {

        ArrayList<T> postorder = new ArrayList<T>(size);

        postOrderRecursive(root, postorder);

        return postorder;

    }

    /**
     * private helper method to facilitate postorder method
     *
     * @param node the root node
     * @param list the list created in postorder
     */
    private void postOrderRecursive(Node<T> node, ArrayList<T> list) {

        if (node != null) {

            postOrderRecursive(node.getLeft(), list);
            postOrderRecursive(node.getRight(), list);
            list.add(node.getData());

        }

    }

    @Override
    public List<T> inorder() {

        ArrayList<T> inorder = new ArrayList<T>(size);

        inOrderRecursive(root, inorder);

        return inorder;

    }

    /**
     * private helper method to facilitate inorder method
     *
     * @param node the root node
     * @param list the list created in inorder
     */
    private void inOrderRecursive(Node<T> node, ArrayList<T> list) {

        if (node != null) {

            inOrderRecursive(node.getLeft(), list);
            list.add(node.getData());
            inOrderRecursive(node.getRight(), list);

        }

    }

    @Override
    public List<T> levelorder() {

        ArrayList<T> levelorder = new ArrayList<T>(size);

        levelOrderHelper(levelorder, root);

        return levelorder;

    }

    /**
     * private helper method to facilitate levelorder method
     *
     * @param node the root node
     * @param list the list created in levelorder
     */
    private void levelOrderHelper(List<T> list, Node<T> node) {

        if (node != null) {

            LinkedList<Node<T>> levelOrder = new LinkedList<Node<T>>();
            levelOrder.add(node);

            while (!levelOrder.isEmpty()) {

                Node<T> current = levelOrder.poll();

                if (current.getLeft() != null) {

                    levelOrder.add(current.getLeft());

                }

                if (current.getRight() != null) {

                    levelOrder.add(current.getRight());

                }

                list.add(current.getData());

            }

        }

    }

    @Override
    public void clear() {

        size = 0;
        root = null;

    }

    @Override
    public int height() {

        if (root == null) {

            return -1;

        } else {

            return root.getHeight();

        }
    }

    /**
     * corrects the height of a node
     *
     * @param node node that needs to be corrected
     */
    private void heightHelper(Node<T> node) {

        if (node.getLeft() == null && node.getRight() == null) {

            node.setHeight(0);

        } else if (node.getLeft() == null) {

            node.setHeight(node.getRight().getHeight() + 1);

        } else if (node.getRight() == null) {

            node.setHeight(node.getLeft().getHeight() + 1);

        } else if (node.getLeft().getHeight()
                == node.getRight().getHeight()) {

            node.setHeight(node.getLeft().getHeight() + 1);

        } else if (node.getLeft().getHeight()
                > node.getRight().getHeight()) {

            node.setHeight(node.getLeft().getHeight() + 1);

        } else {

            node.setHeight(node.getRight().getHeight() + 1);

        }

    }

    /**
     * decrements the height of the tree
     *
     * @param node root of the tree to decrement
     */
    private void decrementHeight(Node<T> node) {

        if (node != null) {

            node.setHeight(node.getHeight() - 1);
            decrementHeight(node.getLeft());
            decrementHeight(node.getRight());

        }

    }

    /**
     * increments the height of all nodes
     * in the tree
     *
     * @param node root of the tree to increment
     */
    private void incrementHeight(Node<T> node) {

        if (node != null) {

            node.setHeight(node.getHeight() + 1);
            incrementHeight(node.getLeft());
            incrementHeight(node.getRight());

        }

    }

    @Override
    public Node<T> getRoot() {

        return root;

    }

    /**
     * finds smallest value
     *
     * @param node the parent node
     * @return smallest value
     */
    private T smallestNode(Node<T> node) {

        Node<T> current = node;

        if (current.getLeft() == null) {

            return current.getData();

        } else {

            while (current.getLeft() != null) {

                current = current.getLeft();

            }

        }

        return current.getData();

    }

    /**
     * shifts the tree's right branch left
     *
     * @param node the node at the beginning of the rotation
     * @return corrected tree
     */
    private Node<T> rotateLeftRight(Node<T> node) {

        node.setLeft(rotateLeft(node.getLeft()));
        incrementHeight(node.getLeft());

        node = rotateRight(node);

        return node;

    }

    /**
     * shifts the tree's left branch right
     *
     * @param node the node at the beginning of the rotation
     * @return corrected tree
     */
    private Node<T> rotateRightLeft(Node<T> node) {

        node.setRight(rotateRight(node.getRight()));
        incrementHeight(node.getRight());

        node = rotateLeft(node);

        return node;

    }

    /**
     * shifts the whole tree left
     *
     * @param unbalancedNode the node from where the tree should rotated
     * @return corrected tree
     */
    private Node<T> rotateLeft(Node<T> unbalancedNode) {

        Node<T> returnNode = unbalancedNode.getRight();

        unbalancedNode.setRight(returnNode.getLeft());
        decrementHeight(unbalancedNode);
        decrementHeight(unbalancedNode);

        if (unbalancedNode.getRight() != null) {

            heightHelper(unbalancedNode.getRight());
            fixBalanceFactor(unbalancedNode.getRight());

        }

        if (unbalancedNode.getLeft() != null) {

            heightHelper(unbalancedNode.getLeft());
            fixBalanceFactor(unbalancedNode.getLeft());

        }

        fixBalanceFactor(unbalancedNode);
        returnNode.setLeft(unbalancedNode);
        heightHelper(returnNode);
        fixBalanceFactor(returnNode);

        return returnNode;

    }

    /**
     * shifts the whole tree right
     *
     * @param unbalancedNode the node from where the tree should rotated
     * @return corrected tree
     */
    private Node<T> rotateRight(Node<T> unbalancedNode) {

        Node<T> returnNode = unbalancedNode.getLeft();

        unbalancedNode.setLeft(returnNode.getRight());
        decrementHeight(unbalancedNode);
        decrementHeight(unbalancedNode);

        if (unbalancedNode.getLeft() != null) {

            heightHelper(unbalancedNode.getLeft());
            fixBalanceFactor(unbalancedNode.getLeft());

        }

        if (unbalancedNode.getRight() != null) {

            heightHelper(unbalancedNode.getRight());
            fixBalanceFactor(unbalancedNode.getRight());

        }

        fixBalanceFactor(unbalancedNode);
        returnNode.setRight(unbalancedNode);
        heightHelper(returnNode);
        fixBalanceFactor(returnNode);

        return returnNode;

    }

    /**
     * fixes the balance factor of a node
     *
     * @param unbalancedNode node to be balanced
     */
    private void fixBalanceFactor(Node<T> unbalancedNode) {

        int leftHeight = -1;

        if (unbalancedNode.getLeft() != null) {

            leftHeight = unbalancedNode.getLeft().getHeight();

        }

        int rightHeight = -1;

        if (unbalancedNode.getRight() != null) {

            rightHeight = unbalancedNode.getRight().getHeight();

        }

        unbalancedNode.setBalanceFactor(leftHeight - rightHeight);

    }

    public static void main(String[] args) {

    }

}
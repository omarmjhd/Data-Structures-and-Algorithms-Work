import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BST<T extends Comparable<T>> implements BSTInterface<T> {

    private Node<T> root = null;
    private ArrayList<T> inList = new ArrayList<T>();
    private ArrayList<T> preList = new ArrayList<T>();
    private ArrayList<T> postList = new ArrayList<T>();
    private ArrayList<T> levelList = new ArrayList<T>();
    private int size = 0;
    private boolean containsVal = false;

    @Override
    public void add(T data) {

        Node<T> newNode = new Node<T>(data);

        if (root == null) {

            root = newNode;
            size++;

        } else {

            addRecursive(root, data);
            size++;

        }
    }

    /**
    * private helper function using recursion to help add(T data).
    *
    * @param node is the current node.
    * @param data is the data.
    */
    private void addRecursive(Node<T> node, T data) {

        Node<T> newNode = new Node<T>(data);

        if (data.compareTo(node.getData()) < 0) {

            if (node.getLeft() != null) {

                addRecursive(node.getLeft(), data);

            } else {

                node.setLeft(newNode);

            }

        } else if (data.compareTo(node.getData()) > 0) {

            if (node.getRight() != null) {

                addRecursive(node.getRight(), data);

            } else {

                node.setRight(newNode);


            }
        }
    }


    @Override
    public T remove(T data) {

        if (!contains(data)) {
            return null;
        }

        root = removeRecursive(root, data);

        if (root == null) {
            return null;

        } else {
            size--;
        }
        return data;
    }

    /**
     * private helper function using recursion to help remove(T data).
     *
     * @param node is the current node.
     * @param data is the data.
     * @return The node that replaces the removed node
     */
    private Node<T> removeRecursive(Node<T> node, T data) {

        if (node == null) {

            return null;

        }

        if (data.compareTo(node.getData()) < 0) {

            if (node.getLeft() != null) {

                node.setLeft(removeRecursive(node.getLeft(), data));

                return node;
            }

            return null;

        } else if (data.compareTo(node.getData()) > 0) {

            if (node.getRight() != null) {

                node.setRight(removeRecursive(node.getRight(), data));

                return node;
            }

            return null;

        } else {

            if (node.getLeft() == null && node.getRight() == null) {

                return null;

            } else if (node.getLeft() == null) {

                return node.getRight();

            } else if (node.getRight() == null) {

                return node.getLeft();

            } else {

                Node<T> predecessor = node.getLeft();

                while (predecessor.getRight() != null) {

                    predecessor = predecessor.getRight();

                }
                node.setData(predecessor.getData());
                node.setLeft(removeRecursive(node.getLeft(), node.getData()));
                return node;

            }
        }
    }

    @Override
    public T get(T data) {

        boolean finished = false;

        if (root == null) {

            return null;

        } else {

            Node<T> node = root;

            while (!finished) {

                if (data.compareTo(node.getData()) == 0) {

                    return node.getData();

                } else if (data.compareTo(node.getData()) < 0) {
                    if (node.getLeft() == null) {
                        return null;
                    } else {
                        node = node.getLeft();
                    }
                } else if (data.compareTo(node.getData()) > 0) {
                    if (node.getRight() == null) {
                        return null;
                    } else {
                        node = node.getRight();
                    }

                }

            }

            return null;

        }

    }


    @Override
    public boolean contains(T data) {

        containsVal = false;
        containsRecursive(root, data);

        return containsVal;

    }

    /**
     * private helper function to help see if a BST contains a value
     *
     * @param node is the current node.
     * @param data the data that is being searched for
     *
     */
    private void containsRecursive(Node<T> node, T data) {

        if (node == null) {

            return;

        }

        if (data == null) {

            while (node.getRight() != null) {

                node = node.getRight();

            }

            if (node.getData() == null) {

                containsVal = true;

            }

            return;
        }

        if (node.getData() == null) {

            if (node.getLeft() != null) {

                if (node.getLeft().getData() == data) {

                    containsVal = true;
                }
            }

            return;
        }

        if (data.compareTo(node.getData()) < 0) {

            containsRecursive(node.getLeft(), data);

        } else if (data.compareTo(node.getData()) > 0) {

            containsRecursive(node.getRight(), data);

        } else {

            if (node.getData() == data) {

                containsVal = true;

            }
        }
    }

    @Override
    public int size() {

        return size;

    }



    @Override
    public List<T> preorder() {

        preList.clear();

        preorderRecursive(root);

        return preList;

    }

    /**
     * private helper method to facilitate preorder method
     *
     * @param node a node to begin with.
     *
     */
    private void preorderRecursive(Node<T> node) {

        if (node != null) {

            preList.add(node.getData());
            preorderRecursive(node.getLeft());
            preorderRecursive(node.getRight());

        }
    }

    @Override
    public List<T> postorder() {

        postList.clear();
        postorderRecursive(root);

        return postList;

    }

    /**
     * private helper method to facilitate postorder method
     *
     * @param node a node to begin with.
     *
     */
    private void postorderRecursive(Node<T> node) {

        if (node != null) {

            postorderRecursive(node.getLeft());
            postorderRecursive(node.getRight());
            postList.add(node.getData());

        }
    }

    @Override
    public List<T> inorder() {

        inList.clear();
        inorderRecursive(root);

        return inList;

    }

    /**
     * private helper method to facilitate inorder method
     *
     * @param node a node to begin with.
     *
     */
    private void inorderRecursive(Node<T> node) {

        if (node != null) {
            inorderRecursive(node.getLeft());
            inList.add(node.getData());
            inorderRecursive(node.getRight());
        }
    }


    @Override
    public List<T> levelorder() {

        levelList.clear();
        levelorderRecursive(root);

        return levelList;

    }


    /**
     * private helper method to facilitate levelorder method
     *
     * @param node a node to begin with.
     *
     */
    private void levelorderRecursive(Node<T> node) {

        Queue<Node<T>> queue = new LinkedList<Node<T>>();
        Node<T> current = root;

        if (node != null) {
            queue.add(node);
        }

        while (!queue.isEmpty()) {

            Node<T> next = queue.remove();
            levelList.add(next.getData());

            if (next.getLeft() != null) {

                queue.add(next.getLeft());

            }

            if (next.getRight() != null) {

                queue.add(next.getRight());

            }
        }
    }


    @Override
    public void clear() {

        root = null;
        size = 0;

    }

    @Override
    public int height() {

        return heightRecursive(root);

    }

    /**
     * private helper method to facilitate calculating height
     *
     * @param node a node to begin with.
     *
     */
    private int heightRecursive(Node<T> node) {

        if (node == null) {

            return -1;

        } else {

            return 1 + Math.max(heightRecursive(node.getLeft()),
                    heightRecursive(node.getRight()));

        }
    }

    @Override
    public Node<T> getRoot() {

        return root;

    }

}
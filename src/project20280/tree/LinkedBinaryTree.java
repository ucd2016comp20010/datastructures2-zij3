package project20280.tree;

import project20280.interfaces.List;
import project20280.interfaces.Position;

import java.lang.reflect.Array;
import java.util.ArrayList;
// import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // constructor

    public static LinkedBinaryTree<Integer> makeRandom(int n) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        return bt;
    }

    // nonpublic utility

    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last) {
        if (first > last) return null;
        else {
            Integer treeSize = last - first + 1;
            Integer leftCount = rnd.nextInt(treeSize);
            Integer rightCount = treeSize - leftCount - 1;
            Node<T> root = new Node<T>((T) ((Integer) (first + leftCount)), parent, null, null);
            root.setLeft(randomTree(root, first, first + leftCount - 1));
            root.setRight(randomTree(root, first + leftCount + 1, last));
            return root;
        }
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    public static void main(String [] args) {
        LinkedBinaryTree<String> bt = new LinkedBinaryTree<>();
        String[] arr = { "A", "B", "C", "D", "E", null, "F", null, null, "G", "H", null, null, null, null };
        bt.createLevelOrder(arr);
        System.out.println(bt.toBinaryTreeString());
        System.out.println(bt.size());
        System.out.println(bt.numLeaves(bt.root()));

        // Height
        LinkedBinaryTree<Integer> bt1 = new LinkedBinaryTree<>();
        Integer [] arr1 = new Integer[] {1,
                2,3,
                4,5,6,7,
                8,9,10,11,12, 13, 14, 15,
                16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,
                null,null,null ,35};

        bt1.createLevelOrder(arr1);
        System.out.println(bt1.height());

        // Diameter
        LinkedBinaryTree<Integer> bt2 = new LinkedBinaryTree<>();
        Integer [] arr2 = new Integer[] {1,
                2,3,
                4,5,6,7,
                8,9,10,11,12, 13, 14, 15,
                16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,
                null,null,null ,35};

        bt2.createLevelOrder(arr2);
        System.out.println(bt2.diameter());

        // Inorder + Postorder
        Integer [] inorder= {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
                18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        Integer [] preorder= {18, 2, 1, 14, 13, 12, 4, 3, 9, 6, 5, 8, 7, 10, 11, 15, 16,
                17, 28, 23, 19, 22, 20, 21, 24, 27, 26, 25, 29, 30};

        LinkedBinaryTree <Integer > bt3 = new LinkedBinaryTree <>();
        bt3.construct(inorder , preorder);
        System.out.println(bt3.toBinaryTreeString());


    }

    private int preIndex = 0;
    public void construct(Integer[] inorder, Integer[] preorder) {

        size = inorder.length;
        root = (Node<E>) recConstruct(inorder, 0, inorder.length - 1, preorder);

    }

    private Node<Integer> recConstruct(Integer[] inorder, int inStart, int inEnd, Integer[] preorder) {

        if (inStart > inEnd) {
            return null;
        }


        Integer rootVal = preorder[preIndex];
        preIndex++;

        Node<Integer> node = new Node<>(rootVal, null, null, null);

        int inIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i].equals(rootVal)) {
                inIndex = i;
                break;
            }
        }

        node.setLeft(recConstruct(inorder, inStart, inIndex - 1, preorder));
        node.setRight(recConstruct(inorder, inIndex + 1, inEnd, preorder));

        if (node.getLeft() != null) {
            node.getLeft().setParent(node);
        }
        if (node.getRight() != null) {
            node.getRight().setParent(node);
        }

        return node;
    }


    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    // update methods supported by this class

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {

        if (!isEmpty()) {
            throw new IllegalStateException();
        }
        root = createNode(e, null, null, null);
        size++;
        return root;
    }

    public void insert(E e) {

        addRecursive(root, e);

    }

    // recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e) {

        Comparable<? super E> element = (Comparable<? super E>) e;
        int compare = element.compareTo(p.getElement());


        if (compare > 0) {
            if (p.getLeft() == null) {
                Node<E> newNode = createNode(e, p, null, null);
                p.setLeft(newNode);
                size++;
                return newNode;
            }

            addRecursive(p.getRight(), e);
        }

        return null;
    }

    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (left(p) != null) {
            throw new IllegalArgumentException("p already has a left child");
        }
        Node<E> newNode = createNode(e, parent, null, null);
        parent.setLeft(newNode);
        size++;
        return newNode;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (right(p) != null) {
            throw new IllegalArgumentException("p already has a right child");
        }
        Node<E> newNode = createNode(e,parent, null,null);
        parent.setRight(newNode);
        size++;
        return newNode;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E e1 = node.getElement();
        node.setElement(e);

        return e1;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> root = validate(p);

        root.setLeft(t1.root);
        root.setRight(t2.root);
        t1.root .setParent(root);
        t2.root.setParent(root);

    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> parent = validate(p);

        if (numChildren(p) == 2) {
            throw new IllegalArgumentException("p has two children");
        }

        Node<E> child = (parent.getLeft() != null) ? parent.getLeft() : parent.getRight();
        E e1 = parent.getElement();

        if (child != null) {
            child.setParent(parent.getParent());
        }
        if (parent == root) {
            root = child;
        }
        else {
            Node<E> grandParent = parent.getParent();
            if (parent == grandParent.getLeft()) {
                grandParent.setLeft(child);
            }
            else {
                grandParent.setRight(child);
            }
        }

        size--;
        parent.setParent(parent);
        return e1;
    }

    public String toString() {
        return positions().toString();
    }

    public void createLevelOrder(ArrayList<E> l) {
        root = null;
        size = 0;
        root = createLevelOrderHelper(l, null, 0);
    }

    private Node<E> createLevelOrderHelper(java.util.ArrayList<E> l, Node<E> p, int i) {
        if ( i < l.size() ) {
            Node<E> node = createNode(l.get(i), p, null, null);
            size++;
            node.setLeft(createLevelOrderHelper(l, node, 2 * i + 1));
            node.setRight(createLevelOrderHelper(l, node, 2 * i + 2));
            return node;
        }
        return null;
    }

    public void createLevelOrder(E[] arr) {

        root = null;
        size = 0;
        root = createLevelOrderHelper(arr, null, 0);

    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i) {

        if ( i >= arr.length ) {
            return null;
        }

        if (arr[i] == null) {
            return null;
        }

        Node<E> node = createNode(arr[i], p, null, null);
        size++;
        node.setLeft(createLevelOrderHelper(arr, node, 2 * i + 1));
        node.setRight(createLevelOrderHelper(arr, node, 2 * i + 2));
        return node;
    }

    private int maxDiameter = 0;


    public int diameter() {
        maxDiameter = 0;
        heightForDiameter(root);
        return maxDiameter;
    }

    private int heightForDiameter(Node<E> node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = heightForDiameter(node.getLeft());
        int rightHeight = heightForDiameter(node.getRight());

        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public int numLeaves(Position<E> p) {

        if (p == null) return 0;

        if (isExternal(p)) {
            return 1;
        }
        else return numLeaves(left(p)) + numLeaves(right(p));

    }


    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }


    public java.util.List<java.util.List<E>> rootToLeafPaths() {
        java.util.List<java.util.List<E>> paths = new java.util.ArrayList<>();

        if (isEmpty()) {
            return paths;
        }

        rootToLeafPathsHelper(root, new java.util.ArrayList<>(), paths);
        return paths;
    }

    private void rootToLeafPathsHelper(Node<E> node, java.util.List<E> currentPath, java.util.List<java.util.List<E>> paths) {
        if (node == null) return;

        currentPath.add(node.getElement());

        if (node.getLeft() == null && node.getRight() == null) {
            paths.add(new java.util.ArrayList<>(currentPath));
        } else {
            rootToLeafPathsHelper(node.getLeft(), currentPath, paths);
            rootToLeafPathsHelper(node.getRight(), currentPath, paths);
        }

        currentPath.remove(currentPath.size() - 1);
    }

    /**
     * Nested static class for a binary tree node.
     */
    public static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        // accessor
        public E getElement() {
            return element;
        }

        // modifiers
        public void setElement(E e) {
            element = e;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> n) {
            left = n;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> n) {
            right = n;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> n) {
            parent = n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (element == null) {
                sb.append("\u29B0");
            } else {
                sb.append(element);
            }
            return sb.toString();
        }
    }


}

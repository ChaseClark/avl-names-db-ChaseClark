package edu.frostburg.cosc310.p00;

public class CCNamesDB {
    // left and right sub trees can only differ by a height of 1
    private static final int ALLOWED_IMBALANCE = 1;

    private AvlNode<CCRecord> root;

    public CCNamesDB() {
        root = null;
    }

    public void makeEmpty() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Calculates height of a node
     *
     * @param t the node to be evaluated
     * @return the height of the node t, or -1, if null
     */
    private int height(AvlNode<CCRecord> t) {
        return t == null ? -1 : t.height;
    }

    public boolean contains(CCRecord x) {
        return contains(x, root);
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is the item to search for.
     * @param t the node that roots the subtree.
     * @return true if the item is found; false otherwise.
     */
    private boolean contains(CCRecord x, AvlNode<CCRecord> t) {
        if (t==null) return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return contains(x,t.left);
        else if(compareResult > 0)
            return contains(x,t.right);
        else
            return true;
    }

    public CCRecord findMin() {
        if (isEmpty()) return null;
        return findMin(root).element;
    }

    public AvlNode<CCRecord> findMin(AvlNode<CCRecord> t) {
        if (t == null)
            return null;
        else if(t.left == null)
            return t;
        return findMin(t.left);
    }

    public CCRecord findMax() {
        if (isEmpty()) return null;
        return findMax(root).element;
    }

    public AvlNode<CCRecord> findMax(AvlNode<CCRecord> t) {
        if(t != null)
            while(t.right != null)
                t = t.right;
        return t;
    }

    private AvlNode<CCRecord> balance(AvlNode<CCRecord> t) {
        if (t == null) return t;

        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE)
            if (height(t.left.left) >= height(t.left.right))
                t = rotateWithLeftChild(t);
            else
                t = doubleRotateWithLeftChild(t);
        else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE)
            if (height(t.right.right) >= height(t.right.left))
                t = rotateWithRightChild(t);
            else
                t = doubleRotateWithRightChild(t);

        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }

    // whiteboard this sometime
    private AvlNode<CCRecord> rotateWithLeftChild(AvlNode<CCRecord> k2) {
        AvlNode<CCRecord> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    private AvlNode<CCRecord> rotateWithRightChild(AvlNode<CCRecord> k2) {
        AvlNode<CCRecord> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.right), height(k2.left)) + 1;
        k1.height = Math.max(height(k1.right), k2.height) + 1;
        return k1;
    }

    private AvlNode<CCRecord> doubleRotateWithLeftChild(AvlNode<CCRecord> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AvlNode<CCRecord> doubleRotateWithRightChild(AvlNode<CCRecord> k3) {
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }

    // insert first element in tree
    public void insert(CCRecord x) {
        root = insert(x, root);
    }

    public AvlNode<CCRecord> insert(CCRecord x, AvlNode<CCRecord> t) {
        if (t == null)
            return new AvlNode<>(x, null, null);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            ; // duplicate, do nothing
        return balance(t);
    }

    public void remove(CCRecord x) {
        remove(x, root);
    }

    public AvlNode<CCRecord> remove(CCRecord x, AvlNode<CCRecord> t) {
        if (t == null)
            return t; //item not found do nothing
        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            t.left = remove(x, t.left);
        else if (compareResult > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null) { //two children
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        } else
            t = (t.left != null) ? t.left : t.right;
        return balance(t);
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree() {
        if(isEmpty())
            System.out.println("Empty tree!");
        else
            printTree(root);
    }

    private void printTree(AvlNode<CCRecord> t) {
        if(t != null)
        {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }
}

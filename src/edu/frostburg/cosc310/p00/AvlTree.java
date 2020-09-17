package edu.frostburg.cosc310.p00;

public class AvlTree {
    // left and right sub trees can only differ by a height of 1
    private static final int ALLOWED_IMBALANCE = 1;

    private AvlNode<CCRecord> root = null;

    /**
     * Calculates height of a node
     * @param t the node to be evaluated
     * @return the height of the node t, or -1, if null
     */
    private int height(AvlNode<CCRecord> t)
    {
        return t == null ? -1 : t.height;
    }

    private AvlNode<CCRecord> insert(CCRecord x, AvlNode<CCRecord> t)
    {
        if(t == null)
            return new AvlNode<>(x,null,null);

        int compareResult = x.compareTo(t.element);

        if(compareResult < 0)
            t.left = insert(x,t.left);
        else if(compareResult > 0)
            t.right = insert(x, t.right);
        else
            ; // duplicate, do nothing
        return balance(t);
    }

    private AvlNode<CCRecord> balance(AvlNode<CCRecord> t) {
        if(t == null)
            return t;

        if(height(t.left)-height(t.right) > ALLOWED_IMBALANCE)
            if(height(t.left.left) >= height(t.left.right))
                t = rotateWithLeftChild(t);
            else
                t = doubleRotateWithLeftChild(t);
            else if(height(t.right)-height(t.left) > ALLOWED_IMBALANCE)
                if(height(t.right.right) >= height(t.right.left))
                    t = rotateWithRightChild(t);
                else
                    t = doubleRotateWithRightChild(t);

        t.height = Math.max(height(t.left),height(t.right)) + 1;
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
        return k2;
    }

    private AvlNode<CCRecord> doubleRotateWithLeftChild(AvlNode<CCRecord> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AvlNode<CCRecord> doubleRotateWithRightChild(AvlNode<CCRecord> t) {
        return t;
    }

}

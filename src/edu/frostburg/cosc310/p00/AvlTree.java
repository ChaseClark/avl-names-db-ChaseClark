package edu.frostburg.cosc310.p00;

public class AvlTree<AnyType> {

    public int height(AvlNode<AnyType> t)
    {
        return t == null ? -1 : t.height;
    }
}

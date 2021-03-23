package eg.edu.alexu.csd.filestructure.redblacktree;

import java.awt.*;

public class myNode <T extends Comparable<T>, V> implements INode<T,V> {
    private myNode left,right,parent;
    private T key;
    private V val;
    boolean color;
    myNode (T key, V val){
        left=null;
        right=null;
        parent=null;
        this.key=key;
        this.val=val;
        this.color=true;
    }


    @Override
    public void setParent(INode<T, V> parent) {
       this. parent= (myNode) parent;
    }

    @Override
    public INode<T, V> getParent() {

        return this.parent;
    }

    @Override
    public void setLeftChild(INode<T, V> leftChild) {
            left = (myNode) leftChild;
    }

    @Override
    public INode<T, V> getLeftChild() {
        return left;
    }

    @Override
    public void setRightChild(INode<T, V> rightChild) {
            right = (myNode) rightChild;
    }

    @Override
    public INode<T, V> getRightChild() {
        return right;
    }

    @Override
    public T getKey() {

        return key;
    }

    @Override
    public void setKey(T key) {
       this.key=key;
    }

    @Override
    public V getValue() {
        return val;
    }

    @Override
    public void setValue(V value) {
        this.val=value;
    }

    @Override
    public boolean getColor() {
        return this.color;
    }

    @Override
    public void setColor(boolean color) {
        this.color=color;
    }
    @Override
    public boolean isNull() {
        return val==null;
    }
}

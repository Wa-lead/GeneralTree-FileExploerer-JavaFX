package sample;

import java.util.ArrayList;

public class GeneralTreeNode<T extends Comparable<? super T>> {

    protected GeneralTreeNode<T> parent;
    protected T key;
    protected ArrayList<GeneralTreeNode<T>> children;
    protected int level;



    public GeneralTreeNode(T key) {
        this.key = key;
        children = new ArrayList<>();
    }


    @Override
    public String toString() {
        return key+"";

    }
    

    public void setParent(GeneralTreeNode<T> parent) {
        this.parent = parent;
        this.level = parent.level+1;
    }
    public int compareTo(GeneralTreeNode<T> that) {
        if (this.key.compareTo(that.key) > 1)
            return 1;
        else if (this.key.compareTo(that.key) < 1)
            return -1;
        else
            return 0;
    }
}

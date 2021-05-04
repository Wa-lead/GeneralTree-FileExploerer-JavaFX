package sample;

import java.io.File;
import java.util.*;

public class GeneralTree<T extends Comparable<? super T>> {
    private int MAXIMUM = 10;
    GeneralTreeNode<T> rootNode;
    private int folders;

    public GeneralTree(File file, T node) {
        this.rootNode = new GeneralTreeNode<T>(node);
        enter(file, node);
    }

////////////////////////////////////////////////////////////////////////////////////////

    public void setMAXIMUM(int MAXIMUM) {
        if (MAXIMUM < this.MAXIMUM)
            System.out.println("Can't proceed");
        else
            this.MAXIMUM = MAXIMUM;
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    public void insert(T father, T newNode) {
        GeneralTreeNode<T> parenT = find(rootNode, father);
        if (parenT != null) {
            if (parenT.children.size() != MAXIMUM) {
                GeneralTreeNode<T> t = new GeneralTreeNode<T>(newNode);
                t.setParent(parenT);
                parenT.children.add(t);
            } else {
                System.out.println("This node has the maximum allowed children,");
            }
        } else
            System.out.println("This directory does not exist");
    }

////////////////////////////////////////////////////////////////////////////////////////

    public String search(T key) {
        GeneralTreeNode<T> node = find(rootNode, key);
        if (node == null)
            return "This file is not in tree";
        else {
            if (node.parent == null)
                return (String) node.key + " ";
            else
                return  search(node.parent.key)+ " -> " + node.key;
        }
    }
////////////////////////////////////////////////////////////////////////////////////////

    private GeneralTreeNode<T> find(GeneralTreeNode<T> rootNode, T key) {
        if (rootNode.key.equals(key)) {
            return rootNode;
        }
        for (GeneralTreeNode<T> g : rootNode.children) {
            GeneralTreeNode<T> desiredNode = find(g, key);
            if (desiredNode != null) {
                return desiredNode;
            }
        }
        return null;
    }
    
////////////////////////////////////////////////////////////////////////////////////////

    public void delete(T file) {
        GeneralTreeNode<T> node = find(rootNode, file);
        if (node == null) {
            System.out.println("The file does not exist");
            return;
        } else {
            GeneralTreeNode<T> parent = node.parent;
            node.parent = null;
            parent.children.remove(node);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    public String numberOfFiles() {
        folders=0;
        return numberOfFiles(rootNode);
    }
    public String numberOfFiles(GeneralTreeNode<T> node) {
        if (node.children.isEmpty())
            return "";
        else {
            for (GeneralTreeNode<T> g : node.children) {
                folders++;
                numberOfFiles(g);
            }
        }
        return String.valueOf(folders);
    }
    ////////////////////////////////////////////////////////////////////////////////////////

    public void sortByLevel() {
        sortByLevelAux(rootNode);
    }

    public void sortByLevelAux(GeneralTreeNode<T> node) {
        if (node.children.isEmpty())
            return;
        else {
            node.children.sort(Comparator.comparing(o -> o.key));
            for (GeneralTreeNode<T> g : node.children) {
                sortByLevelAux(g);
            }
        }
    }
////////////////////////////////////////////////////////////////////////////////////////

    public String Preorder() {
        return preorder(rootNode);
    }

    protected String preorder(GeneralTreeNode<T> p) {
        if (p != null) {
            String x = p.key + " -> ";
            for (GeneralTreeNode<T> g : p.children) {
                x += preorder(g);
            }
            return x;
        }
        return "";
    }
////////////////////////////////////////////////////////////////////////////////////////

    public String BreadthFirst(){
        return BreadthFirst(rootNode);
    }
    private String BreadthFirst(GeneralTreeNode<T> root){
        String bredthFirst ="";
        if (root == null)
            return "";
        Queue<GeneralTreeNode<T>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            while (q.size()>0) {
                GeneralTreeNode<T> p = q.remove();
                bredthFirst+=p.key + " -> ";
                for (GeneralTreeNode<T> g: p.children)
                    q.add(g);
            }
        }
        return bredthFirst;
    }
////////////////////////////////////////////////////////////////////////////////////////

    public String Postorder() {
        return postorder(rootNode);
    }

    protected String postorder(GeneralTreeNode<T> p) {
        if (p != null) {
            String x = "";
            for (GeneralTreeNode<T> g : p.children) {
                x += postorder(g);
            }
            return x + p.key + " -> ";
        }
        return "";
    }
////////////////////////////////////////////////////////////////////////////////////////

    public String printTree() {
        return "Number of folders in the tree is: "+ numberOfFiles()+"\n\n"+ printTreeAux(rootNode);
    }

    public String printTreeAux(GeneralTreeNode<T> node) {
        String x = "";
        for (int i = 0; i < node.level; i++) {
            x += "    ";
        }
        x += "- " + node + "\n";
        for (GeneralTreeNode<T> g : node.children) {
            x += "\n" + printTreeAux(g);
        }
        return x;
    }
////////////////////////////////////////////////////////////////////////////////////////-

    public void enter(File file, T parent) {
        for (File fileEntry : file.listFiles()) {
            if (fileEntry.isDirectory()) {
                insert(parent, (T) fileEntry.getName());
                enter(fileEntry, (T) fileEntry.getName());
            } else {
                insert(parent, (T) fileEntry.getName());
            }
        }
    }
//////////////////////////////////////////////////////////////////////////////////////// Extra Un-used methods


    public ArrayList<GeneralTreeNode<T>> getLevel(int level) {
        ArrayList<GeneralTreeNode<T>> array = new ArrayList<>();
        array = getLevelAux(array, rootNode, level);
        return array;
    }

    public ArrayList<GeneralTreeNode<T>> getLevelAux(ArrayList<GeneralTreeNode<T>> arrayList, GeneralTreeNode<T> node, int level) {
        if (node.level == level)
            arrayList.add(node);
        for (GeneralTreeNode<T> g : node.children) {
            getLevelAux(arrayList, g, level);
        }
        return arrayList;
    }

    ////////////////////////////////////////////////////////////////////////////////////////--
    public void print() {
        System.out.println(rootNode.key);
        printAUX(rootNode);
    }

    public void printAUX(GeneralTreeNode<T> node) {
        if (node.children.isEmpty())
            return;
        System.out.println(node.level + 1 + " - " + node.children);
        for (GeneralTreeNode<T> g : node.children) {
            printAUX(g);
        }
    }
}


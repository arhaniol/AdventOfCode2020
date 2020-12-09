package org.example.day7;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
    private T data;
    private Node<T> parent;
    private List<Node<T>> children= new ArrayList<Node<T>>();

    public Node() {
    }

    public Node(T data) {
        this.data = data;
        parent=this;
    }

    public void add(T obj){

    }

    private Node getParent(T obj){
        return parent;
    }
}

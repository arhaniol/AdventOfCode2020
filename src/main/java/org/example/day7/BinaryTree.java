package org.example.day7;

import java.util.LinkedList;
import java.util.List;

public class BinaryTree {
    private Node root;
    private List<Bag> bagList;

    public BinaryTree(){
        root=new Node();
        bagList=new LinkedList<>();
    }
    public void add(Bag bag) {
        if (bag != null) {
            if(bagList.size()==0) {
                bagList.add(bag);
            }else{

            }
        }
    }


//    public void add()
}

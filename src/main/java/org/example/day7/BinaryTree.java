package org.example.day7;

import java.util.*;

public class BinaryTree {
    List<Bag> nodes;

    public BinaryTree() {
        nodes = new ArrayList<>();
    }

    public void addNode(Bag bag) {
        if (nodes.contains(bag)) {
            throw new IllegalArgumentException("Cos jest nie tak: " + bag.getName() + " już istnieje!");
        }
        for (Bag node : nodes) {
            if (node.getChildren().containsKey(bag.getName())) {
                bag.addParent(node);
            }
            if (bag.getChildren().containsKey(node.getName())) {
                node.addParent(bag);
            }
        }
        nodes.add(bag);
    }

    public Set<Bag> getAllParents(String nodeName) {
        Set<Bag> bags = new HashSet<>();
        if (nodeName == null || nodeName.length() == 0) {
            return bags;
        }
        for (Bag node : nodes) {
            if (node.getName().equals(nodeName)) {
                bags.addAll(node.getParents());
                for (Bag bag : node.getParents()) {
                    bags.addAll(getAllParents(bag.getName()));
                }
            }
        }
        return bags;
    }

    public int getAllChildren(String rootName) {
        if (rootName == null || rootName.length() == 0) {
            return 0;
        }
        int counter = 1;
        for (Bag node : nodes) {
            if (node.getName().equals(rootName)) {
                for (String name : node.getChildren().keySet()) {
                    counter += node.getChildren().get(name) * getAllChildren(name);
                }
            }
        }
        return counter;
    }
}

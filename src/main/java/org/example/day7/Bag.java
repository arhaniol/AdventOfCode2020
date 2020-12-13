package org.example.day7;

import java.util.*;

public class Bag {
    private String name;
    private List<Bag> parents;
    private Map<String,Integer> children;

    public boolean addParent(Bag parent){
        if(!parents.contains(parent)){
            parents.add(parent);
            return true;
        }
        return false;
    }

    public Bag() {
        parents=new ArrayList<>();
        children =new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bag> getParents() {
        return parents;
    }

    public void setParents(List<Bag> parents) {
        this.parents = parents;
    }

    public Map<String, Integer> getChildren() {
        return children;
    }

    public void setChildren(Map<String, Integer> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bag)) return false;
        Bag bag = (Bag) o;
        return name.equals(bag.name) && Objects.equals(children, bag.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children);
    }
}

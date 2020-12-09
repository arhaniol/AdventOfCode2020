package org.example.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bag {
    private String color;
    private List<Bag> content;

    public Bag() {
        content = new ArrayList<>();
    }

    public Bag(String color, List<Bag> content) {
        this.color = color;
        if (content == null) {
            this.content = null;
        } else {
            this.content = new ArrayList<>(content);
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setContent(List<Bag> content) {
        this.content = content;
    }

    public void addToContent(Bag bag) {
        if (bag != null && content != null) {
            content.add(bag);
        } else {
            content = null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bag bag = (Bag) o;
        return Objects.equals(color, bag.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}

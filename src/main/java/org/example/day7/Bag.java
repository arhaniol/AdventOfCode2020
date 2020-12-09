package org.example.day7;

import java.util.List;

public class Bag {
    private String color;
    private List<Bag> contain;

    public Bag() {
    }

    public Bag(String color, List<Bag> contain) {
        this.color = color;
        this.contain = contain;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}

package org.example.day7;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HandyHaversacks {

    private BinaryTree tree;

    public HandyHaversacks(String file) {
        tree = new BinaryTree();
        parseInput(file);

//        Bag blu = new Bag("blue", null);
//        Bag black = new Bag("black", null);
//
//        List<Bag> bagList = new ArrayList<>();
//        bagList.add(blu);
//        bagList.add(black);
//
//        Bag plum = new Bag("plum", bagList);
//        Bag olive = new Bag("olive", bagList);
//
//        bagList.clear();
//        bagList.add(plum);
//        bagList.add(olive);
//
//        Bag gold = new Bag("gold", bagList);

    }

    private void parseInput(String file) {
        List<String> input = getInput(file);
        if (input == null) {
            return;
        }
        Pattern pattern = Pattern.compile("(.+) bags contain (.+)\\.");
        Matcher matcher;
        for (String txt : input) {
            matcher = pattern.matcher(txt);
            if (matcher.matches()) {
                Bag bag = new Bag();
                String root = matcher.group(1);
                bag.setColor(root);
                String other = matcher.group(2);
                bag.setContent(getContent(other));
                tree.add(bag);
            }
        }
    }

    private List<Bag> getContent(String text) {
        if (text.length() < 1) {
            return null;
        }
        Pattern pattern = Pattern.compile("(no other bags.|.+)");
        Matcher matcher = pattern.matcher(text);
        if (!matcher.find()) {
            return null;
        }
        if (matcher.group(1).equals("no other bags.")) {
            return null;
        } else {
            String[] colors = matcher.group(1).split(", ");
            Pattern patternBag = Pattern.compile("(\\d) (.+) bags*");
            if (colors.length > 0) {
                List<Bag> bags = new ArrayList<>();
                for (String s : colors) {
                    Matcher matcherBag = patternBag.matcher(s);
                    if (matcherBag.matches()) {
                        int capacity = Integer.parseInt(matcherBag.group(1));
                        Bag bag = new Bag();
                        String color = matcherBag.group(2);
                        bag.setColor(color);
                        bags.add(bag);
                    }
                }
                return bags;
            } else {
                return null;
            }
        }
    }

    /**
     * Function collect data from file
     *
     * @param file name of file to read
     * @return List of Integer data
     */
    private List<String> getInput(String file) {
        String arr,
                mainPath = Paths.get("").toAbsolutePath().toString(),
                javaPath = "\\src\\main\\java",
                packPath = Paths.get(this.getClass().getPackage().getName()).toString().replace('.', '\\');

        try {
            arr = new String(Files.readAllBytes(Paths.get(mainPath, javaPath, packPath, file)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return Stream.of(arr.split("\r\n")).collect(Collectors.toList());
    }

    public int getPart1() {
        return 0;
    }
}

package org.example.day7;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HandyHaversacks {


    public HandyHaversacks(String file) {
        parseInput(file);

        Bag blu = new Bag("blue", null);
        Bag black = new Bag("black", null);

        List<Bag> bagList = new ArrayList<>();
        bagList.add(blu);
        bagList.add(black);

        Bag plum = new Bag("plum", bagList);
        Bag olive = new Bag("olive", bagList);

        bagList.clear();
        bagList.add(plum);
        bagList.add(olive);

        Bag gold = new Bag("gold", bagList);

    }

    private void parseInput(String file) {
        List<String> input = getInput(file);
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
}

package org.example.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TobogganTrajectory {

    private final List<String> forest;

    public TobogganTrajectory(String file) {
        forest = getInput( file);
        if (forest == null) {
            throw new IllegalArgumentException("No data");
        }
    }

    public long getPart1() {
        return countTree(3, 1);
    }

    public long getPart2() {
        return countTree(1, 1) *
                countTree(3, 1) *
                countTree(5, 1) *
                countTree(7, 1) *
                countTree(1, 2);
    }

    private long countTree(int right, int down) {
        int result = 0;
        int pos = right;
        for (int i = down; i < forest.size(); i += down) {
            String row = forest.get(i);
            if ((pos) >= row.length()) {
                pos = pos % row.length();
            }
            if (row.charAt(pos) == '#') {
                result++;
            }
            pos += right;
        }
        return result;
    }

    /**
     * Function collect data from file
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

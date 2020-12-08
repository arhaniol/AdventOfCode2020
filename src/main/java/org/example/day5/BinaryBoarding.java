package org.example.day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BinaryBoarding {

    private List<String> places;
    private List<Integer> ids;

    public BinaryBoarding(String s) {
        ids = new ArrayList<>();
        places = getInput(s);
    }

    public int getPart1() {
        int row, column, max = -1;
        for (String place : places) {
            row = findRow(place.substring(0, 7));
            column = findColumn(place.substring(7));
            ids.add(row * 8 + column);
            max = Math.max(row * 8 + column, max);
        }
        return max;
    }

    private int findColumn(String col) {
        int low = 0;
        int up = 7;
        int size = 8;
        for (int i = 0; i < col.length(); i++) {
            size /= 2;
            if (col.charAt(i) == 'R') {
                low += size;
            } else {
                up -= size;
            }
        }
        if (low == up) {
            return low;
        } else {
            return -1;
        }
    }

    private int findRow(String row) {
        int low = 0;
        int up = 127;
        int size = 128;
        for (int i = 0; i < row.length(); i++) {
            size /= 2;
            if (row.charAt(i) == 'F') {
                up -= size;
            } else {
                low += size;
            }
        }
        if (low == up) {
            return low;
        } else {
            return -1;
        }
    }

    public int getPart2() {
        Collections.sort(ids);
        int missing = -1;
        for (int i = ids.size() - 1; i > 0; i--) {
            if ((ids.get(i) - ids.get(i - 1)) != 1) {
                missing = ids.get(i) - 1;
                break;
            }
        }
        return missing;
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

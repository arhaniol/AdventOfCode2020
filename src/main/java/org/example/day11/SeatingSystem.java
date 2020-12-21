package org.example.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SeatingSystem {
    private final List<String> seats;

    public SeatingSystem(String file) {
        seats = getInput(file);
        makeOccupied();
        boolean notTheSame;
        do {
            List<String> temp = applyRules();
            notTheSame = temp.equals(seats);
            seats.clear();
            seats.addAll(temp);
        } while (!notTheSame);
    }

    private List<String> applyRules() {
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < seats.size(); i++) {
            StringBuilder row = new StringBuilder(seats.get(i));
            for (int j = 0; j < seats.get(i).length(); j++) {
                if (seats.get(i).charAt(j) == '#') {
                    row.setCharAt(j, getState(i, j));
                }
            }
            temp.add(row.toString());
        }
        return temp;
    }

    private char getState(int row, int col) {
        int countHash = 0;
        int start, stop;
        if ((row - 1) >= 0) {
            if ((col - 1) >= 0) {
                start = col - 1;
            } else {
                start = col;
            }
            if ((col + 1) < seats.get(row - 1).length()) {
                stop = col + 1;
            } else {
                stop = col;
            }
            for (int i = start; i <= stop; i++) {
                if (seats.get(row - 1).charAt(i) == '#') {
                    countHash++;
                }
            }
        }
        if ((col - 1) >= 0 && seats.get(row).charAt(col - 1) == '#') {
            countHash++;
        }
        if ((col + 1) < seats.get(row).length() && seats.get(row).charAt(col + 1) == '#') {
            countHash++;
        }
        if ((row + 1) < seats.size()) {
            if ((col - 1) >= 0) {
                start = col - 1;
            } else {
                start = col;
            }
            if ((col + 1) < seats.get(row + 1).length()) {
                stop = col + 1;
            } else {
                stop = col;
            }
            for (int i = start; i <= stop; i++) {
                if (seats.get(row + 1).charAt(i) == '#') {
                    countHash++;
                }
            }
        }
        if (countHash > 3) {
            return 'L';
        } else {
            return '#';
        }
    }

    private void makeOccupied() {
        List<String> temp = new ArrayList<>();
        for (String row : seats) {
            row = row.replace('L', '#');
            temp.add(row);
        }
        seats.clear();
        seats.addAll(temp);
    }

    private int countOccupied() {
        int result = 0;
        for (String row : seats) {
            for (Character c : row.toCharArray()) {
                if (c == '#') {
                    result++;
                }
            }
        }
        return result;
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
        return countOccupied();
    }

    public int getPart2() {
        return 0;
    }
}

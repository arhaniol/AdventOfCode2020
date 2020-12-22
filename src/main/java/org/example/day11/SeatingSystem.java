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
        int startX = getStart(col);
        int stopX = getStop(col, seats.get(row).length());
        int startY = getStart(row);
        int stopY = getStop(row, seats.size());

        for (int k = startY; k <= stopY; k++) {
            String oldRow = seats.get(k);
            for (int l = startX; l <= stopX; l++) {
                if (k != row || l != col) {
                    if (oldRow.charAt(l) == '#') {
                        countHash++;
                    }
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
        for (int i = 0; i < seats.size(); i++) {
            StringBuilder row = new StringBuilder(seats.get(i));
            for (int j = 0; j < seats.get(i).length(); j++) {
                if (seats.get(i).charAt(j) == 'L') {
                    int neighbour = 8;
                    int freeChair = 0;
                    int startX, stopX, startY, stopY;

                    startY = getStart(i);
                    stopY = getStop(i, seats.size());

                    if (startY == i) {
                        neighbour -= 3;
                    }
                    if (stopY == i) {
                        neighbour -= 3;
                    }

                    startX = getStart(j);
                    stopX = getStop(j, seats.get(i).length());

                    if (startX == j) {
                        neighbour -= 3;
                        if (startY == i || stopY == i) {
                            neighbour++;
                        }
                    }
                    if (stopX == j) {
                        neighbour -= 3;
                        if (startY == i || stopY == i) {
                            neighbour++;
                        }
                    }

                    for (int k = startY; k <= stopY; k++) {
                        String oldRow = seats.get(k);
                        for (int l = startX; l <= stopX; l++) {
                            if (k != i || l != j) {
                                if (oldRow.charAt(l) == 'L') {
                                    freeChair++;
                                }
                                if (oldRow.charAt(l) == '.') {
                                    neighbour--;
                                }
                            }
                        }
                    }
                    if (freeChair == neighbour) {
                        row.setCharAt(j, '#');
                    }
                }
            }
            temp.add(row.toString());
        }
        seats.clear();
        seats.addAll(temp);
    }

    private int getStart(int pos) {
        int start;
        if ((pos - 1) >= 0) {
            start = pos - 1;
        } else {
            start = pos;
        }
        return start;
    }

    private int getStop(int pos, int limit) {
        int stop;
        if ((pos + 1) < limit) {
            stop = pos + 1;
        } else {
            stop = pos;
        }
        return stop;
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
        boolean notTheSame;
        do {
            makeOccupied();
            List<String> temp = applyRules();
            notTheSame = temp.equals(seats);
            seats.clear();
            seats.addAll(temp);
        } while (!notTheSame);
        return countOccupied();
    }

    public int getPart2() {
        return 0;
    }
}

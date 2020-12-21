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
//        emptyToOccupied();
        List<String> temp = applyRules('L');
        seats.clear();
        seats.addAll(temp);
        boolean notTheSame;
        do {
            temp = applyRules('#');
            notTheSame = temp.equals(seats);
            seats.clear();
            seats.addAll(temp);
        } while (!notTheSame);
    }

    private List<String> applyRules(char sign) {
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < seats.size(); i++) {
            StringBuilder row = new StringBuilder(seats.get(i));
            for (int j = 0; j < seats.get(i).length(); j++) {
                if (seats.get(i).charAt(j) == sign) {
                    row.setCharAt(j, getNewState(i, j, sign));
                }
            }
            temp.add(row.toString());
        }
        return temp;
    }

    private char getNewState(int row, int col, char sign) {
        int countHash = 0;
        int neighbour = 8;
        int start, stop;
        if ((row - 1) >= 0) {
            if ((col - 1) >= 0) {
                start = col - 1;
            } else {
                start = col;
                neighbour--;
            }
            if ((col + 1) < seats.get(row - 1).length()) {
                stop = col + 1;
            } else {
                stop = col;
                neighbour--;
            }
            for (int i = start; i <= stop; i++) {
                if (seats.get(row - 1).charAt(i) == sign) {
                    countHash++;
                }
            }
        } else {
            neighbour -= 3;
        }
        if ((col - 1) >= 0 && seats.get(row).charAt(col - 1) == sign) {
            countHash++;
        } else {
            neighbour--;
        }
        if ((col + 1) < seats.get(row).length() && seats.get(row).charAt(col + 1) == sign) {
            countHash++;
        } else {
            neighbour--;
        }
        if ((row + 1) < seats.size()) {
            if ((col - 1) >= 0) {
                start = col - 1;
            } else {
                start = col;
                neighbour--;
            }
            if ((col + 1) < seats.get(row + 1).length()) {
                stop = col + 1;
            } else {
                stop = col;
                neighbour--;
            }
            for (int i = start; i <= stop; i++) {
                if (seats.get(row + 1).charAt(i) == sign) {
                    countHash++;
                }
            }
        } else {
            neighbour -= 3;
        }
        if (sign == '#') {
            if (countHash > 3) {
                return 'L';
            } else {
                return '#';
            }
        } else {
            if (countHash == neighbour) {
                return '#';
            } else {
                return 'L';
            }
        }
    }

    private void emptyToOccupied() {
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < seats.size(); i++) {
            StringBuilder row = new StringBuilder(seats.get(i));
            for (int j = 0; j < seats.get(i).length(); j++) {
                if (seats.get(i).charAt(j) == 'L') {
                    row.setCharAt(j, getNewState(i, j, 'L'));
                }
            }
            temp.add(row.toString());
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

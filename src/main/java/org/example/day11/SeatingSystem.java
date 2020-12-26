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
    private int tolerant;

    public SeatingSystem(String file) {
        seats = getInput(file);
    }

    private List<String> applyRules() {
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < seats.size(); i++) {
            StringBuilder row = new StringBuilder(seats.get(i));
            for (int j = 0; j < seats.get(i).length(); j++) {
                if (seats.get(i).charAt(j) == '#') {
                    if (tolerant == 3) {
                        row.setCharAt(j, getStatePart1(i, j));
                    } else if (tolerant == 4) {
                        row.setCharAt(j, getStatePart3(i, j));
                    } else {
                        throw new IllegalArgumentException("This should not happened");
                    }
                }
            }
            temp.add(row.toString());
        }
        return temp;
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    private void makeOccupied2() {
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < seats.size(); i++) {
            StringBuilder row = new StringBuilder(seats.get(i));
            for (int j = 0; j < seats.get(i).length(); j++) {
                if (seats.get(i).charAt(j) == 'L') {
                    int neighbour = 8;
                    int freeChair = 0;
                    int left = j;
                    int top = i;
                    int right = seats.get(i).length() - j - 1;
                    int bottom = seats.size() - i - 1;
                    int leftTop = Math.min(left, top);
                    int rightTop = Math.min(right, top);
                    int rightBottom = Math.min(right, bottom);
                    int leftBottom = Math.min(left, bottom);
                    int result;

                    //left
                    result = whatIsInDirection(-1, 0, left, i, j);
                    if (result > 0) {
                        freeChair++;
                    } else if (result == 0) {
                        neighbour--;
                    }

                    // top left
                    result = whatIsInDirection(-1, 1, leftTop, i, j);
                    if (result > 0) {
                        freeChair++;
                    } else if (result == 0) {
                        neighbour--;
                    }

                    // top
                    result = whatIsInDirection(0, 1, top, i, j);
                    if (result > 0) {
                        freeChair++;
                    } else if (result == 0) {
                        neighbour--;
                    }

                    // top right
                    result = whatIsInDirection(1, 1, rightTop, i, j);
                    if (result > 0) {
                        freeChair++;
                    } else if (result == 0) {
                        neighbour--;
                    }

                    // right
                    result = whatIsInDirection(1, 0, right, i, j);
                    if (result > 0) {
                        freeChair++;
                    } else if (result == 0) {
                        neighbour--;
                    }

                    // bottom right
                    result = whatIsInDirection(1, -1, rightBottom, i, j);
                    if (result > 0) {
                        freeChair++;
                    } else if (result == 0) {
                        neighbour--;
                    }

                    // bottom
                    result = whatIsInDirection(0, -1, bottom, i, j);
                    if (result > 0) {
                        freeChair++;
                    } else if (result == 0) {
                        neighbour--;
                    }

                    // bottom left
                    result = whatIsInDirection(-1, -1, leftBottom, i, j);
                    if (result > 0) {
                        freeChair++;
                    } else if (result == 0) {
                        neighbour--;
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

    /**
     * function return what can be seen from point(row,col)
     *
     * @param x:    1 - positive X axle; -1 - negative X axle; 0 - only Y axle
     * @param y:    1 - positive Y axle; -1 - negative Y axle; 0 - only X axle
     * @param limit limit of search
     * @param row   start Y position
     * @param col   start X position
     * @return: 1 - see empty chair; 0 - see no chair; -1 - see occupied chair
     */
    private int whatIsInDirection(int x, int y, int limit, int row, int col) {
        for (int i = 1; i <= limit; i++) {
            if (seats.get(row - y * i).charAt(col + x * i) == 'L') {
                return 1;
            } else if (seats.get(row - y * i).charAt(col + x * i) == '#') {
                return -1;
            }
        }
        return 0;
    }

    private int countDirectionHash(int x, int y, int limit, int row, int col) {

        for (int i = 1; i <= limit; i++) {
            char sign = seats.get(row - y * i).charAt(col + x * i);
            if (sign == '#') {
                return 1;
            } else if (sign == 'L') {
                return 0;
            }
        }
        return 0;
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    private char getStatePart3(int row, int col) {
        int countHash;
        int left = col;
        int top = row;
        int right = seats.get(row).length() - col - 1;
        int bottom = seats.size() - row - 1;
        int leftTop = Math.min(left, top);
        int rightTop = Math.min(right, top);
        int rightBottom = Math.min(right, bottom);
        int leftBottom = Math.min(left, bottom);

        //left
        countHash = countDirectionHash(-1, 0, left, row, col);

        //left top
        countHash += countDirectionHash(-1, 1, leftTop, row, col);

        //top
        countHash += countDirectionHash(0, 1, top, row, col);

        //right top
        countHash += countDirectionHash(1, 1, rightTop, row, col);

        //right
        countHash += countDirectionHash(1, 0, right, row, col);

        //right bottom
        countHash += countDirectionHash(1, -1, rightBottom, row, col);

        //bottom
        countHash += countDirectionHash(0, -1, bottom, row, col);

        //left bottom
        countHash += countDirectionHash(-1, -1, leftBottom, row, col);

        if (countHash > tolerant) {
            return 'L';
        } else {
            return '#';
        }
    }

    private char getStatePart1(int row, int col) {
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

        if (countHash > tolerant) {
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
        tolerant = 3;
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
        tolerant = 4;
        boolean notTheSame;
        do {
            makeOccupied2();
            List<String> temp = applyRules();
            notTheSame = temp.equals(seats);
            seats.clear();
            seats.addAll(temp);
        } while (!notTheSame);
        return countOccupied();
    }
}

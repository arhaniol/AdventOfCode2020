package org.example.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RainRisk {
    private List<String> list;
    private List<String> commends;
    private int east;
    private int north;
    private int currentDirection;

    public RainRisk(String file) {
        list = getInput(file);
    }

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
        for (String line : list) {
            char direction = line.charAt(0);
            int unit = Integer.parseInt(line.substring(1));
            switch (direction) {
                case 'N':
                    north += unit;
                    break;
                case 'S':
                    north -= unit;
                    break;
                case 'E':
                    east += unit;
                    break;
                case 'W':
                    east -= unit;
                    break;
                case 'L':
                    currentDirection += unit;
                    break;
                case 'R':
                    currentDirection += 360 - unit;
                    break;
                case 'F':
                    currentDirection %= 360;
                    switch (currentDirection) {
                        case 0:
                            east += unit;
                            break;
                        case 90:
                            north += unit;
                            break;
                        case 180:
                            east -= unit;
                            break;
                        case 270:
                            north -= unit;
                            break;
                    }
                    break;
            }
        }
        return Math.abs(north) + Math.abs(east);
    }

    public int getPart2() {
        north = 1;
        east = 10;

        int x = 0;
        int y = 0;

        for (String line : list) {
            char direction = line.charAt(0);
            int unit = Integer.parseInt(line.substring(1));
            switch (direction) {
                case 'N':
                    north += unit;
                    break;
                case 'S':
                    north -= unit;
                    break;
                case 'E':
                    east += unit;
                    break;
                case 'W':
                    east -= unit;
                    break;
                case 'L':
                    rotateWayPoint(360 - unit);
                    break;
                case 'R':
                    rotateWayPoint(unit);
                    break;
                case 'F':
                    x += east * unit;
                    y += north * unit;
                    break;
            }
        }

        return Math.abs(x) + Math.abs(y);
    }

    private void rotateWayPoint(int degree) {
        int temp;
        switch (degree) {
            case 0:
                break;
            case 90:
                temp = east;
                east = north;
                north = (-1) * temp;
                break;
            case 180:
                east = (-1) * east;
                north = (-1) * north;
                break;
            case 270:
                temp = north;
                north = east;
                east = (-1) * temp;
                break;
        }
    }
}

package org.example.day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TobogganTrajectory {



    /**
     * Function collect data from file
     *
     * @return List of Integer data
     */
    private List<String> getInput() {
        String arr,
                path = "C:\\Users\\michal.musial\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\org\\example\\day2\\";
        String file = "input.txt";
        try {
            arr = new String(Files.readAllBytes(Paths.get(path + file)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return Stream.of(arr.split("\r\n")).collect(Collectors.toList());
    }

}

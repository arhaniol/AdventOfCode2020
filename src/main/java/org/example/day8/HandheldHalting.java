package org.example.day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HandheldHalting {
    private List<String> list;
    private int accumulator;
    private List<Integer> performed;

    public HandheldHalting(String file){
        list=getInput(file);
        performed=new ArrayList<>();
    }

    private void runOneLoop() {
        for(String line:list){

        }
    }

    private void parseInput(String input){
        String[] instruction=input.split(" ");

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
        runOneLoop();
        return accumulator;
    }

    public int getPart2() {
        return 0;
    }
}

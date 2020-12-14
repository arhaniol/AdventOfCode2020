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

    public HandheldHalting(String file) {
        list = getInput(file);
        performed = new ArrayList<>();
    }

    private void runOneLoop() {
        for (int i = 0; i < list.size(); i++) {
            if (performed.contains(i)) {
                break;
            }
            String[] instruction = list.get(i).split(" ");
            performed.add(i);
            switch (instruction[0]) {
                case "nop":
                    break;
                case "acc":
                    accumulator += Integer.parseInt(instruction[1]);
                    break;
                case "jmp":
                    i += Integer.parseInt(instruction[1]) - 1;
                    break;
                default:
                    throw new IllegalArgumentException("To nie powinno się wydarzyć");
            }
        }
    }

    private boolean hasLoop(List<String> instructions) {
        List<Integer> read = new ArrayList<>();
        for (int i = 0; i < instructions.size(); i++) {
            if (read.contains(i)) {
                return false;
            }
            String[] instruction = instructions.get(i).split(" ");
            read.add(i);
            switch (instruction[0]) {
                case "nop":
                    break;
                case "acc":
                    accumulator += Integer.parseInt(instruction[1]);
                    break;
                case "jmp":
                    i += Integer.parseInt(instruction[1]) - 1;
                    if (i < 0 || i > instructions.size()) {
                        return false;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("To nie powinno się wydarzyć!");
            }
        }
        return true;
    }

    private void replaceInstruction() {
        for (int i = 0; i < list.size(); i++) {
            accumulator = 0;
            String[] instruction = list.get(i).split(" ");
            switch (instruction[0]) {
                case "nop":
                    list.set(i, "jmp " + instruction[1]);
                    if (hasLoop(list)) {
                        return;
                    } else {
                        list.set(i, "nop " + instruction[1]);
                    }
                    break;
                case "jmp":
                    list.set(i, "nop " + instruction[1]);
                    if (hasLoop(list)) {
                        return;
                    } else {
                        list.set(i, "jmp " + instruction[1]);
                    }
                    break;
            }
        }
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
        replaceInstruction();
        return accumulator;
    }
}

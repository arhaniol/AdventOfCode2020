package org.example.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomCustoms {
    private List<String> groups;
    private int part1;
    private int part2;

    public CustomCustoms(String s) {
        groups = getInput(s);
        countPart1();
        countPart2();
    }

    private void countPart1() {
        for (String group : groups) {
            Set<Character> set = new HashSet<>();
            for (int i = 0; i < group.length(); i++) {
                char c = group.charAt(i);
                if (c != '\r' && c != '\n') {
                    if (!set.contains(c)) {
                        set.add(c);
                        part1++;
                    }
                }
            }
        }
    }

    public int getPart1() {
        return part1;
    }

    public int getPart2() {
        return part2;
    }

    private void countPart2() {
        for (String group : groups) {
            String[] persons = group.split("\r\n");
            if (persons.length == 1) {
                part2 += persons[0].length();
                continue;
            }
            Map<Character, Integer> map = new HashMap<>();
            for (String singlePerson : persons) {
                for (int i = 0; i < singlePerson.length(); i++) {
                    char c = singlePerson.charAt(i);
                    if (!map.containsKey(c)) {
                        map.put(c, 1);
                    } else {
                        map.put(c, map.get(c) + 1);
                    }
                }
            }

            for (Integer value : map.values()) {
                if (value == persons.length) {
                    part2++;
                }
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

        return Stream.of(arr.split("\r\n\r\n")).collect(Collectors.toList());
    }
}

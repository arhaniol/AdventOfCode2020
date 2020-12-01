package org.example.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportRepair {
    /**
     * find that entries that sum to 2020 and then multiply those numbers together.
     */

    List<Integer> input;

    public ReportRepair() {
        input = getInput("input.txt");
        if (input == null) {
            throw new NullPointerException("No data");
        }

    }

    public int findMultiplicationOfSum() {
        Collections.sort(input);
        for (int i = 0; i < input.size() - 2; i++) {
            int a1 = input.get(i);
            for (int j = i + 1; j < input.size() - 1; j++) {
                int a2 = input.get(j);
                int sum1 = a1 + a2;
                if (sum1 > 2020) {
                    break;
                }
                for (int k = j + 1; k < input.size(); k++) {
                    int a3 = input.get(k);
                    int sum2 = sum1 + a3;
                    if (sum2 > 2020) {
                        break;
                    }
                    if (sum2 == 2020) {
                        return a1 * a2 * a3;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * Function collect data from file
     * @param file with data
     * @return List of Integer data
     */
    private List<Integer> getInput(String file) {
        String arr,
                path = "C:\\Users\\michal.musial\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\org\\example\\day1\\";
        try {
            arr = new String(Files.readAllBytes(Paths.get(path + file)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return Stream.of(arr.split("\r\n")).map(Integer::parseInt).collect(Collectors.toList());
    }

}

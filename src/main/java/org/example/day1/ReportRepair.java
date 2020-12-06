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

    private final List<Integer> input;
    private int part1 = 0;
    private int part2 = 0;

    public int getPart1() {
        return part1;
    }

    public int getPart2() {
        return part2;
    }

    public ReportRepair() {
        input = getInput();
        if (input == null) {
            throw new NullPointerException("No data");
        }
    }

    public void findMultiplicationOfSum() {
        Collections.sort(input);
        for (int i = 0; i < input.size() - 2; i++) {
            int a1 = input.get(i);
            for (int j = i + 1; j < input.size() - 1; j++) {
                int a2 = input.get(j);
                int sum1 = a1 + a2;
                if (sum1 > 2020) {
                    break;
                }
                if(sum1==2020){
                    part1=a1*a2;
                }
                for (int k = j + 1; k < input.size(); k++) {
                    int a3 = input.get(k);
                    int sum2 = sum1 + a3;
                    if (sum2 > 2020) {
                        break;
                    }
                    if (sum2 == 2020) {
                        part2= a1 * a2 * a3;
                    }
                }
            }
        }
    }

    /**
     * Function collect data from file
     *
     * @return List of Integer data
     */
    private List<Integer> getInput() {
        String arr,
                mainPath = Paths.get("").toAbsolutePath().toString(),
                javaPath = "\\src\\main\\java",
                packPath = Paths.get(this.getClass().getPackage().getName()).toString().replace('.', '\\'),
                file = "input.txt";

        try {
            arr = new String(Files.readAllBytes(Paths.get(mainPath, javaPath, packPath, file)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return Stream.of(arr.split("\r\n")).map(Integer::parseInt).collect(Collectors.toList());
    }

}

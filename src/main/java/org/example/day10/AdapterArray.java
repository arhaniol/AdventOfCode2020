package org.example.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdapterArray {
    private final List<Integer> list;
    private int dif1;
    private int dir3;
    private long distinctWays = 1;

    public AdapterArray(String file) {
        list = getInput(file);
        Collections.sort(list);
        countDif();
        countVariants();
    }

    private void countVariants() {
        int last = 0;
        int dif;
        int series = 1;

        for (Integer integer : list) {
            dif = integer - last;
            if (dif == 1) {
                series++;
            } else if (dif > 3) {
                throw new IllegalArgumentException("To nie powinno się zdarzyć");
            } else {
                distinctWays *= countPermutation(series);
                series = 1;
            }
            last = integer;
        }
        if (series > 1) {
            distinctWays *= countPermutation(series);
        }
    }

    private long countPermutation(int series) {
        long result = 1;
        if (series < 3) {
            return result;
        }
        if (series == 3) {
            result = 2;
        } else {
            int places = series - 1;
            int div2 = places / 2;
            for (int i = 1; i <= div2; i++) {
                result += factorial(places - i) / (factorial(i) * factorial(places - (2 * i)));
            }
            int div3 = places / 3;
            for (int i = 1; i <= div3; i++) {
                int restPlaces = places - (2 * i);
                result += factorial(restPlaces) / (factorial(i) * factorial(places - (3 * i)));
                div2 = (restPlaces - i) / 2;
                for (int j = 1; j <= div2; j++) {
                    result += factorial(restPlaces - j) / (factorial(i) * factorial(j) * factorial(restPlaces - (3 * i)));
                }
            }
        }
        return result;
    }

    private long factorial(int number) {
        long result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }

    private void countDif() {
        int initial = 0;

        for (Integer integer : list) {
            switch (integer - initial) {
                case 1:
                    dif1++;
                    initial++;
                    break;
                case 2:
                    initial += 2;
                    break;
                case 3:
                    dir3++;
                    initial += 3;
                    break;
                default:
                    throw new IllegalArgumentException("To nie powinno się zdarzyć");

            }
        }
        dir3++;
    }

    /**
     * Function collect data from file
     *
     * @param file name of file to read
     * @return List of Integer data
     */
    private List<Integer> getInput(String file) {
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

        return Stream.of(arr.split("\r\n")).map(Integer::parseInt).collect(Collectors.toList());
    }

    public int getPart1() {
        return dif1 * dir3;
    }

    public long getPart2() {
        return distinctWays;
    }
}

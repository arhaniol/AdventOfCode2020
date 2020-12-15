package org.example.day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EncodingError {
    private List<Long> list;

    public EncodingError(String file) {
        list = getInput(file);
    }

    /**
     * Function collect data from file
     *
     * @param file name of file to read
     * @return List of Integer data
     */
    private List<Long> getInput(String file) {
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

        return Stream.of(arr.split("\r\n")).map(Long::parseLong).collect(Collectors.toList());
    }

    public Long getPart1(int initialLength) {
        return findNotSum(initialLength);
    }

    private Long findNotSum(int initialLength) {
        for (int i = initialLength; i < list.size(); i++) {
            if (!hasSum(list.get(i), i - initialLength, i)) {
                return list.get(i);
            }
        }
        return 0L;
    }

    private boolean hasSum(Long result, int start, int stop) {
        for (int i = start; i < stop; i++) {
            for (int j = i + 1; j < stop; j++) {
                if ((list.get(i) + list.get(j)) == result) {
                    return true;
                }
            }
        }
        return false;
    }

    public long getPart2(int initialLength) {
        Long toFind = getPart1(initialLength);
        int pos = list.indexOf(toFind);
        long sum = 0;
        long min, max;
        for (int i = 0; i < pos - 2; i++) {
            sum = list.get(i);
            min = sum;
            max = sum;
            for (int j = i + 1; j < pos - 1; j++) {
                long temp = sum + list.get(j);
                if (temp > toFind) {
                    break;
                }
                sum += list.get(j);
                min = Math.min(min, list.get(j));
                max = Math.max(max, list.get(j));
                if (temp == toFind) {
                    return min + max;
                }
            }
        }
        return 0;
    }
}

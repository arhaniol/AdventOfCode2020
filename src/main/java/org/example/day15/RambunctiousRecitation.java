package org.example.day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class RambunctiousRecitation {
    private int[] input;
    private final int start;
    private final int stop;

    public RambunctiousRecitation(String file) {
        stop = 30000001;
        start = getInput(file);
    }

    private int getInput(String file) {
        String arr,
                mainPath = Paths.get("").toAbsolutePath().toString(),
                javaPath = "\\src\\main\\java",
                packPath = Paths.get(this.getClass().getPackage().getName()).toString().replace('.', '\\');

        try {
            arr = new String(Files.readAllBytes(Paths.get(mainPath, javaPath, packPath, file)));
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }

        int[] numbers = Stream.of(arr.split(",")).mapToInt(Integer::parseInt).toArray();
        if (numbers.length < stop) {
            input = new int[stop];
            System.arraycopy(numbers, 0, input, 0, numbers.length);
        } else {
            input = numbers;
        }
        return numbers.length;
    }

    public int getPart1(int last) {
        if (last > stop) {
            throw new IllegalArgumentException("Argument must be lower then " + stop);
        }
        for (int i = start; i < last; i++) {
            input[i] = getMostRecentNumber(i - 1);
            if (i % 1000000 == 0) {
                System.out.print(i + " ,");
            }
        }
        return input[last - 1];
    }

    private int getMostRecentNumber(int last) {
        for (int i = last - 1; i >= 0; i--) {
            if (input[last] == input[i]) {
                return last - i;
            }
        }
        return 0;
    }

    public int getPart2(int last) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < start - 1; i++) {
            map.put(input[i], i + 1);
        }
        int lastNumber = input[start - 1];
        int temp;
        for (int i = start; i < last; i++) {
            if (map.containsKey(lastNumber)) {
                temp = i - map.get(lastNumber);
                map.put(lastNumber, i);
                lastNumber = temp;
            } else {
                map.put(lastNumber, i);
                lastNumber = 0;
            }
        }
        return lastNumber;
    }
}

package org.example.day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DockingData {
    private final List<String> list;
    private String mask;
    private final Map<Integer, String> memBin;
    private final Map<Long, Integer> memLong;
    private int address;

    public DockingData(String file) {
        list = getInput(file);
        memBin = new HashMap<>();
        memLong = new HashMap<>();
    }

    public long getPart1() {
        for (String line : list) {
            int val = getValue(line);
            if (val > -1 && !mask.isEmpty()) {
                memBin.put(address, applyMask(val));
            }
        }
        return getSumBin();
    }

    private long getSumBin() {
        long sum = 0;
        for (Integer key : memBin.keySet()) {
            sum += Long.parseLong(memBin.get(key), 2);
        }
        return sum;
    }

    private String applyMask(int val) {
        String binary = Integer.toBinaryString(val);
        StringBuilder result = new StringBuilder("000000000000000000000000000000000000");
        int j = 0;
        char c;
        for (int i = mask.length() - 1; i >= 0; i--, j++) {
            if (j < binary.length()) {
                c = binary.charAt(binary.length() - j - 1);
            } else {
                c = '0';
            }
            if (mask.charAt(i) == 'X') {
                result.setCharAt(i, c);
            } else {
                result.setCharAt(i, mask.charAt(i));
            }
        }
        return result.toString();
    }

    private int getValue(String line) {
        Pattern pattern = Pattern.compile("mask = ([X10]{36})");
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            mask = matcher.group(1);
        } else {
            pattern = Pattern.compile("mem\\[(\\d+)] = (\\d+)");
            matcher = pattern.matcher(line);
            if (matcher.matches()) {
                address = Integer.parseInt(matcher.group(1));
                return Integer.parseInt(matcher.group(2));
            }
        }
        return -1;
    }

    public long getPart2() {

        for (String line : list) {
            int val = getValue(line);
            if (val > -1 && !mask.isEmpty()) {
                storeVal(val, getAddressMask(address));
            }
        }
        return getSumLong();
    }

    private long getSumLong() {
        long sum = 0;
        for (Long key : memLong.keySet()) {
            sum += memLong.get(key);
        }
        return sum;
    }

    private void storeVal(int val, String address) {
        StringBuilder add = new StringBuilder(address);
        for (int i = 0; i < address.length(); i++) {
            if (address.charAt(i) == 'X') {
                add.setCharAt(i, '0');
                storeVal(val, add.toString());
                add.setCharAt(i, '1');
                storeVal(val, add.toString());
                return;
            }
        }
        Long memAddress = Long.parseLong(address, 2);
        memLong.put(memAddress, val);
    }

    private String getAddressMask(int val) {
        String binary = Integer.toBinaryString(val);
        StringBuilder result = new StringBuilder("000000000000000000000000000000000000");
        int j = 0;
        char c;
        for (int i = mask.length() - 1; i >= 0; i--, j++) {
            if (j < binary.length()) {
                c = binary.charAt(binary.length() - j - 1);
            } else {
                c = '0';
            }
            if (mask.charAt(i) == 'X') {
                result.setCharAt(i, 'X');
            } else if (mask.charAt(i) == '1') {
                result.setCharAt(i, '1');
            } else {
                result.setCharAt(i, c);
            }
        }
        return result.toString();
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
}

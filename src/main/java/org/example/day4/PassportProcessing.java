package org.example.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PassportProcessing {

    private final List<String> passports;
    private int part1;
    private int part2;

    public PassportProcessing(String file) {
        passports = getInput(file);
        countValidPassports();
    }

    public int getPart1() {
        return part1;
    }

    public int getPart2() {
        return part2;
    }

    private void countValidPassports() {
        for (String passport : passports) {
            checkPart1(passport);
            checkPart2(passport);
        }
    }

    private void checkPart1(String passport) {
        if (!passport.contains("byr:")) {
            return;
        }
        if (!passport.contains("iyr:")) {
            return;
        }
        if (!passport.contains("eyr:")) {
            return;
        }
        if (!passport.contains("hgt:")) {
            return;
        }
        if (!passport.contains("hcl:")) {
            return;
        }
        if (!passport.contains("ecl:")) {
            return;
        }
        if (!passport.contains("pid:")) {
            return;
        }
        part1++;
    }

    private void checkPart2(String passport) {
        int k = passport.indexOf("byr:");
        if (k >= 0) {
            if (!fourDigitBetween(passport.substring(k + 4, k + 8), 1920, 2002)) {
                return;
            }
        } else {
            return;
        }
        k = passport.indexOf("iyr:");
        if (k >= 0) {
            if (!fourDigitBetween(passport.substring(k + 4, k + 8), 2010, 2020)) {
                return;
            }
        } else {
            return;
        }
        k = passport.indexOf("eyr:");
        if (k >= 0) {
            if (!fourDigitBetween(passport.substring(k + 4, k + 8), 2020, 2030)) {
                return;
            }
        } else {
            return;
        }
        k = passport.indexOf("hgt:");
        if (k >= 0) {
            if (passport.startsWith("cm", k + 7)) {
                int value = Integer.parseInt(passport.substring(k + 4, k + 7));
                if (value < 150 || value > 193) {
                    return;
                }
            } else if (passport.startsWith("in", k + 6)) {
                int value = Integer.parseInt(passport.substring(k + 4, k + 6));
                if (value < 59 || value > 76) {
                    return;
                }
            } else {
                return;
            }
        } else {
            return;
        }
        k = passport.indexOf("hcl:#");
        if (k >= 0) {
            Pattern pattern = Pattern.compile("[0-9a-f]{6}");
            if (!pattern.matcher(passport.substring(k + 5, k + 11)).matches()) {
                return;
            }
        } else {
            return;
        }
        k = passport.indexOf("ecl:");
        if (k >= 0) {
            String eye = passport.substring(k + 4, k + 7);
            if (!eye.contains("blu") &&
                    !eye.contains("brn") &&
                    !eye.contains("gry") &&
                    !eye.contains("grn") &&
                    !eye.contains("hzl") &&
                    !eye.contains("oth")) {
                return;
            }
        } else {
            return;
        }
        k = passport.indexOf("pid:");
        if (k >= 0) {
            Pattern pattern = Pattern.compile("\\d{9}");
            if (passport.length() < (k + 13) || !pattern.matcher(passport.substring(k + 4, k + 13)).matches()) {
                return;
            }
        } else {
            return;
        }
        part2++;
    }

    private boolean fourDigitBetween(String digit, int min, int max) {
        if (digit.length() != 4) {
            return false;
        }
        if (min > max) {
            return false;
        }
        int val = Integer.parseInt(digit);
        return val >= min && val <= max;
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

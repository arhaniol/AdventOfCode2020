package org.example.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PassportProcessing {

    private final List<Passport> passports;
    private int part1;
    private int part2;
    private static PassportCriteria criteria;

    public PassportProcessing(String file) {
        criteria=new PassportCriteria();
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
        for (Passport passport : passports) {
            if (passport.isAllFields()) {
                part1++;
            }
            if (criteria.checkPassport(passport)) {
                part2++;
            }
        }
    }

    /**
     * Function collect data from file
     *
     * @param file name of file to read
     * @return List of Integer data
     */
    private List<Passport> getInput(String file) {
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

        return Stream.of(arr.split("\r\n\r\n")).map(Passport::new).collect(Collectors.toList());
    }
}

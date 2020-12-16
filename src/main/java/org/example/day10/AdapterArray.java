package org.example.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdapterArray {
    private List<Integer> list;
    private int dif1;
    private int dir3;

    public AdapterArray(String file) {
        list = getInput(file);
        Collections.sort(list);
        countDif();
    }

    void countVariants() {
        int initial = 0;
        int dif,priv;
        int count1=0;
        int count2=0;
        int count3=0;

        for (Integer integer : list) {
            dif = integer - initial;
            if (dif == 1) {
                count1++;
            }else if(dif==2){
            priv=dif;
        }
    }

    void countDif() {
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

    public int getPart2() {
        return dir3;
    }
}

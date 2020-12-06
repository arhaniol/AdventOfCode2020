package org.example.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PasswordPhilosophy {
    private final List<String> passwords;
    private int part1=0;
    private int part2=0;

    public int getPart1() {
        return part1;
    }

    public int getPart2() {
        return part2;
    }

    public PasswordPhilosophy() {
        passwords = getInput();
    }

    public void countValidPassword() {

        for (String password : passwords) {
            Pattern pattern = Pattern.compile("(\\d+)-(\\d+) ([a-z]): (\\w+)");
            Matcher matcher = pattern.matcher(password);
            if (matcher.matches()) {
                int min = Integer.parseInt(matcher.group(1)),
                        max = Integer.parseInt(matcher.group(2));
                char s = matcher.group(3).charAt(0);
                String text = matcher.group(4);

                if (part1(min, max, s, text)) {
                    part1++;
                }

                if (part2(min, max, s, text)) {
                    part2++;
                }
            }
        }
    }

    public boolean part1(int min, int max, char s, String password) {
        int counter = 0;
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) == s) {
                counter++;
            }
        }
        return counter >= min && counter <= max;
    }

    public boolean part2(int pos1, int pos2, char s, String password){
        if(pos1>pos2 || (pos1-1)>password.length()){
            return false;
        }
        if(password.charAt(pos1-1)==s && password.charAt(pos2-1)!=s){
            return true;
        }else return password.charAt(pos1 - 1) != s && password.charAt(pos2 - 1) == s;
    }

    /**
     * Function collect data from file
     * @return List of Integer data
     */
    private List<String> getInput() {
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

        return Stream.of(arr.split("\r\n")).collect(Collectors.toList());
    }
}

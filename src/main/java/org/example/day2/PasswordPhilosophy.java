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
    List<String> passwords;

    public PasswordPhilosophy() {
        passwords = getInput();
    }

    public int countValidPassword() {
        int result = 0;
        for (String password : passwords) {
            Pattern pattern = Pattern.compile("(\\d+)-(\\d+) ([a-z]): (\\w+)");
            Matcher matcher = pattern.matcher(password);
            if (matcher.matches()) {
                int min = Integer.parseInt(matcher.group(1)),
                        max = Integer.parseInt(matcher.group(2));
                char s = matcher.group(3).charAt(0);
                String text = matcher.group(4);

                if (part2(min, max, s, text)) {
                    result++;
                }
            }
        }
        return result;
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
        }else if(password.charAt(pos1-1)!=s && password.charAt(pos2-1)==s){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Function collect data from file
     *
     * @return List of Integer data
     */
    private List<String> getInput() {
        String arr,
                path = "C:\\Users\\michal.musial\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\org\\example\\day2\\";
        String file = "input.txt";
        try {
            arr = new String(Files.readAllBytes(Paths.get(path + file)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return Stream.of(arr.split("\r\n")).collect(Collectors.toList());
    }

}

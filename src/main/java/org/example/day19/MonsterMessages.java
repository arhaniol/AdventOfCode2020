package org.example.day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MonsterMessages {
    private List<String> receivedMessage;
    private Map<Integer, String> rules;
    private List<StringBuilder> correctAnswers;

    public MonsterMessages(String file) {
        getInput(file);
    }

    private void getInput(String file) {
        String arr,
                mainPath = Paths.get("").toAbsolutePath().toString(),
                javaPath = "\\src\\main\\java",
                packPath = Paths.get(this.getClass().getPackage().getName()).toString().replace('.', '\\');

        try {
            arr = new String(Files.readAllBytes(Paths.get(mainPath, javaPath, packPath, file)));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        String[] input = arr.split("\r\n\r\n");

        rules = Stream.of(input[0].split("\r\n")).map(s -> s.split(": ")).collect(Collectors.toMap(s -> Integer.parseInt(s[0]), s -> s[1]));
        receivedMessage = Stream.of(input[1].split("\r\n")).collect(Collectors.toList());
    }

    public int build(){
        buildAnswersList();
        return correctAnswers.size();
    }

    public int getPart1() {
        buildAnswersList();
        return countCorrectMessages();
    }

    private int countCorrectMessages() {
        int result = 0;
        for (String message : receivedMessage) {
            if (correctAnswers.contains(message)) {
                result++;
            }
        }
        return result;
    }

    private void buildAnswersList() {
        correctAnswers = new ArrayList<>();
        StringBuilder answer = new StringBuilder();
        String rule0 = rules.get(0);
        correctAnswers.add(answer);
        getAnswer(rule0);
    }

    private void appendToCorrectAnswer(String answer) {
        for (int i = 0; i < correctAnswers.size(); i++) {
            correctAnswers.get(i).append(answer);
        }
    }

    private void getAnswer(String rule) {
        Pattern pattern = Pattern.compile("\"(.)\"");
        Matcher matcher = pattern.matcher(rule);
        if (matcher.matches()) {
            appendToCorrectAnswer(matcher.group(1));
            return;
        }
        if (rule.contains(" | ")) {
            String[] subRules = rule.split(" \\| ");
            getAnswer(subRules[0]);
            for (int i = 1; i < subRules.length; i++) {
                correctAnswers.add(correctAnswers.get(0));
                getAnswer(subRules[i]);
            }
            return;
        }
        if (rule.contains(" ")) {
            int[] subRules = Arrays.stream(rule.split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int subRule : subRules) {
                getAnswer(rules.get(subRule));
            }
//        } else {
//            getAnswer(rules.get(Integer.parseInt(rule)));
        }
    }

    public int getPart2() {
        return 0;
    }
}

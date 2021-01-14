package org.example.day18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class OperationOrder {
    List<String> list;

    public OperationOrder(String file) {
        list = getInput(file);
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

    private long evaluateParentheses(String expression) {
        StringBuilder exp = new StringBuilder(expression);
        if (expression.contains("(")) {
            int lastOpen = exp.lastIndexOf("(");
            while (lastOpen > -1) {
                int nextClose = exp.indexOf(")", lastOpen);
                long evaluation = evaluateParentheses(exp.substring(lastOpen + 1, nextClose));
                exp.replace(lastOpen, nextClose + 1, String.valueOf(evaluation));
                lastOpen = exp.lastIndexOf("(");
            }
        }
        return evaluateExpression(exp);
    }

    private long evaluateExpression(StringBuilder exp) {
        long[] arr = Arrays.stream(exp.toString().split(" [\\+\\*]+ ")).mapToLong(Long::parseLong).toArray();
        char[] operation = Arrays.stream(exp.toString().split("\\d+")).map(String::trim).collect(joining("")).toCharArray();

        long result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (operation[i - 1] == '+') {
                result += arr[i];
            } else {
                result *= arr[i];
            }
        }
        return result;
    }

    private long evaluateParentheses2(String expression) {
        StringBuilder exp = new StringBuilder(expression);
        if (expression.contains("(")) {
            int lastOpen = exp.lastIndexOf("(");
            while (lastOpen > -1) {
                int nextClose = exp.indexOf(")", lastOpen);
                long evaluation = evaluateParentheses2(exp.substring(lastOpen + 1, nextClose));
                exp.replace(lastOpen, nextClose + 1, String.valueOf(evaluation));
                lastOpen = exp.lastIndexOf("(");
            }
        }
        return evaluateExpression(evaluateAdd(exp));
    }

    private StringBuilder evaluateAdd(StringBuilder exp) {
        long[] arr = Arrays.stream(exp.toString().split(" [\\+\\*]+ ")).mapToLong(Long::parseLong).toArray();
        char[] operation = Arrays.stream(exp.toString().split("\\d+")).map(String::trim).collect(joining("")).toCharArray();
        StringBuilder result = new StringBuilder();

        long temp = arr[0];
        for (int i = 0; i < operation.length; i++) {
            if (operation[i] == '+') {
                temp += arr[i + 1];
            } else {
                result.append(temp);
                result.append(' ');
                result.append(operation[i]);
                result.append(' ');
                temp = arr[i + 1];
            }
            if (i == operation.length - 1/* && operation[i] == '+'*/) {
                result.append(temp);
            }
        }
        return result;
    }

    public long getPart1() {
        long result = 0;
        for (String line : list) {
            result += evaluateParentheses(line);
        }
        return result;
    }

    public long getPart2() {
        long result = 0;
        for (String line : list) {
            result += evaluateParentheses2(line);
        }
        return result;
    }
}

package org.example;

import org.example.day1.ReportRepair;
import org.example.day2.PasswordPhilosophy;
import org.example.day3.TobogganTrajectory;
import org.example.day4.PassportProcessing;
import org.example.day5.BinaryBoarding;

public class App {
    public static void main(String[] args) {
        System.out.println("Day 1:");
        ReportRepair reportRepair = new ReportRepair();
        reportRepair.findMultiplicationOfSum();
        System.out.println("Part1: " + reportRepair.getPart1());
        System.out.println("Part2: " + reportRepair.getPart2());

        System.out.println("Day 2:");
        PasswordPhilosophy passwordPhilosophy = new PasswordPhilosophy();
        passwordPhilosophy.countValidPassword();

        System.out.println("* Part1: " + passwordPhilosophy.getPart1());
        System.out.println("* Part2: " + passwordPhilosophy.getPart2());

        System.out.println("Day 3:");
        TobogganTrajectory tobogganTrajectory = new TobogganTrajectory("input.txt");

        System.out.println("* Part1: " + tobogganTrajectory.getPart1());
        System.out.println("* Part2: " + tobogganTrajectory.getPart2());

        System.out.println("Day 4:");
        PassportProcessing passportProcessing = new PassportProcessing("input.txt");

        System.out.println("* Part1: " + passportProcessing.getPart1());
        System.out.println("* Part2: " + passportProcessing.getPart2());

        System.out.println("Day 5:");
        BinaryBoarding binaryBoarding = new BinaryBoarding("input.txt");

        System.out.println("* Part1: " + binaryBoarding.getPart1());
        System.out.println("* Part2: " + binaryBoarding.getPart2());
    }

}

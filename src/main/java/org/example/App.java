package org.example;

import org.example.day1.ReportRepair;
import org.example.day10.AdapterArray;
import org.example.day11.SeatingSystem;
import org.example.day12.RainRisk;
import org.example.day13.ShuttleSearch;
import org.example.day2.PasswordPhilosophy;
import org.example.day3.TobogganTrajectory;
import org.example.day4.PassportProcessing;
import org.example.day5.BinaryBoarding;
import org.example.day6.CustomCustoms;
import org.example.day7.HandyHaversacks;
import org.example.day8.HandheldHalting;
import org.example.day9.EncodingError;

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

        System.out.println("Day 6:");
        CustomCustoms customCustoms = new CustomCustoms("input.txt");

        System.out.println("* Part1: " + customCustoms.getPart1());
        System.out.println("* Part2: " + customCustoms.getPart2());

        System.out.println("Day 7:");
        HandyHaversacks handyHaversacks = new HandyHaversacks("input.txt");

        System.out.println("* Part1: " + handyHaversacks.getPart1());
        System.out.println("* Part2: " + handyHaversacks.getPart2());

        System.out.println("Day 8:");
        HandheldHalting handheldHalting = new HandheldHalting("input.txt");

        System.out.println("* Part1: " + handheldHalting.getPart1());
        System.out.println("* Part2: " + handheldHalting.getPart2());

        System.out.println("Day 9:");
        EncodingError encodingError = new EncodingError("input.txt");

        System.out.println("* Part1: " + encodingError.getPart1(25));
        System.out.println("* Part2: " + encodingError.getPart2(25));

        System.out.println("Day 10:");
        AdapterArray adapterArray = new AdapterArray("input.txt");

        System.out.println("* Part1: " + adapterArray.getPart1());
        System.out.println("* Part2: " + adapterArray.getPart2());

        System.out.println("Day 11:");
        SeatingSystem seatingSystem = new SeatingSystem("input.txt");

        System.out.println("* Part1: " + seatingSystem.getPart1());

        SeatingSystem seatingSystem2 = new SeatingSystem("input.txt");
        System.out.println("* Part2: " + seatingSystem2.getPart2());

        System.out.println("Day 12:");
        RainRisk rainRisk = new RainRisk("input.txt");

        System.out.println("* Part1: " + rainRisk.getPart1());
        System.out.println("* Part2: " + rainRisk.getPart2());

        System.out.println("Day 13:");
        ShuttleSearch shuttleSearch = new ShuttleSearch("input.txt");
//        shuttleSearch.setInitTimeStamp(100_000_000_000_000L);

        System.out.println("* Part1: " + shuttleSearch.getPart1());
        System.out.println("* Part2: " + shuttleSearch.getPart2());
    }

}

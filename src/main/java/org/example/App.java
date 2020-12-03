package org.example;

import org.example.day1.ReportRepair;
import org.example.day2.PasswordPhilosophy;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
//        ReportRepair reportRepair=new ReportRepair();
//        System.out.println(reportRepair.findMultiplicationOfSum());

        PasswordPhilosophy passwordPhilosophy = new PasswordPhilosophy();
        System.out.println(passwordPhilosophy.countValidPassword());
    }

}

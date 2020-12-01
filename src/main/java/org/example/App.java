package org.example;

import org.example.day1.ReportRepair;

public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ReportRepair reportRepair=new ReportRepair();
        System.out.println(reportRepair.findMultiplicationOfSum());
    }

}

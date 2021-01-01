package org.example.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShuttleSearch {

    private int timeStamp;
    private final List<Bus> busList;
    private long mod;
    private int lastI;

    public ShuttleSearch(String file) {
        busList = new ArrayList<>();
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

        String[] array = arr.split("\r\n");
        timeStamp = Integer.parseInt(array[0]);
        List<String> list = Stream.of(array[1].split(",")).collect(Collectors.toList());
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals("x")) {
                Bus bus = new Bus();
                bus.number = Integer.parseInt(list.get(i));
                bus.dif = i;
                bus.timeStamp = bus.number;
                busList.add(bus);
            }
        }
    }

    public int getPart1() {
        int min = timeStamp;
        int temp;
        int theBus = 0;
        for (Bus bus : busList) {
            temp = timeStamp % bus.number;
            if (temp == 0) {
                break;
            } else {
                temp = (bus.number * (timeStamp / bus.number + 1)) % timeStamp;
                if (temp < min) {
                    min = temp;
                    theBus = bus.number;
                }
            }
        }

        return min * theBus;
    }

    public long getPart2() {

        Bus first = busList.get(0);
        Bus last = busList.get(busList.size() - 1);
        long dif;
        do {
            dif = last.timeStamp - first.timeStamp;
            do {
                while (dif < last.dif) {
                    last.timeStamp += last.number;
                    dif = last.timeStamp - first.timeStamp;
                }
                while (dif > last.dif) {
                    first.timeStamp += first.number;
                    dif = last.timeStamp - first.timeStamp;
                }
            } while (dif != last.dif);
        } while (!isAllTimeStampOK());

        return busList.get(0).timeStamp;
    }

    private long a;
    private long b;
    private long c;
    private long d;

    private boolean isAllTimeStampOK() {
        Bus first = busList.get(0);
        Bus last = busList.get(busList.size() - 1);
        for (int i = 1; i < busList.size() - 1; i++) {
            Bus curr = busList.get(i);
            if ((first.timeStamp + curr.dif) % curr.number != 0) {
                if (a == 0) {
                    a = first.timeStamp;
                    first.timeStamp += first.number;
                    last.timeStamp += last.number;
                } else {
                    if (b != a) {
                        mod = first.timeStamp - a;
                        b = a;
                    }
                    first.timeStamp += mod;
                    last.timeStamp += mod;
                }
                return false;
            } else {
                curr.timeStamp = first.timeStamp + curr.dif;
                if (lastI < i) {
                    c = first.timeStamp;
                    lastI = i;
                    d = 0;
                } else {
                    if (lastI == i && d == 0) {
                        mod = first.timeStamp - c;
                        d = c;
                    }
                }
            }
        }
        return true;
    }

    class Bus {
        int number;
        long dif;
        long timeStamp;
    }
}

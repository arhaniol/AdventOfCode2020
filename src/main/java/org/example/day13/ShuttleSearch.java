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
    private long initTimeStamp;
    private long counter;

    public void setInitTimeStamp(long initTimeStamp) {
        this.initTimeStamp = initTimeStamp;
    }

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

        if (initTimeStamp > 100_000_000L) {
            for (Bus bus : busList) {
                bus.timeStamp = ((initTimeStamp / bus.number) + 1) * bus.number;
            }
        }

        computedTimeStamp(1);

        return busList.get(0).timeStamp;
    }

    private boolean computedTimeStamp(int pos) {
        if (pos < busList.size()) {
            Bus firstBus = busList.get(0);
            Bus currentBus = busList.get(pos);
            long dif = currentBus.timeStamp - firstBus.timeStamp;
            if (dif == currentBus.dif) {
                if (computedTimeStamp(pos + 1)) {
                    dif = currentBus.timeStamp - firstBus.timeStamp;
                } else {
                    return false;
                }
            }
            while (dif != currentBus.dif) {
                counter++;
                if ((counter % 1_000_000_000) == 0) {
                    System.out.println(firstBus.timeStamp);
                }
                while (dif < currentBus.dif) {
                    currentBus.timeStamp += currentBus.number;
                    dif = currentBus.timeStamp - firstBus.timeStamp;
                }
                while (dif > currentBus.dif) {
                    firstBus.timeStamp += firstBus.number;
                    dif = currentBus.timeStamp - firstBus.timeStamp;
                }
                if (dif == currentBus.dif) {
                    if (computedTimeStamp(pos + 1)) {
                        dif = currentBus.timeStamp - firstBus.timeStamp;
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    class Bus {
        int number;
        long dif;
        long timeStamp;
    }
}

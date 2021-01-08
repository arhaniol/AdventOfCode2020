package org.example.day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TicketTranslation {
    List<Field> fields;
    Ticket myTicket;
    List<Ticket> nearbyTicket;

    public TicketTranslation(String file) {
        fields = new ArrayList<>();
        myTicket = new Ticket();
        nearbyTicket = new ArrayList<>();
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
        // fields
        String[] fields = input[0].split("\r\n");
        int semicolon;
        for (String s : fields) {
            Field field = new Field();
            semicolon = s.indexOf(':');
            field.setName(s.substring(0, semicolon));
            field.setRange(getRange(s.substring(semicolon + 2)));
            this.fields.add(field);
        }
        // your ticket
        String[] temp1 = input[1].split("\r\n");
        myTicket.setFields(Stream.of(temp1[1].split(",")).map(s -> new Field(null, null, Integer.parseInt(s))).collect(Collectors.toList()));

        // nearby tickets
        String[] temp = input[2].split("\r\n");
        for (int i = 1; i < temp.length; i++) {
            Ticket ticket = new Ticket();
            ticket.setFields(Stream.of(temp[i].split(",")).map(s -> new Field(null, null, Integer.parseInt(s))).collect(Collectors.toList()));
            nearbyTicket.add(ticket);
        }
    }

    private int[] getRange(String text) {
        int[] range = new int[4];
        Pattern pattern = Pattern.compile("(\\d+)-(\\d+) or (\\d+)-(\\d+)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            for (int i = 0; i < matcher.groupCount(); i++) {
                range[i] = Integer.parseInt(matcher.group(i + 1));
            }
            return range;
        }
        return null;
    }

    public long getPart1() {
        long result = 0;
        Set<Ticket> toRemove = new HashSet<>();
        boolean outOfRange;
        for (Ticket t : nearbyTicket) {
            for (Field tf : t.getTicketField()) {
                outOfRange = true;
                for (Field f : fields) {
                    if (f.isInRange(tf.getValue())) {
                        outOfRange = false;
                        break;
                    }
                }
                if (outOfRange) {
                    result += tf.getValue();
                    toRemove.add(t);
                    break;
                }
            }
        }
        nearbyTicket.removeAll(toRemove);
        return result;
    }

    public long getPart2() {
        Map<Integer, List<String>> map = new HashMap<>();
        boolean inField;
        int allFields = fields.size();
        for (int i = 0; i < allFields; i++) {
            List<String> couldBe = new ArrayList<>();
            for (Field f : fields) {
                inField = true;
                for (Ticket t : nearbyTicket) {
                    List<Field> ticketFields = t.getTicketField();
                    Field currentField = ticketFields.get(i);
                    if (!f.isInRange(currentField.getValue())) {
                        inField = false;
                        break;
                    }
                }
                if (!f.isInRange(myTicket.getTicketField().get(i).getValue())) {
                    inField = false;
                }
                if (inField) {
                    for (Ticket t : nearbyTicket) {
                        t.ticketField.get(i).setName(f.getName());
                    }
                    couldBe.add(f.getName());
                }
            }
            map.put(i, couldBe);
        }
        String oneName = "";
        for (Integer key : map.keySet()) {
            if (map.get(key).size() == 1) {
                oneName = map.get(key).get(0);
            }
        }
        String temp = "";
        for (Integer ignored : map.keySet()) {
            for (Integer key2 : map.keySet()) {
                if (map.get(key2).size() > 1) {
                    if (map.get(key2).size() == 2) {
                        map.get(key2).remove(oneName);
                        temp = map.get(key2).get(0);
                    } else {
                        map.get(key2).remove(oneName);
                    }
                }
            }
            oneName = temp;
        }
        long result = 1;
        for (Integer key : map.keySet()) {
            myTicket.getTicketField().get(key).setName(map.get(key).get(0));
            if (map.get(key).get(0).contains("departure")) {
                result *= myTicket.getTicketField().get(key).getValue();
            }
        }
        return result;
    }

    private static class Ticket {
        private List<Field> ticketField;

        public List<Field> getTicketField() {
            return ticketField;
        }

        public void setFields(List<Field> fields) {
            this.ticketField = fields;
        }
    }

    private static class Field {
        private String name;
        private int[] range;
        private int value;

        public Field() {

        }

        public Field(String name, int[] range, int value) {
            this.name = name;
            this.range = range;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setRange(int[] range) {
            this.range = range;
        }

        public int getValue() {
            return value;
        }

        public boolean isInRange(int val) {
            return (range[0] <= val && val <= range[1]) || (range[2] <= val && val <= range[3]);
        }
    }
}

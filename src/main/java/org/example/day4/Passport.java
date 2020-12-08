package org.example.day4;

import static org.example.day4.LengthUnit.CENTIMETER;
import static org.example.day4.LengthUnit.INCH;

class Passport {
    int byr;
    int iyr;
    int eyr;
    int hgt;
    LengthUnit unit;
    String hcl;
    EyeColor ecl;
    String pid;

    private boolean allFields;
    private boolean valid;

    public Passport(String data) {
        valid = false;
        allFields = true;

        String value = extractData(data, "byr:");
        if (value != null) {
            byr = Integer.parseInt(value);
        } else {
            byr = -1;
            allFields = false;
        }
        value = extractData(data, "iyr:");
        if (value != null) {
            iyr = Integer.parseInt(value);
        } else {
            iyr = -1;
            allFields = false;
        }
        value = extractData(data, "eyr:");
        if (value != null) {
            eyr = Integer.parseInt(value);
        } else {
            eyr = -1;
            allFields = false;
        }
        value = extractData(data, "hgt:");
        if (value != null) {
            int pos;
            if (value.contains("cm")) {
                pos = value.indexOf("cm");
                hgt = Integer.parseInt(value.substring(0, pos));
                unit = CENTIMETER;
            } else if (value.contains("in")) {
                pos = value.indexOf("in");
                hgt = Integer.parseInt(value.substring(0, pos));
                unit = INCH;
            } else {
                hgt = -1;
                unit = LengthUnit.OTHER;
            }
        } else {
            hgt = -1;
            unit = LengthUnit.OTHER;
            allFields = false;
        }
        value = extractData(data, "hcl:");
        if (value != null) {
            hcl = value;
        } else {
            hcl = null;
            allFields = false;
        }

        value = extractData(data, "ecl:");
        if (value != null) {
            try {
                ecl = EyeColor.valueOf(value);
            } catch (Exception ex) {
                ecl = EyeColor.OTHER;
            }
        } else {
            ecl = EyeColor.OTHER;
            allFields = false;
        }

        value = extractData(data, "pid:");
        if (value != null) {
            pid = value;
        } else {
            pid = null;
            allFields = false;
        }

        checkValid();

    }

    private void checkValid() {
        valid = byr >= -1 &&
                iyr >= -1 &&
                eyr >= -1 &&
                hgt >= -1 &&
                unit != LengthUnit.OTHER &&
                hcl != null &&
                ecl != EyeColor.OTHER &&
                pid != null;
    }

    public boolean isAllFields() {
        return allFields;
    }

    public boolean isValid() {
        return valid;
    }

    String extractData(String data, String pattern) {
        String result = null;
        if (data == null || data.length() == 0) {
            return result;
        }
        if (data.contains(pattern)) {
            int start = data.indexOf(pattern);
            if (start >= 0) {
                int endEnter = data.indexOf("\r\n", start);
                int endSpace = data.indexOf(" ", start);
                int end;
                if (endEnter >= 0 && endSpace >= 0) {
                    end = Math.min(endEnter, endSpace);
                } else if (endEnter < 0 && endSpace < 0) {
                    end = data.length();
                } else {
                    end = Math.max(endEnter, endSpace);
                }
                if (end > start) {
                    result = data.substring(start + pattern.length(), end);
                }
            }
        }
        return result;
    }
}

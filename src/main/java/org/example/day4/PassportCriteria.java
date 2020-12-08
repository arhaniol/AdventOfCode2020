package org.example.day4;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.example.day4.EyeColor.*;
import static org.example.day4.LengthUnit.*;

public class PassportCriteria {
    int byrMin;
    int byrMax;
    int iyrMin;
    int iyrMax;
    int eyrMin;
    int eyrMax;
    int hgtCmMin;
    int hgtCmMax;
    int hgtInMin;
    int hgtInMax;
    Pattern hclCriteria;
    List<EyeColor> eclAllowColors;
    Pattern pidCriteria;

    public PassportCriteria() {
        byrMin = 1920;
        byrMax = 2002;
        iyrMin = 2010;
        iyrMax = 2020;
        eyrMin = 2020;
        eyrMax = 2030;
        hgtCmMin = 150;
        hgtCmMax = 193;
        hgtInMin = 59;
        hgtInMax = 76;
        hclCriteria = Pattern.compile("#[0-9a-f]{6}");
        eclAllowColors = Arrays.asList(amb, blu, brn, gry, grn, hzl, oth);
        pidCriteria = Pattern.compile("\\d{9}");
    }

    boolean checkPassport(Passport passport) {
        if (!passport.isValid()) {
            return false;
        }
        if (passport.byr < byrMin || passport.byr > byrMax ||
                passport.iyr < iyrMin || passport.iyr > iyrMax ||
                passport.eyr < eyrMin || passport.eyr > eyrMax) {
            return false;
        }
        if (passport.unit == CENTIMETER) {
            if (passport.hgt < hgtCmMin || passport.hgt > hgtCmMax) {
                return false;
            }
        } else if (passport.unit == INCH) {
            if (passport.hgt < hgtInMin || passport.hgt > hgtInMax) {
                return false;
            }
        }
        if (!hclCriteria.matcher(passport.hcl).matches()) {
            return false;
        }
        if (!eclAllowColors.contains(passport.ecl)) {
            return false;
        }
        if (!pidCriteria.matcher(passport.pid).matches()) {
            return false;
        }
        return true;
    }
}


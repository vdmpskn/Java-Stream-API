package com.foxminded.vdmpskn.javastreamsapi;

import com.foxminded.vdmpskn.javastreamsapi.domain.AbbreviationAndDatePair;
import com.foxminded.vdmpskn.javastreamsapi.domain.RacerInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RaceDataParser {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
    Pattern pattern = Pattern.compile("([A-Za-z]{3})(\\d{4}-\\d{2}-\\d{2}_\\d{2}:\\d{2}:\\d{2}\\.\\d{3})");

    public List<AbbreviationAndDatePair> parseLogFile(String logFilePath) {
        List<AbbreviationAndDatePair> pairs = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(logFilePath));
            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String abbreviation = matcher.group(1);
                    String dateString = matcher.group(2);
                    LocalDateTime date = LocalDateTime.parse(dateString, formatter);
                    pairs.add(new AbbreviationAndDatePair(abbreviation, date));
                } else {
                    System.out.println("No match found for line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pairs;
    }

    public List<RacerInfo> parseAbbreviationFile(String filePath) {
        List<RacerInfo> racerInfos = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                String[] parts = line.split("_");
                if (parts.length >= 3) {
                    String abbreviation = parts[0].trim();
                    String fullName = parts[1].trim();
                    String team = parts[2].trim();

                    racerInfos.add(new RacerInfo(abbreviation, fullName, team));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return racerInfos;
    }

}


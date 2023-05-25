package com.foxminded.vdmpskn.javastreamsapi;

import com.foxminded.vdmpskn.javastreamsapi.domain.AbbreviationAndDatePair;
import com.foxminded.vdmpskn.javastreamsapi.domain.RacerInfo;

import java.time.LocalTime;
import java.util.*;

import static com.foxminded.vdmpskn.javastreamsapi.RaceAnalyzer.computeLapTime;

public class Main {

    public static void main(String[] args) {
        RaceDataParser parser = new RaceDataParser();
        List<AbbreviationAndDatePair> startAbbreviationsAndDatePair = parser.parseLogFile("src/main/resources/start.log");
        List<AbbreviationAndDatePair> endAbbreviationsAndDatePair = parser.parseLogFile("src/main/resources/end.log");
        List<RacerInfo> racerInfos = parser.parseAbbreviationFile("src/main/resources/abbreviations.txt");

        Map<String, LocalTime> lapTimeMap = computeLapTime(startAbbreviationsAndDatePair, endAbbreviationsAndDatePair);
        Printer printer = new Printer();
        printer.printReport(racerInfos, lapTimeMap);

    }
}


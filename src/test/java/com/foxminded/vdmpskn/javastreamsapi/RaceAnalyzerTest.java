package com.foxminded.vdmpskn.javastreamsapi;

import com.foxminded.vdmpskn.javastreamsapi.domain.AbbreviationAndDatePair;
import com.foxminded.vdmpskn.javastreamsapi.domain.RacerInfo;
import com.foxminded.vdmpskn.javastreamsapi.domain.RacerResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.*;

public class RaceAnalyzerTest {

    @Test
    public void testComputeLapTime() {
        RaceAnalyzer raceAnalyzer = new RaceAnalyzer();

        List<AbbreviationAndDatePair> startPairs = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        startPairs.add(new AbbreviationAndDatePair("DRR", LocalDateTime.parse("2023-05-23_10:00:00.000", formatter)));

        startPairs.add(new AbbreviationAndDatePair("SVF", LocalDateTime.parse("2023-05-23_10:02:00.000", formatter)));

        List<AbbreviationAndDatePair> endPairs = new ArrayList<>();
        endPairs.add(new AbbreviationAndDatePair("DRR", LocalDateTime.parse("2023-05-23_10:01:30.000", formatter)));
        endPairs.add(new AbbreviationAndDatePair("SVF", LocalDateTime.parse("2023-05-23_10:03:30.000", formatter)));

        Map<String, LocalTime> lapTimeMap = raceAnalyzer.computeLapTime(startPairs, endPairs);

        Assertions.assertEquals(2, lapTimeMap.size());
        Assertions.assertTrue(lapTimeMap.containsKey("DRR"));
        Assertions.assertTrue(lapTimeMap.containsKey("SVF"));

    }

    @Test
    public void testEnrichRacerResult() {
        RaceAnalyzer raceAnalyzer = new RaceAnalyzer();

        List<RacerInfo> racerInfos = new ArrayList<>();
        racerInfos.add(new RacerInfo("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER"));
        racerInfos.add(new RacerInfo("SVF", "Sebastian Vettel", "FERRARI"));

        Map<String, LocalTime> lapTimeMap = new HashMap<>();
        lapTimeMap.put("DRR", LocalTime.parse("00:01:30.000"));
        lapTimeMap.put("SVF", LocalTime.parse("00:03:30.000"));

        List<RacerResult> racerResults = raceAnalyzer.enrichRacerResult(racerInfos, lapTimeMap);

        Assertions.assertEquals(2, racerResults.size());

        RacerResult racerResult1 = racerResults.get(0);
        Assertions.assertEquals("Daniel Ricciardo", racerResult1.getFullName());
        Assertions.assertEquals("RED BULL RACING TAG HEUER", racerResult1.getTeam());
        Assertions.assertEquals(LocalTime.parse("00:01:30.000"), racerResult1.getLapTime());

        RacerResult racerResult2 = racerResults.get(1);
        Assertions.assertEquals("Sebastian Vettel", racerResult2.getFullName());
        Assertions.assertEquals("FERRARI", racerResult2.getTeam());
        Assertions.assertEquals(LocalTime.parse("00:03:30.000"), racerResult2.getLapTime());
    }

}

package com.foxminded.vdmpskn.javastreamsapi;

import com.foxminded.vdmpskn.javastreamsapi.domain.AbbreviationAndDatePair;
import com.foxminded.vdmpskn.javastreamsapi.domain.RacerInfo;
import com.foxminded.vdmpskn.javastreamsapi.domain.RacerResult;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class RaceAnalyzer {

        public static Map<String, LocalTime> computeLapTime(List<AbbreviationAndDatePair> startAbbreviationsAndDatePair, List<AbbreviationAndDatePair> endAbbreviationsAndDatePair) {
            Map<String, LocalTime> lapTimes = new HashMap<>();

            Map<String, LocalTime> startTimes = startAbbreviationsAndDatePair.stream()
                    .collect(Collectors.toMap(
                            AbbreviationAndDatePair::getAbbreviation,
                            startPair -> startPair.getTime().toLocalTime()
                    ));

            for (AbbreviationAndDatePair endPair : endAbbreviationsAndDatePair) {
                String abbreviation = endPair.getAbbreviation();
                LocalTime endTime = endPair.getTime().toLocalTime();
                LocalTime startTime = startTimes.get(abbreviation);

                if (startTime != null) {
                    Duration lapDuration = Duration.between(startTime, endTime);
                    LocalTime lapTime = LocalTime.MIDNIGHT.plus(lapDuration);

                    lapTimes.put(abbreviation, lapTime);
                }
            }

            return lapTimes;
        }

    public List<RacerResult> enrichRacerResult(List<RacerInfo> racerInfos, Map<String, LocalTime> lapTimeMap) {
        List<RacerResult> racerResults = new ArrayList<>();

        for (RacerInfo racerInfo : racerInfos) {
            String abbreviation = racerInfo.getAbbreviation();
            String fullName = racerInfo.getFullName();
            String team = racerInfo.getTeam();
            LocalTime lapTime = lapTimeMap.get(abbreviation);

            RacerResult racerResult = new RacerResult(fullName, team, lapTime);
            racerResults.add(racerResult);
        }

        return racerResults;
    }

}




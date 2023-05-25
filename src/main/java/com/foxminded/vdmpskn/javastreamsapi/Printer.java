package com.foxminded.vdmpskn.javastreamsapi;

import com.foxminded.vdmpskn.javastreamsapi.domain.RacerInfo;
import com.foxminded.vdmpskn.javastreamsapi.domain.RacerResult;

import java.time.LocalTime;
import java.util.*;

public class Printer {
    RaceAnalyzer rr = new RaceAnalyzer();

    public void printReport(List<RacerInfo> racerInfos, Map<String, LocalTime> lapTimeMap) {
        List<RacerResult> racerResults = rr.enrichRacerResult(racerInfos, lapTimeMap);
        racerResults.sort(Comparator.comparing(RacerResult::getLapTime));

        for (int i = 0; i < racerResults.size(); i++) {
            RacerResult racerResult = racerResults.get(i);
            String position = (i + 1) + ".";
            String name = racerResult.getFullName();
            String team = racerResult.getTeam();
            LocalTime lapTime = racerResult.getLapTime();
            String formattedLapTime = String.format("%02d:%02d.%03d",
                    lapTime.getMinute(), lapTime.getSecond(), lapTime.getNano() / 1_000_000);

            System.out.printf("%-3s %-20s | %-35s | %s\n", position, name, team, formattedLapTime);

            if (i == 14) {
                System.out.println("---------------------------------------");
            }
        }
    }

}

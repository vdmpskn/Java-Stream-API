package com.foxminded.vdmpskn.javastreamsapi.domain;

import java.time.LocalTime;

public class RacerResult {
    String fullName;
    String team;
    LocalTime lapTime;

    public RacerResult(String fullName, String team, LocalTime lapTime) {
        this.fullName = fullName;
        this.team = team;
        this.lapTime = lapTime;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTeam() {
        return team;
    }

    public LocalTime getLapTime() {
        return lapTime;
    }
}

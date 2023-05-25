package com.foxminded.vdmpskn.javastreamsapi.domain;

public class RacerInfo {
    String abbreviation;
    String fullName;
    String team;

    public RacerInfo(String abbreviation, String fullName, String team) {
        this.abbreviation = abbreviation;
        this.fullName = fullName;
        this.team = team;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTeam() {
        return team;
    }
}

package com.foxminded.vdmpskn.javastreamsapi.domain;

import java.time.LocalDateTime;

public class AbbreviationAndDatePair {
    String abbreviation;
    LocalDateTime time;

    public AbbreviationAndDatePair(String abbreviation, LocalDateTime time) {
        this.abbreviation = abbreviation;
        this.time = time;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public LocalDateTime getTime() {
        return time;
    }
}



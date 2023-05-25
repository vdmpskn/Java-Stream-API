package com.foxminded.vdmpskn.javastreamsapi;

import com.foxminded.vdmpskn.javastreamsapi.domain.AbbreviationAndDatePair;
import com.foxminded.vdmpskn.javastreamsapi.domain.RacerInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class RaceDataParserTest {

    @Test
    public void testParseLogFile() {
        RaceDataParser parser = new RaceDataParser();
        String logFilePath = "src/main/resources/start.log";

        List<AbbreviationAndDatePair> pairs = parser.parseLogFile(logFilePath);

        Assertions.assertEquals(19, pairs.size());

        AbbreviationAndDatePair pair1 = pairs.get(0);
        Assertions.assertEquals("SVF", pair1.getAbbreviation());
        Assertions.assertEquals(LocalDateTime.parse("2018-05-24T12:02:58.917"), pair1.getTime());

        AbbreviationAndDatePair pair2 = pairs.get(1);
        Assertions.assertEquals("NHR", pair2.getAbbreviation());
        Assertions.assertEquals(LocalDateTime.parse("2018-05-24T12:02:49.914"), pair2.getTime());

    }

    @Test
    public void testParseAbbreviationFile() {
        RaceDataParser parser = new RaceDataParser();
        String filePath = "src/main/resources/abbreviations.txt";

        List<RacerInfo> racerInfos = parser.parseAbbreviationFile(filePath);

        Assertions.assertEquals(19, racerInfos.size());


        RacerInfo racer1 = racerInfos.get(0);
        Assertions.assertEquals("DRR", racer1.getAbbreviation());
        Assertions.assertEquals("Daniel Ricciardo", racer1.getFullName());
        Assertions.assertEquals("RED BULL RACING TAG HEUER", racer1.getTeam());

        RacerInfo racer2 = racerInfos.get(1);
        Assertions.assertEquals("SVF", racer2.getAbbreviation());
        Assertions.assertEquals("Sebastian Vettel", racer2.getFullName());
        Assertions.assertEquals("FERRARI", racer2.getTeam());

    }
}


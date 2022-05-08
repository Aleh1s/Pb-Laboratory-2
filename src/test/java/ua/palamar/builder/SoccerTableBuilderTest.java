package ua.palamar.builder;

import junit.framework.TestCase;
import org.junit.Test;
import ua.palamar.counter.ScoreCounter;
import ua.palamar.counter.SoccerScoreCounter;
import ua.palamar.data.DataValidator;
import ua.palamar.data.SoccerDataValidator;
import ua.palamar.parser.DataParser;
import ua.palamar.parser.SoccerDataParser;

public class SoccerTableBuilderTest extends TestCase {

    private final DataValidator dataValidator = new SoccerDataValidator();
    private final DataParser dataParser = new SoccerDataParser(dataValidator);
    private final ScoreCounter scoreCounter = new SoccerScoreCounter(dataValidator);
    private final SoccerTableBuilder soccerTableBuilder = new SoccerTableBuilder(dataParser, scoreCounter);

    @Test
    public void testBuildResultTable() {
        // given
        String[] given = new String[]{
                "AFC Bournemouth,4:4,1:1,0:0,3:2,4:2,3:0,0:0,2:1,4:2,4:3",
                "Arsenal,3:1,2:3,2:3,4:3,0:1,1:2,1:0,1:4,0:2,2:4",
                "Burnley,3:1,4:0,3:1,3:2,2:2,0:1,3:4,3:3,2:3,1:1",
                "Chelsea,3:0,3:0,3:0,2:2,0:4,3:0,0:4,3:4,2:2,4:1",
                "Crystal Palace,0:0,1:2,4:1,3:4,2:2,2:1,3:3,2:1,4:3,4:0",
                "Everton,4:1,3:0,0:0,3:0,4:3,1:4,4:0,3:3,4:0,4:1",
                "Hull City,2:3,4:1,2:4,4:3,3:2,2:2,1:3,4:0,0:3,0:1",
                "Leicester City,0:1,1:1,2:3,1:2,1:0,4:2,3:2,3:2,4:3,3:3",
                "Liverpool,0:0,4:0,1:1,1:2,1:1,4:4,0:4,2:2,4:2,3:12"
        };
        String expected =
                "AFC Bournemouth,22\r\n" +
                        "Arsenal,9\r\n" +
                        "Burnley,15\r\n" +
                        "Chelsea,17\r\n" +
                        "Crystal Palace,18\r\n" +
                        "Everton,23\r\n" +
                        "Hull City,13\r\n" +
                        "Leicester City,17\r\n" +
                        "Liverpool,11\r\n";

        // when
        String actual = soccerTableBuilder.buildResultTable(given);

        // then
        assertEquals(expected, actual);

    }

    @Test
    public void testPush() {
        // given
        StringBuilder givenSB = new StringBuilder();
        String givenStr = "AFC Bournemouth,4:4,1:1,0:0,3:2,4:2,3:0,0:0,2:1,4:2,4:3";
        String expected = "AFC Bournemouth,22\r\n";

        // when
        soccerTableBuilder.push(givenStr, givenSB);
        String actual = givenSB.toString();

        // then
        assertEquals(expected, actual);
    }
}
package ua.palamar.parser;

import junit.framework.TestCase;
import org.junit.Test;
import ua.palamar.data.DataValidator;
import ua.palamar.data.SoccerDataValidator;

public class SoccerDataParserTest extends TestCase {

    private final DataValidator dataValidator = new SoccerDataValidator();
    private final SoccerDataParser soccerDataParser = new SoccerDataParser(dataValidator);

    @Test
    public void testParseName() {
        // given
        String given = "Real Madrid,1:2";
        String expected = "Real Madrid";

        // when
        String actual = soccerDataParser.parseName(given);

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void testParseMatchOutcomesLine() {
        // given
        String given = "Real Madrid,1:1,2:5,1:5";
        String expected = "1:1,2:5,1:5";

        // when
        String actual = soccerDataParser.parseMatchOutcomesLine(given);

        // then
        assertEquals(expected, actual);
    }
}
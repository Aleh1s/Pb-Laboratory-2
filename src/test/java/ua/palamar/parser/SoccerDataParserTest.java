package ua.palamar.parser;

import junit.framework.TestCase;
import org.junit.Test;

public class SoccerDataParserTest extends TestCase {

    private final SoccerDataParser soccerDataParser = new SoccerDataParser();

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
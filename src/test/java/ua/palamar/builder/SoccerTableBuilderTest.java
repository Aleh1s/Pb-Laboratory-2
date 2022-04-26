package ua.palamar.builder;

import junit.framework.TestCase;
import org.junit.Test;
import ua.palamar.counter.ScoreCounter;
import ua.palamar.counter.SoccerScoreCounter;
import ua.palamar.parser.DataParser;
import ua.palamar.parser.SoccerDataParser;

public class SoccerTableBuilderTest extends TestCase {

    private final DataParser dataParser = new SoccerDataParser();
    private final ScoreCounter scoreCounter = new SoccerScoreCounter();
    private final SoccerTableBuilder soccerTableBuilder = new SoccerTableBuilder(dataParser, scoreCounter);

    @Test
    public void testBuildResultTable() {
        // given
        String[] given = new String[]{
                "Real,1:2,1:1",
                "Barcelona,1:1,3:1"
        };
        String expected = "Real,1\r\nBarcelona,4\r\n";

        // when
        String actual = soccerTableBuilder.buildResultTable(given);

        // then
        assertEquals(expected, actual);

    }

    @Test
    public void testPush() {
    }
}
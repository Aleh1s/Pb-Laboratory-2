package ua.palamar.counter;

import junit.framework.TestCase;
import org.junit.Test;
import ua.palamar.data.DataValidator;
import ua.palamar.data.SoccerDataValidator;
import ua.palamar.parser.DataParser;
import ua.palamar.parser.SoccerDataParser;

import static org.junit.Assert.assertArrayEquals;

public class SoccerScoreCounterTest extends TestCase {

    private final DataValidator dataValidator = new SoccerDataValidator();
    private final DataParser dataParser = new SoccerDataParser(dataValidator);
    private final SoccerScoreCounter soccerScoreCounter = new SoccerScoreCounter(dataValidator);

    @Test
    public void testCountTotalScore() {
        // given
        DataParser dataParser = new SoccerDataParser(dataValidator);
        String given = "AFC Bournemouth,4:4,1:1,0:0,3:2,4:2,3:0,0:0,2:1,4:2,4:3";
        int expected = 22;

        // when
        int actual = soccerScoreCounter.countTotalScore(given, dataParser);

        // then
        assertEquals(expected, actual);
    }

    public void testGetMatchOutcomes() {
        // given
        String given = "1:1,1:2,1:9";
        String[] expected = new String[]{"1:1", "1:2", "1:9"};

        // when
        String[] actual = soccerScoreCounter.getMatchOutcomes(given);

        // then
        assertArrayEquals(expected, actual);
    }

    public void testDetermineScore() {
        // given
        String[] win = new String[]{"3", "1"};
        String[] loose = new String[]{"1", "3"};
        String[] draw = new String[]{"1", "1"};

        int expectedWin = 3;
        int expectedLoose = 0;
        int expectedDraw = 1;

        // when
        int actualWin = soccerScoreCounter.determineScore(win, dataParser);
        int actualLoose = soccerScoreCounter.determineScore(loose, dataParser);
        int actualDraw = soccerScoreCounter.determineScore(draw, dataParser);

        // then
        assertEquals(expectedWin, actualWin);
        assertEquals(expectedLoose, actualLoose);
        assertEquals(expectedDraw, actualDraw);
    }
}
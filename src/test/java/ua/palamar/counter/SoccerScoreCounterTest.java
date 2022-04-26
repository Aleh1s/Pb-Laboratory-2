package ua.palamar.counter;

import junit.framework.TestCase;
import org.junit.Test;
import ua.palamar.parser.DataParser;
import ua.palamar.parser.SoccerDataParser;

import static org.junit.Assert.assertArrayEquals;

public class SoccerScoreCounterTest extends TestCase {

    private final SoccerScoreCounter soccerScoreCounter = new SoccerScoreCounter();

    @Test
    public void testCountTotalScore() {
        // given
        DataParser dataParser = new SoccerDataParser();
        String given = "Real Madrid,1:1,2:2,4:3";
        int expected = 5;

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
        int actualWin = soccerScoreCounter.determineScore(win);
        int actualLoose = soccerScoreCounter.determineScore(loose);
        int actualDraw = soccerScoreCounter.determineScore(draw);

        // then
        assertEquals(expectedWin, actualWin);
        assertEquals(expectedLoose, actualLoose);
        assertEquals(expectedDraw, actualDraw);
    }
}
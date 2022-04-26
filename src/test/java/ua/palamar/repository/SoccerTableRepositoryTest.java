package ua.palamar.repository;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertArrayEquals;

public class SoccerTableRepositoryTest extends TestCase {

    private final SoccerTableRepository soccerTableRepository = new SoccerTableRepository();

    @Test
    public void testGetTeamsFromTable() {
        // given
        File given = new File("src/test/resources/input.csv");
        String[] expected = new String[]{
                "Chelsea FC,3:0,0:0,1:0,0:2,4:1,3:1,0:3,1:5,0:2,4:1",
                "Barcelona,3:0,0:0,1:0,0:2,4:1,3:1,0:3,1:5,0:2,4:1"
        };

        // when
        String[] actual = soccerTableRepository.getTeamsFromTable(given);

        // then
        assertArrayEquals(expected, actual);
    }

    public void testSaveTable() {
        // given
        File dir = new File("src/test/resources");
        String given = "2\r\nReal Madrid,5\r\nBarcelona,7";
        String[] expected = new String[]{"Real Madrid,5", "Barcelona,7"};

        // when
        soccerTableRepository.saveTable(dir, given);
        String[] actual = soccerTableRepository.getTeamsFromTable(
                new File(dir.getPath() + "/result.csv")
        );

        // then
        assertArrayEquals(expected, actual);
    }

    public void testCountTeams() {
        // given
        String given = "11";
        int expected = 11;

        // when
        int actual = soccerTableRepository.countTeams(given);

        // then
        assertEquals(expected, actual);
    }

    public void testCreateResultFile() {
        // given
        File dir = new File("src/test/resources");
        File given = new File(dir, "result.csv");

        if (given.exists()) {
            given.delete();
        }

        // when
        soccerTableRepository.createResultFile(dir);
        boolean actual = given.exists();

        // then
        assertTrue(actual);
    }
}
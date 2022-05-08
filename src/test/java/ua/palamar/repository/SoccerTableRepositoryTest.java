package ua.palamar.repository;

import junit.framework.TestCase;
import org.junit.Test;
import ua.palamar.data.DataValidator;
import ua.palamar.data.SoccerDataValidator;
import ua.palamar.parser.DataParser;
import ua.palamar.parser.SoccerDataParser;

import java.io.File;

import static org.junit.Assert.assertArrayEquals;

public class SoccerTableRepositoryTest extends TestCase {

    private final DataValidator dataValidator = new SoccerDataValidator();
    private final SoccerTableRepository soccerTableRepository = new SoccerTableRepository();

    private final DataParser dataParser = new SoccerDataParser(dataValidator);
    @Test
    public void testGetTeamsFromTable() {
        // given
        File given = new File("src/test/resources/input.csv");
        String[] expected = new String[]{
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

        // when
        String[] actual = soccerTableRepository.getTeamsFromTable(given, dataParser);

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
                new File(dir.getPath() + "/result.csv"),
                dataParser
        );

        // then
        assertArrayEquals(expected, actual);
    }

    public void testCountTeams() {
        // given
        String given = "11";
        int expected = 11;

        // when
        int actual = soccerTableRepository.countTeams(given, dataParser);

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
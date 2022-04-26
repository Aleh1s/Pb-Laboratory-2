package ua.palamar.service;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import ua.palamar.builder.SoccerTableBuilder;
import ua.palamar.builder.TableBuilder;
import ua.palamar.counter.ScoreCounter;
import ua.palamar.counter.SoccerScoreCounter;
import ua.palamar.parser.DataParser;
import ua.palamar.parser.SoccerDataParser;
import ua.palamar.repository.SoccerTableRepository;
import ua.palamar.repository.TableRepository;

import java.io.File;

public class SoccerTableServiceTest extends TestCase {

    private final File dir = new File("src/test/resources/dir");
    private final TableRepository tableRepository = new SoccerTableRepository();
    private final DataParser dataParser = new SoccerDataParser();
    private final ScoreCounter scoreCounter = new SoccerScoreCounter();
    private final TableBuilder tableBuilder = new SoccerTableBuilder(dataParser, scoreCounter);
    private final SoccerTableService soccerTableService = new SoccerTableService(dir, tableRepository, tableBuilder);

    @Test
    public void testExportResultTable() {

        // given
        File resultFile = new File("src/test/resources/dir/result.csv");

        if (resultFile.exists()) {
            resultFile.delete();
        }

        soccerTableService.exportResultTable();
    }
}
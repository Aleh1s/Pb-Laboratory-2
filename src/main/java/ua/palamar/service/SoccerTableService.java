package ua.palamar.service;

import ua.palamar.builder.SoccerTableBuilder;
import ua.palamar.builder.TableBuilder;
import ua.palamar.counter.ScoreCounter;
import ua.palamar.parser.DataParser;
import ua.palamar.repository.TableRepository;
import ua.palamar.table.ResultTableList;

import java.io.File;

import static ua.palamar.file.FileValidator.*;

public class SoccerTableService implements TableService{

    private final File dir;
    private final TableRepository tableRepository;
    private final DataParser dataParser;

    private final ScoreCounter scoreCounter;

    public SoccerTableService(File dir,
                              TableRepository tableRepository,
                              DataParser dataParser,
                              ScoreCounter scoreCounter) {
        this.dir = dir;
        this.tableRepository = tableRepository;
        this.dataParser = dataParser;
        this.scoreCounter = scoreCounter;
    }

    @Override
    public void exportResultTable() {
        directoryExists(dir);
        directoryIsNotEmpty(dir);
        directoryHasOnlyCsvFiles(dir);
        resultFileExists(dir);

        File[] files = dir.listFiles();
        ResultTableList resultTableList = new ResultTableList();
        TableBuilder tableBuilder = new SoccerTableBuilder(dataParser, scoreCounter);

        for (File file: files) {
            String[] teams = tableRepository.getTeamsFromTable(file, dataParser);
            String resultTable = tableBuilder.buildResultTable(teams);
            resultTableList.add(resultTable);
        }

        tableRepository.saveTable(dir, resultTableList.concatAll());
    }
}

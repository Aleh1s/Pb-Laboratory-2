package ua.palamar.service;

import ua.palamar.builder.SoccerTableBuilder;
import ua.palamar.builder.TableBuilder;
import ua.palamar.counter.ScoreCounter;
import ua.palamar.parser.DataParser;
import ua.palamar.repository.TableRepository;
import ua.palamar.table.ResultTableList;

import java.io.File;

public class SoccerTableService implements TableService{

    private final File dir;
    private final TableRepository tableRepository;
    private final TableBuilder tableBuilder;

    public SoccerTableService(File dir,
                              TableRepository tableRepository,
                              TableBuilder tableBuilder) {
        this.dir = dir;
        this.tableRepository = tableRepository;
        this.tableBuilder = tableBuilder;
    }

    @Override
    public void exportResultTable() {
        validateDir(dir);
        deleteResultFileIfExists();
        File[] files = dir.listFiles();
        ResultTableList resultTableList = new ResultTableList();

        for (File file: files) {
            String[] teams = tableRepository.getTeamsFromTable(file);
            String resultTable = tableBuilder.buildResultTable(teams);
            resultTableList.add(resultTable);
        }

        tableRepository.saveTable(dir, resultTableList.concatAll());
    }

    public static void validateDir(File dir) {
        if (!dir.exists()) {
            throw new RuntimeException("Directory does not exist");
        }

        if (dir.list() == null) {
            throw new RuntimeException("Directory is empty");
        }
    }
    
    public void deleteResultFileIfExists() {
        File result = new File(dir, "result.csv");
        if (result.exists()) result.delete();
    }
}

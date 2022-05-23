package ua.palamar.service;

import ua.palamar.builder.SoccerTableBuilder;
import ua.palamar.builder.TableBuilder;
import ua.palamar.counter.ScoreCounter;
import ua.palamar.data.DataValidator;
import ua.palamar.notificator.ErrorNotificator;
import ua.palamar.parser.DataParser;
import ua.palamar.repository.TableRepository;
import ua.palamar.table.ResultTableList;

import java.io.File;

import static ua.palamar.file.FileValidator.*;

public class SoccerTableService implements TableService {

    private final File dir;
    private final TableRepository tableRepository;
    private final DataParser dataParser;
    private final ScoreCounter scoreCounter;
    private final DataValidator dataValidator;

    public SoccerTableService(
            File dir,
            TableRepository tableRepository,
            DataParser dataParser,
            ScoreCounter scoreCounter,
            DataValidator dataValidator
    ) {
        this.dir = dir;
        this.tableRepository = tableRepository;
        this.dataParser = dataParser;
        this.scoreCounter = scoreCounter;
        this.dataValidator = dataValidator;
    }



    @Override
    public boolean exportResultTable() {
        boolean directoryValid = isDirectoryValid(dir);

        if (!directoryValid) {
            return false;
        }

        File[] files = dir.listFiles();

        boolean isDataInFilesValid = validateDataInFiles(files);

        if (!isDataInFilesValid) {
            return false;
        }

        if (ErrorNotificator.getInstance().hasErrors()) {
            ErrorNotificator.getInstance().showErrorsIfExist();
            return false;
        }

        ResultTableList resultTableList = new ResultTableList();
        TableBuilder tableBuilder = new SoccerTableBuilder(dataParser, scoreCounter);

        for (File file : files) {
            String[] teams = tableRepository.getTeamsFromTable(file);
            String resultTable = tableBuilder.buildResultTable(teams, file);
            resultTableList.add(resultTable);
        }

        tableRepository.saveTable(dir, resultTableList.concatAll());
        return true;
    }

    public boolean validateDataInFiles(File[] files) {
        for (File file : files) {
            String[] teams = tableRepository.getTeamsFromTable(file);

            if (teams != null) {
                dataValidator.isDataValid(teams, file);
            }
        }

        return ErrorNotificator.getInstance().showErrorsIfExist();
    }


}

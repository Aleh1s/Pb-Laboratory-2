package ua.palamar.builder;

import ua.palamar.counter.ScoreCounter;
import ua.palamar.parser.DataParser;
import ua.palamar.repository.TableRepository;

import java.io.File;

public class SoccerTableBuilder implements TableBuilder {

    private final DataParser dataParser;
    private final ScoreCounter scoreCounter;

    public SoccerTableBuilder(DataParser dataParser,
                              ScoreCounter scoreCounter) {
        this.dataParser = dataParser;
        this.scoreCounter = scoreCounter;
    }

    @Override
    public String buildResultTable(File table, TableRepository tableRepository) {
        String[] teams = tableRepository.getTeamsFromTable(table);
        StringBuilder temp = new StringBuilder();

        for (String team : teams) {
            push(team, temp);
        }

        return temp.toString();
    }

    public void push(String team, StringBuilder temp) {
        String teamName = dataParser.parseName(team);
        int totalScore = scoreCounter.countTotalScore(team, dataParser);
    }
}

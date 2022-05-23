package ua.palamar.builder;

import ua.palamar.counter.ScoreCounter;
import ua.palamar.parser.DataParser;

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
    public String buildResultTable(String[] teams, File file) {
        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < teams.length; i++) {
            push(teams[i], temp, i, file);
        }

        return temp.toString();
    }

    public void push(String team, StringBuilder temp, int lineNumber, File file) {
        String teamName = dataParser.parseName(team);
        int totalScore = scoreCounter.countTotalScore(team, dataParser, lineNumber, file);

        temp.append(teamName).append(",").append(totalScore).append("\r\n");
    }
}

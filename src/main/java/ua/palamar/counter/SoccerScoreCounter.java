package ua.palamar.counter;

import ua.palamar.data.DataValidator;
import ua.palamar.parser.DataParser;

import java.io.File;

public class SoccerScoreCounter implements ScoreCounter {

    @Override
    public int countTotalScore(String team, DataParser dataParser, int lineNumber, File file) {
        int totalScore = 0;
        String matchOutcomesLine = dataParser.parseMatchOutcomesLine(team);
        String[] matchOutcomes = getMatchOutcomes(matchOutcomesLine);


        for (String matchOutcome : matchOutcomes) {

            String[] temp = matchOutcome.split(":");
            totalScore += determineScore(temp);
        }

        return totalScore;
    }

    public String[] getMatchOutcomes(String matchOutcomesLine) {
        return matchOutcomesLine.split(",");
    }

    public int determineScore(String[] matchOutcome) {
        int goalsScored = Integer.parseInt(matchOutcome[0]);
        int goalsMissed = Integer.parseInt(matchOutcome[1]);

        if (goalsScored > goalsMissed) {
            return 3;
        } else if (goalsScored < goalsMissed) {
            return 0;
        } else {
            return 1;
        }
    }
}

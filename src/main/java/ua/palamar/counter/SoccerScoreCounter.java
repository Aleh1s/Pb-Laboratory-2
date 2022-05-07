package ua.palamar.counter;

import ua.palamar.data.DataValidator;
import ua.palamar.parser.DataParser;

public class SoccerScoreCounter implements ScoreCounter {

    private final DataValidator dataValidator;

    public SoccerScoreCounter(DataValidator dataValidator) {
        this.dataValidator = dataValidator;
    }

    @Override
    public int countTotalScore(String team, DataParser dataParser) {
        int totalScore = 0;
        String matchOutcomesLine = dataParser.parseMatchOutcomesLine(team);
        String[] matchOutcomes = getMatchOutcomes(matchOutcomesLine);

        dataValidator.idEnoughNumberOfMatchOutcomes(matchOutcomes.length);

        for (String matchOutcome : matchOutcomes) {

            dataValidator.matchOutcomeIsNotEmpty(matchOutcome);
            dataValidator.isMatchOutcomeCorrect(matchOutcome);

            String[] temp = matchOutcome.split(":");
            totalScore += determineScore(temp, dataParser);
        }

        return totalScore;
    }

    public String[] getMatchOutcomes(String matchOutcomesLine) {
        return matchOutcomesLine.split(",");
    }

    public int determineScore(String[] matchOutcome, DataParser dataParser) {
        int goalsScored = dataParser.parseInteger(matchOutcome[0]);
        int goalsMissed = dataParser.parseInteger(matchOutcome[1]);

        dataValidator.isScoreValid(goalsScored, goalsMissed);

        if (goalsScored > goalsMissed) {
            return 3;
        } else if (goalsScored < goalsMissed) {
            return 0;
        } else {
            return 1;
        }
    }
}

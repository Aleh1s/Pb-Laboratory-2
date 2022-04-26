package ua.palamar.counter;

import ua.palamar.parser.DataParser;

public class SoccerScoreCounter implements ScoreCounter {


    @Override
    public int countTotalScore(String team, DataParser dataParser) {
        int totalScore = 0;
        String[] matchOutcomes = getMatchOutcomes(team);
        return 0;
    }

    public String[] getMatchOutcomes(String team) {
        String temp = team.substring(team.indexOf(','));
        return team.split(",");
    }

    public int determineScore(String[] matchOutcome) {
        int goalsScored = Integer
                .parseInt(matchOutcome[0]);
        int goalsMissed = Integer
                .parseInt(matchOutcome[1]);

        if (goalsScored > goalsMissed) {
            return 3;
        } else if (goalsScored < goalsMissed) {
            return 0;
        } else {
            return 1;
        }
    }
}

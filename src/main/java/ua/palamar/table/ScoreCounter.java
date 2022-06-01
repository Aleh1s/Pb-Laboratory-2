package ua.palamar.table;

public class ScoreCounter {

    public static int countTotalScore(String[] matchOutcomes) {
        int totalScore = 0;

        for (String matchOutcome : matchOutcomes) {

            String[] temp = matchOutcome.split(":");
            totalScore += determineScore(temp);
        }

        return totalScore;
    }

    private static int determineScore(String[] matchOutcome) {
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

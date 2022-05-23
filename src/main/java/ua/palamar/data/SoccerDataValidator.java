package ua.palamar.data;

import ua.palamar.notificator.ErrorNotificator;
import ua.palamar.parser.DataParser;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoccerDataValidator implements DataValidator {

    private final DataParser dataParser;

    public SoccerDataValidator(DataParser dataParser) {
        this.dataParser = dataParser;
    }

    public void matchOutcomesValidator(String teamData, int row, File file) {
        String teamName = dataParser.parseName(teamData);
        String matchOutcomesLine = dataParser.parseMatchOutcomesLine(teamData);
        String[] matchOutcomes = matchOutcomesLine.split(",");

        isTeamNameValid(teamName, row, file);
        isMatchOutcomesEnough(matchOutcomes.length, file, row);

        for (int i = 0; i < matchOutcomes.length; i++) {
            matchOutcomeIsNotEmpty(matchOutcomes[i], row, i, file);
            boolean matchOutcomeCorrect = isMatchOutcomeCorrect(matchOutcomes[i], row, i, file);
            if (matchOutcomeCorrect) {
                isScoreValid(matchOutcomes[i], row, i, file);
            }
        }
    }

    private void isMatchOutcomesEnough(int length, File file, int row) {
        if (length != 10) {
            ErrorNotificator.getInstance().addError("Not enough match outcomes", row + 1, -1, file.getName());
        }
    }

    private void matchOutcomeIsNotEmpty(String matchOutcome, int row, int col, File file) {
        if (matchOutcome.equals("")) {
            ErrorNotificator.getInstance().addError("Match outcome can not be empty", row + 1, col + 1, file.getName());
        }
    }

    private boolean isMatchOutcomeCorrect(String matchOutcome, int row, int col, File file) {
        if (!matchOutcome.contains(":")) {
            ErrorNotificator.getInstance().addError("Can not find delimiter ':'", row + 1, col + 1, file.getName());
            return false;
        }

        Pattern pattern = Pattern.compile("\\b\\d+:\\d+\\b");
        Matcher matcher = pattern.matcher(matchOutcome);

        if (!matcher.matches()) {
            ErrorNotificator.getInstance().addError("Match outcome format is invalid. Try to make like this 3:2", row + 1, col + 1, file.getName());
            return false;
        }
        return true;
    }

    public void isScoreValid(String matchOutcome, int row, int col, File file) {
        int goalsScored = Integer.parseInt(matchOutcome.split(":")[0]);
        int goalsMissed = Integer.parseInt(matchOutcome.split(":")[1]);

        if (goalsScored < 0 || goalsMissed < 0) {
            ErrorNotificator.getInstance().addError("Score can not be negative", row + 1, col + 1, file.getName());
        }
    }

    public void isTeamNameValid(String str, int row, File file) {
        if (str.equals("")) {
            ErrorNotificator.getInstance().addError(
                    "Team name can not be empty", row + 1, 0, file.getName()
            );
        }
    }

    @Override
    public void isDataValid(String[] teams, File file) {
        for (int i = 0; i < teams.length; i++) {
            matchOutcomesValidator(teams[i], i, file);
        }
    }
}

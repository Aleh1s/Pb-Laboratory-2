package ua.palamar.validator;

import ua.palamar.logger.Logger;

import java.io.File;
import java.util.Objects;
import java.util.regex.Pattern;

public class DataValidator {

    private static final Logger logger = Logger.getInstance();

    public static boolean UnparsedTeamsAreValid(String[] unparsedTeams, File file) {
        if (!Objects.nonNull(unparsedTeams)) {
            return false;
        }

        boolean isValid = true;
        for (int i = 0; i < unparsedTeams.length; i++) {
            if (!unparsedTeamIsValid(unparsedTeams[i], file, i + 1)) {
                isValid = false;
            }
        }

        return isValid;
    }

    private static boolean unparsedTeamIsValid(String unparsedTeam, File file, int row) {
        Pattern pattern = Pattern.compile("([A-Za-z\\d ])+,(\\d+:\\d+,){9}(\\d+:\\d)");
        boolean isValid = pattern.matcher(unparsedTeam).matches();
        if (!isValid) {
            String[] cells = unparsedTeam.split(",");
            findCauseInTeamName(cells, file, row);
            findCauseInMatchOutcomes(cells, file, row);
            return false;
        }

        return true;
    }

    private static void findCauseInMatchOutcomes(String[] cells, File file, int row) {
        String[] matchOutcomes;
        if (cells.length > 1) {
            matchOutcomes = new String[cells.length - 1];
            for (int i = 1; i < cells.length; i++) {
                matchOutcomes[i - 1] = cells[i];
            }

            if (matchOutcomes.length != 10) {
                logger.log(
                        String.format(
                                "Invalid data. Match outcomes count should be 10. File %s, Row %s",
                                file.getName(),
                                row
                        )
                );
            }


            for (int i = 0; i < matchOutcomes.length; i++) {
                findCauseInMatchOutcome(matchOutcomes[i], file, row, i + 1);
            }
        }
    }

    private static void findCauseInMatchOutcome(String matchOutcome, File file, int row, int col) {
        Pattern pattern = Pattern.compile("\\b\\d+:\\d+\\b");
        boolean matches = pattern.matcher(matchOutcome).matches();
        if (!matches) {
            logger.log(
                    String.format(
                            "Invalid data. Match outcome's format is invalid. File %s, row %d, col %d",
                            file.getName(),
                            row,
                            col
                    )
            );
        }
    }

    private static void findCauseInTeamName(String[] cells, File file, int row) {
        String name = null;
        if (cells.length != 0) {
            name = cells[0];
        }

        if (Objects.equals(name, "") || Objects.equals(name, null)) {
            logger.log(String.format("Invalid data. Team name can not be empty. File %s row %d", file.getName(), row));
        }
    }


}

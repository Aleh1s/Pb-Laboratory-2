package ua.palamar.data;

public class SoccerDataValidator implements DataValidator {
    @Override
    public void idEnoughNumberOfMatchOutcomes(int length) {
        if (length != 10) {
            throw new RuntimeException(
                    String.format("Invalid data exception. Expected 10 match outcomes but actually %d", length)
            );
        }
    }

    @Override
    public void matchOutcomeIsNotEmpty(String str) {
        if (str.equals("")) {
            throw new RuntimeException(
                    "Invalid data exception. Match outcome can not be empty"
            );
        }
    }

    @Override
    public void isMatchOutcomeCorrect(String srt) {
        int delimiterIndex = srt.indexOf(":");
        if (delimiterIndex != -1) {
            String beforeDelimiter = srt.substring(0, delimiterIndex);
            String afterDelimiter = srt.substring(delimiterIndex + 1);

            if (beforeDelimiter.equals("") || afterDelimiter.equals("")) {
                throw new RuntimeException(
                        String.format("Invalid data exception. Match outcome can not contain empty score %s", srt)
                );

            }
        } else {
            throw new RuntimeException(
                    String.format(
                            "Invalid data exception. Expected delimiter is ':' but actually %s", srt
                    )
            );
        }
    }

    @Override
    public void isScoreValid(int goalsScored, int goalsMissed) {
        if (goalsScored < 0 || goalsMissed < 0) {
            throw new RuntimeException("Invalid data exception. Score can not be negative");
        }
    }

    @Override
    public void isTeamNameValid(String str) {
        if (str == null || str.equals("")) {
            throw new RuntimeException(
                    "Team name can not be empty or null"
            );
        }
    }
}

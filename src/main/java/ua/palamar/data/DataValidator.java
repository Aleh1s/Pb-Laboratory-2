package ua.palamar.data;

public interface DataValidator {

    void idEnoughNumberOfMatchOutcomes(int length);
    void matchOutcomeIsNotEmpty(String str);
    void isMatchOutcomeCorrect(String srt);
    void isScoreValid(int goalsScored, int goalsMissed);
    void isTeamNameValid(String str);
}

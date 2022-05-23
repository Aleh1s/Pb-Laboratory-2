package ua.palamar.parser;

public class SoccerDataParser implements DataParser {

    @Override
    public String parseName(String team) {
        return team.substring(0, team.indexOf(','));
    }

    @Override
    public String parseMatchOutcomesLine(String team) {
        return team.substring(team.indexOf(',') + 1);
    }
}

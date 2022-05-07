package ua.palamar.parser;

import ua.palamar.data.DataValidator;

public class SoccerDataParser implements DataParser {

    private final DataValidator dataValidator;

    public SoccerDataParser(DataValidator dataValidator) {
        this.dataValidator = dataValidator;
    }

    @Override
    public String parseName(String team) {
        String name = team.substring(0, team.indexOf(','));
        dataValidator.isTeamNameValid(name);
        return name;
    }

    @Override
    public String parseMatchOutcomesLine(String team) {
        return team.substring(team.indexOf(',') + 1);
    }

    @Override
    public Integer parseInteger(String str) {
        try {
            return Integer.parseInt(str);

        } catch (RuntimeException e) {
            throw new RuntimeException(
                    String.format(
                            "Invalid data exception. Expected number but actually [ %s ]", str
                    )
            );
        }
    }


}

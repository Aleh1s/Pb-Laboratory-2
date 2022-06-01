package ua.palamar.parser;

public class DataParser {

    public static String parseName(String unparsedTeam) {
        return unparsedTeam.substring(0, unparsedTeam.indexOf(","));
    }

    public static String[] parseMatchOutcomes(String unparsedTeam) {
        String matchOutcomeLine = unparsedTeam.substring(unparsedTeam.indexOf(',') + 1);
        return matchOutcomeLine.split(",");
    }

}

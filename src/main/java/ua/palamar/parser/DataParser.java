package ua.palamar.parser;

public interface DataParser {

    String parseName(String team);

    String parseMatchOutcomesLine(String team);

    Integer parseInteger(String str);


}

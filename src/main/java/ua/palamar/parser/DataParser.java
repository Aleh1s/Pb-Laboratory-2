package ua.palamar.parser;

import java.io.File;

public interface DataParser {

    String parseName(String team);

    String parseMatchOutcomesLine(String team);


}

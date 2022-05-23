package ua.palamar.counter;

import ua.palamar.parser.DataParser;

import java.io.File;

public interface ScoreCounter {

    int countTotalScore(String team, DataParser dataParser, int lineNumber, File file);

}

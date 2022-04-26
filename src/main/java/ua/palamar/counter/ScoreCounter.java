package ua.palamar.counter;

import ua.palamar.parser.DataParser;

public interface ScoreCounter {

    int countTotalScore(String team, DataParser dataParser);

}

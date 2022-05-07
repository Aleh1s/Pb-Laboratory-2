package ua.palamar.repository;

import ua.palamar.parser.DataParser;

import java.io.File;

public interface TableRepository {

    String[] getTeamsFromTable(File file, DataParser dataParser);
    void saveTable(File dir, String table);
}

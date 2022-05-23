package ua.palamar.repository;

import ua.palamar.parser.DataParser;

import java.io.File;

public interface TableRepository {

    String[] getTeamsFromTable(File file);
    void saveTable(File dir, String table);
}

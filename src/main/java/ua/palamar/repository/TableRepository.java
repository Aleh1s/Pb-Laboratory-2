package ua.palamar.repository;

import java.io.File;

public interface TableRepository {

    String[] getTeamsFromTable(File table);

    void saveTable(File dir);
}

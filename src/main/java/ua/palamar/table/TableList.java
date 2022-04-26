package ua.palamar.table;

import java.util.ArrayList;
import java.util.List;

public class TableList {

    private final List<String> tables = new ArrayList<>();

    public void add(String table) {
        tables.add(table);
    }

    public String concatAll() {
        StringBuilder stringBuilder = new StringBuilder();

        for (String table : tables) stringBuilder.append(table);

        return stringBuilder.toString();
    }
}

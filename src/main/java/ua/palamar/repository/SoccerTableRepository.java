package ua.palamar.repository;

import java.io.*;

public class SoccerTableRepository implements TableRepository {


    @Override
    public String[] getTeamsFromTable(File file) {
        String[] teams = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String row;
            for (int i = 0; (row = reader.readLine()) != null; i++) {
                if (i == 0) {
                    int size = countTeams(row);
                    teams = new String[size];
                } else {
                    teams[i - 1] = row;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return teams;
    }

    @Override
    public void saveTable(File dir, String table) {
//        File result =
//
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter()));
    }

    public int countTeams(String row) {
        return Integer.parseInt(row);
    }

//    public File createResultFile(File dir) {
//
//    }
}

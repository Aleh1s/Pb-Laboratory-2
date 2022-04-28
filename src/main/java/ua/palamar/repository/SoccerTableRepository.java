package ua.palamar.repository;

import java.io.*;

public class SoccerTableRepository implements TableRepository {


    @Override
    public String[] getTeamsFromTable(File file) {
        String[] teams;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int size = countTeams(reader.readLine());

            teams = new String[size];
            for (int i = 1; i < size + 1; i++) {
                teams[i - 1] = reader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return teams;
    }

    @Override
    public void saveTable(File dir, String table) {
        File result = createResultFile(dir);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(result))) {
            writer.write(table);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int countTeams(String row) {
        return Integer.parseInt(row);
    }

    public File createResultFile(File dir) {
        File resultFile = new File(dir, "result.csv");

        if (resultFile.exists())
            return resultFile;

        try {
            resultFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return resultFile;
    }
}

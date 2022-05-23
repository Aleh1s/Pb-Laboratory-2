package ua.palamar.repository;

import ua.palamar.notificator.ErrorNotificator;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoccerTableRepository implements TableRepository {

    @Override
    public String[] getTeamsFromTable(File file) {
        String[] teams;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int size = countTeams(reader.readLine(), file);

            if (size == -1) {
                return null;
            }

            teams = new String[size];
            for (int i = 0; i < size; i++) {
                teams[i] = reader.readLine();
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

    public int countTeams(String row, File file) {
        Pattern pattern = Pattern.compile("\\b\\d+\\b");
        Matcher matcher = pattern.matcher(row);

        if (!matcher.matches()) {
            ErrorNotificator.getInstance().addError("Number of teams is invalid", 0 ,0, file.getName());
            return -1;
        }

        return Integer.parseInt(row);
    }

    public File createResultFile(File dir) {
        File resultFile = new File(dir, "result.csv");

        try {
            resultFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return resultFile;
    }
}

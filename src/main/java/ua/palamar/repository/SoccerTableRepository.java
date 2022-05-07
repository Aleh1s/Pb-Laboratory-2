package ua.palamar.repository;

import ua.palamar.file.FileValidator;
import ua.palamar.parser.DataParser;

import java.io.*;

public class SoccerTableRepository implements TableRepository {


    @Override
    public String[] getTeamsFromTable(File file, DataParser dataParser) {
        String[] teams;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int size = countTeams(reader.readLine(), dataParser);

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

    public int countTeams(String row, DataParser dataParser) {
        return dataParser.parseInteger(row);
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

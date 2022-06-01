package ua.palamar.IOStream;

import ua.palamar.logger.Logger;

import java.io.*;
import java.util.regex.Pattern;

public class IOStream {

    private static final Logger logger = Logger.getInstance();
    public String[] readLines(File file) {
        String[] lines;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String teamCountStr = reader.readLine();

            if (isTeamCountValid(teamCountStr, file)) {
                int teamCount = Integer.parseInt(teamCountStr);

                lines = new String[teamCount];
                for (int i = 0; i < teamCount; i++) {
                    lines[i] = reader.readLine();
                }

                return lines;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void write(File dir, String str) {
        File result = createResultFileIfNotExist(dir);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(result, false))) {
            writer.write(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File createResultFileIfNotExist(File dir) {
        File resultFile = new File(dir, "result.csv");

        if (!resultFile.exists()) {
            try {
                resultFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return resultFile;
    }

    private boolean isTeamCountValid(String teamCountStr, File file) {
        Pattern pattern = Pattern.compile("\\b\\d+\\b");

        boolean countIsValid = pattern.matcher(teamCountStr).matches();
        if (!countIsValid) {
            logger.log(String.format("Invalid team count '%s' in file %s", teamCountStr, file.getName()));
            return false;
        }

        return true;
    }
}

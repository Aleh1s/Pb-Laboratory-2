package ua.palamar.file;

import java.io.File;
import java.util.Objects;

public class FileValidator {

    public static void directoryExists(File dir) {
        if (!dir.exists())
            throw new RuntimeException(
                    String.format("Directory %s does not exist", dir.getName())
            );
    }

    public static void directoryIsNotEmpty(File dir) {
        boolean isEmpty = dir.list().length == 0;
        if (isEmpty)
            throw new RuntimeException(
                    String.format("Directory %s is empty", dir.getName())
            );
    }

    public static void directoryHasOnlyCsvFiles(File dir) {
        String[] files = dir.list();
        for (String file : files){
            String[] temp = file.split("\\.");
            String endOfFile = temp[temp.length - 1];
            if (!Objects.equals(endOfFile, "csv")) {
                throw new RuntimeException(
                        String.format("File in directory has .%s format but should be .csv", endOfFile)
                );
            }
        }
    }

    public static void resultFileExists(File dir) {
        File file = new File(dir, "result.csv");

        if (file.exists()) {
            throw new RuntimeException(
                String.format("Result file exists in %s", dir.getPath())
            );
        }
    }

}

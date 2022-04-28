package ua.palamar.file;

import java.io.File;
import java.util.Objects;

public class FileValidatorImpl {

    public static void fileExists(File dir, String filename) {
        File file = new File(dir, filename);
        if (!file.exists())
            throw new IllegalStateException("File does not exist");
    }

    public static void directoryExists(File dir) {
        if (!dir.exists())
            throw new IllegalStateException("Directory does not exist");
    }

    public static void directoryIsNotEmpty(File dir) {
        boolean isEmpty = dir.list() == null;
        if (isEmpty)
            throw new IllegalStateException("Directory is empty");
    }

    public static void directoryHasOnlyCsvFiles(File dir) {
        String[] files = dir.list();
        for (String file : files){
            String[] temp = file.split("\\.");
            String endOfFile = temp[temp.length - 1];
            if (!Objects.equals(endOfFile, "csv")) {
                throw new IllegalStateException(
                        String.format("File in directory has .%s format but should be .csv", endOfFile)
                );
            }
        }
    }

    public static void deleteFileIfExists(File dir, String filename) {
        File result = new File(dir, filename);
        if (result.exists()) result.delete();
    }

}

package ua.palamar.validator;

import ua.palamar.logger.Logger;

import java.io.File;
import java.util.Objects;
import java.util.regex.Pattern;

public class DirectoryValidator {

    private static final Logger logger = Logger.getInstance();
    public static boolean isDirectoryValid(File dir) {
        boolean directoryExists = directoryExists(dir);
        if (directoryExists) {
            boolean directoryIsEmpty = directoryIsEmpty(dir);
            if (!directoryIsEmpty) {
                return directoryHasOnlyCsvFiles(dir);
            }
            return false;
        }
        return false;
    }

    private static boolean directoryExists(File dir) {
        if (!dir.exists()) {
            logger.log(String.format("Directory %s does not exist", dir.getPath()));
            return false;
        }

        return true;
    }
    private static boolean directoryIsEmpty(File dir) {
        String[] files = dir.list();
        if (!Objects.nonNull(files) || files.length == 0) {
            logger.log(String.format("Directory '%s' is empty", dir.getPath()));
            return true;
        }

        return false;
    }

    private static boolean directoryHasOnlyCsvFiles(File dir) {
        boolean result =  true;
        String[] files = dir.list();

        if (!Objects.nonNull(files)) {
            return false;
        }

        Pattern pattern = Pattern.compile(".+\\.csv");
        for (String file : files) {
            boolean isCsv = pattern.matcher(file).matches();
            if (!isCsv) {
                logger.log(String.format("File '%s' must be csv format", file));
                result = false;
            }
        }

        return result;
    }
}

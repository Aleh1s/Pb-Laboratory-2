package ua.palamar.file;

import ua.palamar.notificator.ErrorNotificator;

import java.io.File;
import java.util.Objects;

public class FileValidator {

    private static void directoryIsNotEmpty(File dir) {
        boolean isEmpty = dir.list().length == 0;
        if (isEmpty)
            ErrorNotificator.getInstance().addError("Directory is empty", -1, -1, dir.getPath());
    }

    private static void directoryHasOnlyCsvFiles(File dir) {
        String[] files = dir.list();
        for (String file : files){
            String[] temp = file.split("\\.");
            String endOfFile = temp[temp.length - 1];
            if (!Objects.equals(endOfFile, "csv")) {
                ErrorNotificator.getInstance().addError("Directory must contains only scv files", -1, -1, dir.getPath());
            }
        }
    }

    private static void resultFileExists(File dir) {
        File file = new File(dir, "result.csv");

        if (file.exists()) {
            ErrorNotificator.getInstance().addError("The result file exists. You should delete it", -1, -1 ,dir.getPath());
        }
    }

    public static boolean isDirectoryValid(File dir) {
        directoryIsNotEmpty(dir);
        directoryHasOnlyCsvFiles(dir);
        resultFileExists(dir);
        return ErrorNotificator.getInstance().showErrorsIfExist();
    }

}

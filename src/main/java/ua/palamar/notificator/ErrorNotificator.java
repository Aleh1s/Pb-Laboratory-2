package ua.palamar.notificator;

import java.util.ArrayList;
import java.util.List;

public class ErrorNotificator {
    private static ErrorNotificator errorNotificator;
    private final List<String> notifications = new ArrayList<>();

    private ErrorNotificator() {
    }

    public static ErrorNotificator getInstance() {
        if (errorNotificator == null)
            errorNotificator = new ErrorNotificator();


        return errorNotificator;
    }

    public boolean showErrorsIfExist() {
        if (hasErrors()) {
            for (String error : notifications) System.err.println(error);
            clear();
            return false;
        }

        return true;
    }

    public void addError(String message, int row, int col, String fileName) {
        String error = String.format("Message: %s, error in position [row: %d] [col: %d], in file %s", message, row + 1, col + 1, fileName);
        notifications.add(error);
    }

    public boolean hasErrors() {
        return !notifications.isEmpty();
    }

    public void clear() {
        notifications.clear();
    }
}

package ua.palamar.data;

import java.io.File;

public interface DataValidator {
    void isDataValid(String[] teams, File file);
}

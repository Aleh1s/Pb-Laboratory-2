package ua.palamar;

import ua.palamar.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new View();
        View.displayTitle();
        view.setDirectory();
        view.startExecuting();
        view.displayAbsolutePathToResultFile();
    }
}
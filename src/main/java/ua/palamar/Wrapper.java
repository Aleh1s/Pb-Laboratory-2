package ua.palamar;

import ua.palamar.logger.Logger;
import ua.palamar.table.TournamentTable;
import ua.palamar.validator.DirectoryValidator;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class Wrapper {

    private static final String TITLE = """
                                                                                                                                               \s
            ,--------.                                                                          ,--.   ,--------.          ,--.    ,--.        \s
            '--.  .--'  ,---.  ,--.,--. ,--.--. ,--,--,   ,--,--. ,--,--,--.  ,---.  ,--,--,  ,-'  '-. '--.  .--'  ,--,--. |  |-.  |  |  ,---. \s
               |  |    | .-. | |  ||  | |  .--' |      \\ ' ,-.  | |        | | .-. : |      \\ '-.  .-'    |  |    ' ,-.  | | .-. ' |  | | .-. :\s
               |  |    ' '-' ' '  ''  ' |  |    |  ||  | \\ '-'  | |  |  |  | \\   --. |  ||  |   |  |      |  |    \\ '-'  | | `-' | |  | \\   --.\s
               `--'     `---'   `----'  `--'    `--''--'  `--`--' `--`--`--'  `----' `--''--'   `--'      `--'     `--`--'  `---'  `--'  `----'\s
                                                                                                                                               \s
            """;
    private File directory;
    TournamentTable tournamentTable = new TournamentTable();

    public static void showTitle() {
        System.out.println(TITLE);
    }
    public void setDirectoryPath() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Write path to directory: ");
        File directory = new File(scanner.nextLine());

        if (!DirectoryValidator.isDirectoryValid(directory)) {
            System.err.printf("Directory %s is not valid. See log file %s", directory.getPath(), Logger.getLogFilePath());
            System.exit(-1);
        }

        this.directory = directory;
    }

    public void exportTable() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("If result file exists, it will be removed. Do you want to continue? [Y/N] ");
        boolean exit = false;

        while (!exit) {
            String chosen = scanner.nextLine();
            switch (chosen.toLowerCase(Locale.ROOT)) {
                case "y" -> {
                    boolean success = tournamentTable.loadTournamentTable(directory);
                    if (success) {
                        tournamentTable.exportTournamentTable(directory);
                        showResultFilePath();
                    }
                    exit = true;
                }
                case "n" -> exit = true;
                default -> System.err.println("Invalid input. Try again.");
            }
        }
    }

    public void showResultFilePath() {
        System.out.println("Congratulations !!! You can find your result file in this directory: " + directory.getPath());
    }
}

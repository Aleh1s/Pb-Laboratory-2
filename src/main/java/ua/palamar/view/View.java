package ua.palamar.view;

import ua.palamar.counter.SoccerScoreCounter;
import ua.palamar.data.SoccerDataValidator;
import ua.palamar.parser.SoccerDataParser;
import ua.palamar.repository.SoccerTableRepository;
import ua.palamar.service.SoccerTableService;
import ua.palamar.service.TableService;

import java.io.File;
import java.util.Scanner;

public class View {

    private File dir;
    private final static String TITLE = """
                                                                                                                                         \s
            ,--------.          ,--.    ,--.             ,---.                   ,--. ,--.                   ,--.   ,--.                 \s
            '--.  .--'  ,--,--. |  |-.  |  |  ,---.     /  O  \\   ,---.   ,---.  |  | `--'  ,---.  ,--,--. ,-'  '-. `--'  ,---.  ,--,--, \s
               |  |    ' ,-.  | | .-. ' |  | | .-. :   |  .-.  | | .-. | | .-. | |  | ,--. | .--' ' ,-.  | '-.  .-' ,--. | .-. | |      \\\s
               |  |    \\ '-'  | | `-' | |  | \\   --.   |  | |  | | '-' ' | '-' ' |  | |  | \\ `--. \\ '-'  |   |  |   |  | ' '-' ' |  ||  |\s
               `--'     `--`--'  `---'  `--'  `----'   `--' `--' |  |-'  |  |-'  `--' `--'  `---'  `--`--'   `--'   `--'  `---'  `--''--'\s
                                                                 `--'    `--'                                                            \s
            Table Application v1.0
            Developed by Oleksandr Palamarchuk
            """;

    public static void displayTitle() {
        System.out.println(TITLE);
    }

    public void setDirectory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write a directory path: ");

        File dir = new File(scanner.nextLine());

        this.dir = dir;
    }

    public void executeTableExporting() {
        TableService tableService = new SoccerTableService(
                this.dir,
                new SoccerTableRepository(),
                new SoccerDataParser(
                        new SoccerDataValidator()
                ),
                new SoccerScoreCounter(
                        new SoccerDataValidator()
                )
        );

        tableService.exportResultTable();
    }

    public static void displayFakeLoading() {
        System.out.println("Exporting...");
    }

    public void displayAbsolutePathToResultFile() {
        File file = new File(this.dir, "result.csv");
        System.out.printf("Path to your result file is %s", file.getAbsolutePath());
    }
}

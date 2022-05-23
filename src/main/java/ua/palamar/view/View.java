package ua.palamar.view;

import ua.palamar.counter.SoccerScoreCounter;
import ua.palamar.data.DataValidator;
import ua.palamar.data.SoccerDataValidator;
import ua.palamar.parser.SoccerDataParser;
import ua.palamar.repository.SoccerTableRepository;
import ua.palamar.service.SoccerTableService;
import ua.palamar.service.TableService;

import java.io.File;
import java.util.Objects;
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

    public int setDirectory() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write a directory path or -1 if you want to close program: ");
        String input = scanner.nextLine();

        if (Objects.equals(input, "-1")) {
            return -1;
        }

        File dir = new File(input);

        if (!dir.exists()) {
            System.err.println("Directory does not exist. Try to change path.");
            return 0;
        }

        this.dir = dir;
        return 1;
    }

    public void exportTable() {
        SoccerDataParser dataParser = new SoccerDataParser();
        DataValidator dataValidator = new SoccerDataValidator(dataParser);

        while(true) {
            int statusCode = setDirectory();

            if (statusCode == -1) break;

            else if (statusCode == 1){
                TableService tableService = new SoccerTableService(
                        this.dir,
                        new SoccerTableRepository(),
                        dataParser,
                        new SoccerScoreCounter(),
                        dataValidator
                );

                boolean failedToExecute = !tableService.exportResultTable();

                if (failedToExecute) System.err.println("Check your files and make changes in regard to errors");

                else {
                    displayAbsolutePathToResultFile();
                    break;
                }
            }
        }
    }

    public void displayAbsolutePathToResultFile() {
        File file = new File(this.dir, "result.csv");
        System.out.printf("Path to your result file is %s\n", file.getAbsolutePath());
    }
}

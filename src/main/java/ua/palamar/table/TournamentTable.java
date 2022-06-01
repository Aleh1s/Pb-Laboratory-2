package ua.palamar.table;

import ua.palamar.IOStream.IOStream;
import ua.palamar.logger.Logger;
import ua.palamar.parser.DataParser;
import ua.palamar.validator.DataValidator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TournamentTable {
    private final List<SoccerTeam> teams;
    private static final Logger logger = Logger.getInstance();

    public TournamentTable() {
        teams = new ArrayList<>();
    }

    private void addAll(String[] unparsedTeams) {
        for (String unparsedTeam : unparsedTeams) {
            add(unparsedTeam);
        }
    }

    private void add(String unparsedTeam) {
        String teamName = DataParser.parseName(unparsedTeam);
        String[] matchOutcomes = DataParser.parseMatchOutcomes(unparsedTeam);
        int points = ScoreCounter.countTotalScore(matchOutcomes);
        SoccerTeam soccerTeam = new SoccerTeam(teamName, points);

        teams.add(soccerTeam);
    }

    public boolean loadTournamentTable(File dir) {
        List<String[]> unparsedTeamsList = new ArrayList<>();
        IOStream ioStream = new IOStream();
        boolean allFilesAreValid = true;

        File[] files = dir.listFiles();

        if (!Objects.nonNull(files)) {
            logger.log(String.format("Directory '%s' is empty", dir.getName()));
            System.err.printf("Directory is empty. See logs %s", Logger.getLogFilePath());
            System.exit(-1);
        }

        for (File file : files) {
            if (!file.getName().equals("result.csv")) {
                String[] unparsedTeams = ioStream.readLines(file);
                if (!DataValidator.UnparsedTeamsAreValid(unparsedTeams, file)) {
                    allFilesAreValid = false;
                }

                if (allFilesAreValid) {
                    unparsedTeamsList.add(unparsedTeams);
                }
            }
        }

        if (allFilesAreValid) {
            for (String[] unparsedTeams : unparsedTeamsList) {
                addAll(unparsedTeams);
            }
        } else {
            System.err.printf("Invalid data. See logs %s", Logger.getLogFilePath());
        }

        return allFilesAreValid;
    }

    public void exportTournamentTable(File dir) {
        IOStream ioStream = new IOStream();
        StringBuilder resultTable = new StringBuilder();

        for (SoccerTeam team: teams) {
            resultTable.append(team.toCsv()).append("\n");
        }

        String table = resultTable.toString();
        table = table.substring(0, table.length() - 1);

        ioStream.write(dir, table);
    }


}

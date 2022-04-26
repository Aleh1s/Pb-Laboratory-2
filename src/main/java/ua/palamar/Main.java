package ua.palamar;

import ua.palamar.repository.SoccerTableRepository;
import ua.palamar.repository.TableRepository;

import java.io.File;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        TableRepository repository = new SoccerTableRepository();
        String[] teamsFromTable = repository.getTeamsFromTable(new File("src/main/resources/input.csv"));
        System.out.println(Arrays.toString(teamsFromTable));
    }
}
package ua.palamar.table;

public class SoccerTeam {
    private String name;
    private int points;

    public SoccerTeam(String name, int points) {
        this.name = name;
        this.points = points;
    }


    public String getName() {
        return name;
    }

    public String toCsv() {
        return String.format("%s,%d", this.name, this.points);
    }
}

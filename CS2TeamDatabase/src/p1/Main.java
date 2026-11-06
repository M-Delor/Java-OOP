package p1;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Counts Lines in the Input File if Needed

    //    try {
    //        File file = new File("Roster.txt");
    //        Scanner sc = new Scanner(file);

    //    int lineCount = 0;

    //    while (sc.hasNextLine()) {
    //            String line = sc.nextLine().trim();

    //            if(!line.isEmpty()) {
    //               lineCount++;
    //            }
    //        }
    //    }

    //    catch (FileNotFoundException ex) {
    //        System.out.println("404 Not Found");
    //    }

        ArrayList<Team> teams = new ArrayList<>();

        try {

            File file = new File("Roster.txt");

            Scanner input = new Scanner(file);

            while (input.hasNext()) {

                    String teamName = input.next();
                    String userName = input.next();
                    String role = input.next();
                    double rating = input.nextDouble();

                    Team team = null;
                    for (Team t : teams) {
                        if (t.getTeamName().equalsIgnoreCase(teamName)) {
                            team = t;
                            break;
                        }
                    }

                    if (team == null) {
                        team = new Team(teamName);
                        teams.add(team);
                    }

                     Player p = new Player(teamName, userName, role, rating);

                    team.addPlayer(p);

            }
            input.close();
        }

        catch (FileNotFoundException e) {
            System.out.println("Cannot find File.");
        }

        ArrayList<Player> players = new ArrayList<>();

        for (Team team : teams) {
            System.out.println(team.getTeamName() + " ");
            for (Player p : team.getPlayers()) {
                players.add(p);
                System.out.println(" " + p.getUsername() + " " + p.getRole() + " " + p.getRating());
            }
        }


        ExportFile("TeamData", teams, players, false);

    }

    public static void ExportFile(String filename, ArrayList<Team> teams, ArrayList<Player> players, boolean append) {

        try {

            FileWriter file = new FileWriter(filename);
            PrintWriter output = new PrintWriter(file, append);

            for (Team team : teams) {
                output.println("Team: " + team.getTeamName());
             for (Player p : players) {
                output.println("Player: " + p.getUsername() + " Role: " + p.getRole()
                        + " Rating: " + p.getRating());
                }
            }
        }

        catch (IOException exx) {
            System.out.println("Cannot Find File");
        }
    }
}

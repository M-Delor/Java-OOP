package p1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Player[] roster = {
                new IGL("Mason", "AWPer", 0.25, 8),
                new Rifler("Parker", "Anchor", 0.15, 11, 0.20),
                new Rifler("Ethan", "", 0.07, 8, 0.10),
                new Support("Wyatt", "AWPer", 0.05, 20),
                new Anchor("Michael", "Rifler", 0.10, .35)
             // new Support("Thomas", "", 0.01, 10)
        };

        roster[0].assignMaps("Nuke", "Dust 2");
        roster[1].assignMaps("Nuke", "Dust 2");
        roster[2].assignMaps("Nuke", "Dust 2");
        roster[3].assignMaps("Cache", "Ancient");
        roster[4].assignMaps("Inferno", "Cache");

        Stack team = new Stack("Razzmatazz");
        for (Player p : roster) {
            team.addPlayer(p);
        }

        System.out.println("=== Match Setup ===");
        System.out.println("Enter Map Played: ");
        String mapPlayed = sc.nextLine();
        System.out.println("Enter Starting Side (CT/T): ");
        String startingSide = sc.nextLine();
        System.out.println("Is Mason AWPing Tonight? (yes/no)");
        String isAwping = sc.nextLine();
        System.out.println();

        if (isAwping.equalsIgnoreCase("yes")) {
            roster[0].assignRole("AWPer");
        }

        team.printScoreboard(mapPlayed);

        double winChance = team.calculateWinProbability(mapPlayed, startingSide);
        team.printMatchSummary(winChance, mapPlayed, startingSide);

        sc.close();

    }
    

}


package p1;

import java.util.ArrayList;

public class Team {

    private String teamName;
    public ArrayList<Player> roster = new ArrayList<>();
    private static final int MAX_PlAYERS = 5;

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public void addPlayer(Player p) {
        if (roster.size() > MAX_PlAYERS) {
            System.out.println("Cannot Add " + p.getUsername() +
                    " to Team " + p.getTeamName() + ". Team is full! (5/5)");
        }
        roster.add(p);
    }

    public String getTeamName() {
        return this.teamName;
    }

    public int getSize() {
        return roster.size();
    }

    public ArrayList<Player> getPlayers() {
        return roster;
    }

}

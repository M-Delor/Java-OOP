package p1;

public class Stack {

    private String teamName;
    private Player[] roster;
    private int count;

    public Stack(String teamName) {
        this.teamName = teamName;
        this.roster = new Player[5];
        this.count = 0;
    }

    public Stack(Stack other) {
        if (other != null) {
            this.teamName = other.teamName;
            this.count = other.count;
            this.roster = new Player[5];
            for (int i = 0; i < other.count; i++) {
                Player p = other.roster[i];
            }
        }
    }

    public boolean addPlayer(Player p) {
        if (p == null || count >= 5) {
            System.out.println("Error: Attempting add a null player or too many entries.");
            return false;
        }

        for (int i = 0; i < this.count; i++) {
            if (this.roster[i].equals(p)) {
                System.out.println("Warning: Duplicate Found");
                return false;
            }
        }

        this.roster[this.count] = p;
        this.count++;
        return true;

    }

    public int calculateTeamKills(String mapPlayed) {
        int total = 0;

        for (int i = 0; i < this.count; i++) {
            total += this.roster[i].calculateKills(mapPlayed);
        }
        return total;
    }

    private double getMapSideBias(String mapPlayed, String startingSide) {

        if (mapPlayed == null || startingSide == null) {
            return 0.0;
        }

        String map = mapPlayed.trim().toLowerCase();
        boolean isCt = startingSide.equalsIgnoreCase("CT");

        switch(map) {
            // Heavily ct sided maps
            case "nuke":
            case "mirage":
            case "overpass":
                return isCt ? 8.0 : -6.0;
            // Heavily t sided maps
            case "anubis":
                return isCt ? -8.0 : 6.0;
            // Slightly t sided maps
            case "inferno":
            case "dust 2":
                return isCt ? -2.5 : 2.5;
            // Slightly ct sided maps
            case "ancient":
            case "cache":
                return isCt ? 2.5 : -2.5;

            default: return 0.0;
        }
    }

    public double calculateWinProbability(String mapPlayed, String startingSide) {
        double winChance = 50;

        double bias = getMapSideBias(mapPlayed, startingSide);

        winChance += bias;

        int teamKills = calculateTeamKills(mapPlayed);

        winChance += (teamKills - 75) * 0.8;

        double avgClutch;
        double sum = 0;

        for (int i = 0; i < this.count; i++) {
            sum += this.roster[i].getClutchRate();
        }

        avgClutch = sum / 5;
        avgClutch = (avgClutch - 0.10) * 50.0;

        winChance += avgClutch;

        winChance = Math.round(winChance * 10.0) / 10.0;

        return winChance;

    }

    public void printScoreboard(String mapPlayed) {
        System.out.println("=== " + this.teamName + " @ " + mapPlayed + " ===");

        for (int i = 0; i < this.count; i++) {
            int numKills = this.roster[i].calculateKills(mapPlayed);
            System.out.println(roster[i].name + "    [" + roster[i].assignedRole + "] -> " +
                    numKills + " projected kills");
        }
        System.out.println("------------------------------");
        System.out.println("Team Projected Kill Total: " + calculateTeamKills(mapPlayed));
    }

    public void printMatchSummary(double winChance, String mapPlayed, String startingSide) {
        System.out.println("=== Simulation Results ===");
        System.out.println("Projected Win Probability: " + winChance + "%");
        System.out.println();

        int yourTotal = 13;
        int oppTotal = 13;

        if (winChance >= 50) {
            yourTotal = 13;
            oppTotal = 11 - (int) Math.round(((winChance - 50.0) / 40.0) * 8.0);
        } else {
            oppTotal = 13;
            yourTotal = 11 - (int) Math.round(((50.0 - winChance) / 45.0) * 8.0);
        }


        int half1;
        int half2;

        half1 = 6 + (int)(Math.round(getMapSideBias(mapPlayed, startingSide) / 3.0));

        if (winChance >= 75.0) {
            half1 += 1;
        } else if (winChance <= 40.0) {
            half1 -= 2;
        }

        if (half1 > 11) {
            half1 = 11;
        }

        if (half1 < 0) {
            half1 = 0;
        }

        if (half1 > yourTotal) {
            half1 = yourTotal;
        }

        half2 = yourTotal - half1;

        int ctRounds;
        int tRounds;

        if (startingSide.equalsIgnoreCase("CT")) {
            ctRounds = half1;
            tRounds = half2;
        } else {
            tRounds = half1;
            ctRounds = half2;
        }

        if (yourTotal == 13) {
            System.out.println(yourTotal + " : " + oppTotal + " Victory");
        } else {
            System.out.println(yourTotal + " : " + oppTotal + " Defeat");
        }

        System.out.println("CT Rounds Won: " + ctRounds);
        System.out.println("T Rounds Won: " + tRounds);

    }


}

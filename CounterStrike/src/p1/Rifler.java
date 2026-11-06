package p1;

public class Rifler extends Player {

    // Bonus kills from rifles
    private final int rifleKills;
    // Chance of getting an entry kill (0.0-1.0)
    private final double entryFragRate;

    public Rifler(String name, String secondaryRole, double clutchRate, int rifleKills, double entryFragRate) {
        super(name, "Rifler", secondaryRole, clutchRate);
        this.rifleKills = rifleKills;
        this.entryFragRate = entryFragRate;
    }

    @Override
    public int getRoleBasedKills() {

        return this.rifleKills + (int)(Math.round(this.entryFragRate * 20));

    }

    public int getRifleKills() {
        return this.rifleKills;
    }

    public double getEntryFragRate() {
        return this.entryFragRate;
    }

}

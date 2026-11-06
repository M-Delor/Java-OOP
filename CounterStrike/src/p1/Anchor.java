package p1;

public class Anchor extends Player {

    private final double holdRate;

    public Anchor(String name, String secondaryRole, double clutchRate, double holdRate) {
        super(name, "Anchor", secondaryRole, clutchRate);
        this.holdRate = holdRate;
    }

    @Override
    public int getRoleBasedKills() {

        return 10 + (int)(Math.round(holdRate * 20));
    }
}

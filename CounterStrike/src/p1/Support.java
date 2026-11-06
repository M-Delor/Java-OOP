package p1;

public class Support extends Player {

    public final int utilityDamagePerRound;

    public Support (String name, String secondaryRole, double clutchRate, int utilityDamagePerRound) {
        super(name, "Support", secondaryRole, clutchRate);
        this.utilityDamagePerRound = utilityDamagePerRound;
    }

    @Override
    public int getRoleBasedKills() {

        return 6 + (int)(Math.round(utilityDamagePerRound / 12));

    }

    public double getUtilityDamagePerRound() {
        return this.utilityDamagePerRound;
    }

}

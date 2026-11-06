package p1;

public class AWPer extends Player{

    private final int awpKills;

    public AWPer (String name, String secondaryRole, double clutchRate, int awpKills) {
        super(name, "AWPer", secondaryRole, clutchRate);
        this.awpKills = awpKills;
    }

    @Override
    public int getRoleBasedKills() {
        return 14 + awpKills;
    }

    public int getAwpKills() {
        return this.awpKills;
    }

}

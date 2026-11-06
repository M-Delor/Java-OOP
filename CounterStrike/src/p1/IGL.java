package p1;

public class IGL extends Player {

    // TAC IQ should be at a scale 1-10
    private final int tacticalIQ;

    public IGL(String name, String secondaryRole, double clutchRate, int tacticalIQ) {
        super(name,"IGL",secondaryRole, clutchRate);
        this.tacticalIQ = tacticalIQ;
    }

    @Override
    public int getRoleBasedKills() {
        if (this.assignedRole.equalsIgnoreCase("AWPer")) {
            return 21;
        } else if (tacticalIQ >= 7) {
            return 16;
        } else {
            return 11;
        }

    }

}

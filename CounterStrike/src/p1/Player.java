package p1;

abstract class Player {

    String name;
    String naturalRole;
    String secondaryRole;
    String assignedRole;
    String strongestMap;
    String weakestMap;
    Double clutchRate;
   public static final Double OUT_OF_POSITION_PENALTY = 0.75;
   public static final Double SECONDARY_ROLE_MODIFIER = 0.90;


    public Player(String name, String naturalRole, String secondaryRole, Double clutchRate) {
        this.name = name;
        this.naturalRole = naturalRole;
        this.assignedRole = naturalRole; // By Default, your role is your natural role
        this.strongestMap = "";
        this.weakestMap = "";
        this.clutchRate = clutchRate;
    }


    public Player(Player other) { // Copy Constructor
        if (other != null) {
            this.name = other.name;
            this.naturalRole = other.naturalRole;
            this.secondaryRole = other.secondaryRole;
            this.assignedRole = other.assignedRole;
            this.strongestMap = "";
            this.weakestMap = "";
            this.clutchRate = other.clutchRate;
        }
    }

    public void assignMaps(String strongest, String weakest) {
        this.strongestMap = strongest;
        this.weakestMap = weakest;
    }

    public boolean isOutOfPosition() {
        return !this.assignedRole.equalsIgnoreCase(this.naturalRole) || this.assignedRole.equalsIgnoreCase(this.secondaryRole);
         }
    
    public void displayInfo() {
    	System.out.println("Name: " + this.name + "| Natural Role: " + this.naturalRole);
    }

    public abstract int getRoleBasedKills();

    public int calculateKills(String mapPlayed) {

        int numKills = this.getRoleBasedKills();

        if (isOutOfPosition()) {
            numKills = (int)((numKills) * OUT_OF_POSITION_PENALTY);
        } else if (this.assignedRole == this.secondaryRole) {
            numKills = (int)((numKills) * SECONDARY_ROLE_MODIFIER);
        }

        if (mapPlayed != null && !mapPlayed.isEmpty()) {
            if (this.strongestMap.equalsIgnoreCase(mapPlayed)) {
                numKills += 5;
            } else if (this.weakestMap.equalsIgnoreCase(mapPlayed)) {
                numKills -= 5;
            }

        }

            numKills += (int) Math.round(this.clutchRate * 20);

            if (numKills < 0) {
                numKills = 0;
            }

        return numKills;

    }
    public boolean equals(Player other) {

        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        return this.name.equalsIgnoreCase(other.name)
                && this.naturalRole.equalsIgnoreCase(other.naturalRole)
                && this.assignedRole.equalsIgnoreCase(other.assignedRole);
    }

    @Override
    public String toString() {
        return name + "[Assigned: " + assignedRole + "| Natural: " + naturalRole + "| Clutch Chance: "
        + clutchRate + "]";
    }

    // Getters and Setters

    public String getName() {
        return this.name;
    }

    public String getNaturalRole() {
        return this.naturalRole;
    }

    public void assignRole(String assignedRole) {
        this.assignedRole = assignedRole;
    }

    public double getClutchRate() {
        return this.clutchRate;
    }

}

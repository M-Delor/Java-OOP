package p1;

public class Player extends Team {

    private String username;
    private String role;
    private double rating;

    public Player (String teamName, String username, String role, double rating) {
        super(teamName);
        this.username = username;
        this.role = role;
        this.rating = rating;
    }

    public String getUsername() {
        return this.username;
    }

    public String getRole() {
        return  this.role;
    }

    public double getRating() {
        return this.rating;
    }


}


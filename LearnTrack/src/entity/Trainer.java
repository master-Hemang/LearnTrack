package entity;

public class Trainer extends Person {
    private String[] expertise;

    public Trainer(String id, String firstName, String lastName, String email, String[] expertise) {
        super(id, firstName, lastName, email);
        this.expertise = expertise;
    }

    @Override
    public String getDisplayName() {
        return "Trainer: " + super.getDisplayName();
    }

    public String[] getExpertise() { return expertise; }
    public void setExpertise(String[] expertise) { this.expertise = expertise; }
}

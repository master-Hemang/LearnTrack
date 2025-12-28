package entity;

public class Student extends Person {
    private String batch;
    private boolean active;

    // Constructor overloading
    public Student(String id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = true;
    }

    public Student(String firstName, String lastName, String batch) {
        super(firstName, lastName);
        this.batch = batch;
        this.active = true;
    }

    // Method overriding
    @Override
    public String getDisplayName() {
        return super.getDisplayName() + " (Batch: " + batch + ")";
    }

    // Getters and Setters
    public String getBatch() { return batch; }
    public void setBatch(String batch) { this.batch = batch; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}

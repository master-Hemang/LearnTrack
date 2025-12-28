package entity;

public class Course {
    private String id;
    private String name;
    private String description;
    private int durationWeeks;
    private boolean active;

    public Course(String id, String name, String description, int durationWeeks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationWeeks = durationWeeks;
        this.active = true;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getDurationWeeks() { return durationWeeks; }
    public void setDurationWeeks(int durationWeeks) { this.durationWeeks = durationWeeks; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}

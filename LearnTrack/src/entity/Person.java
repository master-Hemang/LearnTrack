package entity;


public class Person {
    // Private fields for encapsulation
    private String id;
    private String firstName;
    private String lastName;
    private String email;


    public Person(String id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Default constructor
     */
    public Person() {
        // Default constructor for flexibility
    }

    /**
     * Method that can be overridden by subclasses (demonstrates polymorphism)
     * @return formatted display name
     */
    public String getDisplayName() {
        return firstName + " " + lastName;
    }

    // Getters and Setters (Encapsulation)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Additional method to demonstrate functionality
     * @return full name with email
     */
    public String getFullInfo() {
        return getDisplayName() + " (" + (email != null ? email : "No email") + ")";
    }
}

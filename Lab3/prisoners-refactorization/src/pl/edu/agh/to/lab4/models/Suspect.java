package pl.edu.agh.to.lab4.models;

public abstract class Suspect {
    private final String firstName;
    private final String lastName;
    private final int age;

    public Suspect(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return display();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String display() {
        return firstName + " " + lastName + " (" + age + " lat)";
    }

    public abstract Boolean canBeAccused();
}

package pl.edu.agh.to.lab4.models;


public class Person extends Suspect {
    private final String firstname;
    private final String lastname;
    private final int age;

    public Person(String firstname, String lastname, int age) {
        super(firstname, lastname, age);
        this.age = age;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return firstname;
    }

    public String getSurname() {
        return lastname;
    }

    @Override
    public Boolean canBeAccused() {
        return age > 18;
    }
}

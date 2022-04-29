package pl.edu.agh.to.lab4.models;

import java.util.Calendar;

public class Prisoner extends Suspect {
    private final int judgementYear;
    private final int senteceDuration;
    private final String pesel;
    public final String name;
    public final String surname;

    public Prisoner(String name, String surname, int age, String pesel, int judgementYear, int sentenceDuration) {
        super(name, surname, age);
        this.name = name;
        this.surname = surname;
        this.pesel = pesel;
        this.judgementYear = judgementYear;
        this.senteceDuration = sentenceDuration;
    }

    public Boolean isJailedNow() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear <= judgementYear + senteceDuration;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public Boolean canBeAccused() {
        return !isJailedNow();
    }
}

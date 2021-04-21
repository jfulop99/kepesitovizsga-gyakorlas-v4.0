package hu.nive.ujratervezes.kepesitovizsga.vaccination;

public class Person {

    private String name;
    private String postalCode;
    private int age;
    private String email;
    private String tajNumber;
    private VaccinationType lastVaccineType;

    public Person(String name, String postalCode, int age, String email, String tajNumber, VaccinationType lastVaccineType) {
        this.name = name;
        this.postalCode = postalCode;
        this.age = age;
        this.email = email;
        this.tajNumber = tajNumber;
        this.lastVaccineType = lastVaccineType;
    }

    public static Person parse(String data) {
        String[] parts = data.split(";");
        VaccinationType vaccineType = VaccinationType.NONE;
        if (parts.length == 7) {
            vaccineType = VaccinationType.valueOf(parts[6]);
        }
        return new Person(parts[1], parts[2], Integer.parseInt(parts[3]), parts[4], parts[5], vaccineType);
    }

    public String getName() {
        return name;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getTajNumber() {
        return tajNumber;
    }

    public VaccinationType getLastVaccineType() {
        return lastVaccineType;
    }
}

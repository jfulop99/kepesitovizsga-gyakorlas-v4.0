package hu.nive.ujratervezes.kepesitovizsga.applicants;

public class Applicant {

    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String email;
    private String codeNumber;
    private String skill;

    public Applicant(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Applicant(String firstName, String lastName, String skill) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.skill = skill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Applicant applicant = (Applicant) o;

        if (id != applicant.id) return false;
        if (firstName != null ? !firstName.equals(applicant.firstName) : applicant.firstName != null) return false;
        if (lastName != null ? !lastName.equals(applicant.lastName) : applicant.lastName != null) return false;
        if (gender != null ? !gender.equals(applicant.gender) : applicant.gender != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(applicant.phoneNumber) : applicant.phoneNumber != null)
            return false;
        if (email != null ? !email.equals(applicant.email) : applicant.email != null) return false;
        if (codeNumber != null ? !codeNumber.equals(applicant.codeNumber) : applicant.codeNumber != null) return false;
        return skill != null ? skill.equals(applicant.skill) : applicant.skill == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (codeNumber != null ? codeNumber.hashCode() : 0);
        result = 31 * result + (skill != null ? skill.hashCode() : 0);
        return result;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCodeNumber() {
        return codeNumber;
    }

    public String getSkill() {
        return skill;
    }
}

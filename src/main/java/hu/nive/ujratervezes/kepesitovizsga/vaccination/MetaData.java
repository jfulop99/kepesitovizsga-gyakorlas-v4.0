package hu.nive.ujratervezes.kepesitovizsga.vaccination;

import java.time.LocalDate;

public class MetaData {

    private Town town;
    private LocalDate date;

    public MetaData(Town town, LocalDate date) {
        this.town = town;
        this.date = date;
    }

    public Town getTown() {
        return town;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPostalCode() {
        return town.getPostalCode();
    }

    public String getTownName() {
        return town.getTownName();
    }
}

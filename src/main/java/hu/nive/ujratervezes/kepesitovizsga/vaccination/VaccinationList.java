package hu.nive.ujratervezes.kepesitovizsga.vaccination;


import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class VaccinationList {

    private MetaData metaData;

    private Map<LocalTime, Person> vaccinations;

    private static final Pattern NINE_DIGIT = Pattern.compile("^\\d{9}$");


    public VaccinationList() {
        vaccinations = new TreeMap<>();
    }

    public void readFromFile(BufferedReader br) throws IOException {
        String line;
        metaData = readMetadata(br);
        while ((line = br.readLine()) != null) {
            Person person = Person.parse(line);
            LocalTime time = LocalTime.parse(line.substring(0,5));
            vaccinations.put(time, person);
        }
    }

    private MetaData readMetadata(BufferedReader br) throws IOException {
        String line = br.readLine();
        String[] parts = line.split(" |,");
        line = br.readLine();
        String[] parts2 = line.split(" ");
        line = br.readLine();
        line = br.readLine();
        return new MetaData(new Town(parts[4], parts[2]), LocalDate.parse(parts2[1]));
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public Map<LocalTime, Person> getVaccinations() {
        return vaccinations;
    }

    public List<Person> getPersonsMoreThanHundredYearsOld() {
        return vaccinations.values()
                .stream()
                .filter(person -> person.getAge() > 100)
                .collect(Collectors.toList());
    }

    public List<Person> getAfternoonPersons() {
        return vaccinations.entrySet()
                .stream()
                .filter(localTimePersonEntry -> localTimePersonEntry.getKey().isAfter(LocalTime.of(12, 00)))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

    public void validateTaj() {
        String result = vaccinations.values()
                .stream()
                .filter(person -> !isValidTajNumber(person.getTajNumber()))
                .map(Person::getTajNumber).collect(Collectors.joining(", "));
        if (!result.isBlank()) {
            throw new IllegalArgumentException(result);
        }
    }

    private boolean isValidTajNumber(String tajNumber) {
        if (tajNumber == null || !NINE_DIGIT.matcher(tajNumber).find()) {
            return false;
        }
        int sum = 0;
        int multiplier = 3;
        for (int i = 0; i < 8; i++) {
            sum += (tajNumber.charAt(i) - '0') * multiplier;
            if (multiplier == 3) {
                multiplier = 7;
            } else {
                multiplier = 3;
            }
        }
        return sum % 10 == (tajNumber.charAt(8) - '0');
    }


    public String inviteExactPerson(LocalTime time) {
        return String.format("Kedves %s! Ön következik. Kérem, fáradjon be!", vaccinations.get(time).getName());
    }

    public Town getTown() {
        return metaData.getTown();
    }

    public LocalDate getDateOfVaccination() {
        return metaData.getDate();
    }

    public Map<VaccinationType, Integer> getVaccinationStatistics() {
        Map<VaccinationType, Integer> result = new HashMap<>();
        for (Person item:vaccinations.values()) {
            result.merge(item.getLastVaccineType(), 1, Integer::sum);
        }
        return result;
    }
}

package hu.nive.ujratervezes.kepesitovizsga.applicants;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListBySkills implements ApplicantListGenerator {
    @Override
    public List<Applicant> getListFromDatabase(DataSource dataSource) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("select first_name, last_name, skill from applicants where skill like '___' order by id")){
            return getResultOfQuery(ps);
        } catch (SQLException sqlException) {
            throw new IllegalStateException("Cannot query", sqlException);
        }
    }

    private List<Applicant> getResultOfQuery(PreparedStatement ps) throws SQLException {
        List<Applicant> result = new ArrayList<>();
        try (ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                result.add(new Applicant(rs.getNString("first_name"), rs.getNString("last_name"),
                        rs.getNString("skill")));
            }
            return result;
        }
    }


}

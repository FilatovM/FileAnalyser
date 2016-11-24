package mvcapp.DB_Utils.DAO;

import mvcapp.Entities.Requirement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.util.List;

public class DB_PostgresImpl implements DB_DAO {
    public void loadReqs(List<Requirement> reqs){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.postgresql.Driver.class);
        dataSource.setUrl("jdbc:postgresql://localhost:5432/NCEC");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgresfilm");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        for (Requirement req:reqs) {
            jdbcTemplate.update(
                    "INSERT INTO requirements(id, title, text, comment, done, 'time', 'date') values(?,?,?,?,?,?,?)",
                    req.getId(), req.getTitle(), req.getText(), req.getComment(), req.isDone(), req.getTime(), req.getDate());
        }
    }
}

package by.byshnev.springmvc.dao;


import by.byshnev.springmvc.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Person person = new Person();

        person.setName(resultSet.getString("name"));
        person.setAge(resultSet.getInt("age"));
        person.setId(resultSet.getInt("id"));
        person.setEmail(resultSet.getString("email"));

        return person;
    }
}
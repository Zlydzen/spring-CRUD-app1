package by.byshnev.springmvc.dao;

import by.byshnev.springmvc.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";    //не обязательно
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person();

                //Hibernate это делает автоматически
                int id = resultSet.getInt("id");
                person.setId(id);
                String name = resultSet.getString("name");
                person.setName(name);
                int age = resultSet.getInt("age");
                person.setAge(age);
                String email = resultSet.getString("email");
                person.setEmail(email);

                people.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return people;
    }

//    public Person show(int id) {
//        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
//    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

//    public void update(int i, Person updatePerson) {
//        Person personToBeUpdated = show(i);
//        personToBeUpdated.setName(updatePerson.getName());
//        personToBeUpdated.setAge(updatePerson.getAge());
//        personToBeUpdated.setEmail(updatePerson.getEmail());
//    }

    public void delete(int i) {
        people.removeIf(person -> person.getId() == i);
    }

}

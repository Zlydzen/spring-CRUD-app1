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
            String SQL = "SELECT * FROM Person";
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

    public Person show(int id) {
        Person person = null; // такое объявление обходит стэк блока try, для того чтобы вернуть person

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Person WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next(); //из-за того, что у нас все люди имеют id 1, то эта строка нам вернет первого
                              //встретившегося из БД с id 1;

            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return person;
    }

    public void save(Person person) {

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Person VALUES(1,?,?,?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2,person.getAge());
            preparedStatement.setString(3, person.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Person updatePerson) {

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Peson SET name=?,age=?,email=? WHERE id=?");
            preparedStatement.setString(1, updatePerson.getName());
            preparedStatement.setInt(2,updatePerson.getAge());
            preparedStatement.setString(3, updatePerson.getEmail());
            preparedStatement.setInt(4,id); //так как у нас id 1 для всех person, то БД перезапишет всех person значениями

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void delete(int i) {
    }

}

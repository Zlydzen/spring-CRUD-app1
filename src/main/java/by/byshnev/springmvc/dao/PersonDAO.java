package by.byshnev.springmvc.dao;

import by.byshnev.springmvc.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> people;
    private static int PEOPLE_COUNT;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Mikita Byshniou",32,"nick@gmail.com"));   //++PEOPLE_COUNT == 1;
        people.add(new Person(++PEOPLE_COUNT, "Alan Alberneti",44, "alan.a@outlook.com"));     //++PEOPLE_COUNT == 2; etc.
        people.add(new Person(++PEOPLE_COUNT, "Jhon Silver", 89,"goldman@yahoo.com"));
        people.add(new Person(++PEOPLE_COUNT, "Ken husband Barbie",28,"ken.barbie@pups.com"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int i, Person updatePerson) {
        Person personToBeUpdated = show(i);
        personToBeUpdated.setName(updatePerson.getName());
        personToBeUpdated.setAge(updatePerson.getAge());
        personToBeUpdated.setEmail(updatePerson.getEmail());
    }

    public void delete(int i) {
        people.removeIf(person -> person.getId() == i);
    }

}

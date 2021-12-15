package by.byshnev.springmvc.dao;

import by.byshnev.springmvc.models.Person;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
    private List<Person> people;
    private static int PEOPLE_COUNT;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_COUNT, "Mikita Byshniou"));   //++PEOPLE_COUNT == 1;
        people.add(new Person(++PEOPLE_COUNT,"Alan Alberneti"));     //++PEOPLE_COUNT == 2; etc.
        people.add(new Person(++PEOPLE_COUNT,"Jhon Silver"));
        people.add(new Person(++PEOPLE_COUNT,"Ken husband Barbie"));
    }

}

package by.byshnev.springmvc.controllers;

import by.byshnev.springmvc.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model){
        // получим всех людей из DAO и передадим на отображение в VIEW
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@RequestParam("id") int id, Model model){
        // получим одного человека по id из DAO и передадим на отображение в VEW
        model.addAttribute("person",personDAO.show(id));
        return "people/show";
    }

}

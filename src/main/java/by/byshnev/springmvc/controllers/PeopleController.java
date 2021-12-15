package by.byshnev.springmvc.controllers;

import by.byshnev.springmvc.dao.PersonDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @GetMapping
    public String index(Model model){
        // получим всех людей из DAO и передадим на отображение в VIEW
        return null;
    }

    @GetMapping("/{id}")
    public String show(@RequestParam("id") int id, Model model){
        // получим одного человека по id из DAO и передадим на отображение в VEW
        return null ;
    }

}

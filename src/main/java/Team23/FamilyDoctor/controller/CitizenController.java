package Team23.FamilyDoctor.controller;

import Team23.FamilyDoctor.entity.*;
import Team23.FamilyDoctor.dao.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("citizen")
public class CitizenController {

    @Autowired(required = false)
    private CitizenDAO citizenDao;

    @GetMapping("")
    public String showCitizens(Model model) {
        model.addAttribute("citizens", citizenDao.getCitizens());
        return "citizens";
    }

    @GetMapping("/new")
    public String addCitizen(Model model) {
        Citizen citizen = new Citizen();
        model.addAttribute("citizen", citizen);

        return "add_citizen";

    }

    @GetMapping("{citizen_id}")
    public String editCitizen(@PathVariable Integer citizen_id, Model model) {
        Citizen citizen = citizenDao.getCitizen(citizen_id);
        model.addAttribute("citizen", citizen);
        return "add_citizen";

    }

    @PostMapping("/new")
    public String saveCitizen(@ModelAttribute("citizen") Citizen citizen, Model model) {
        citizenDao.saveCitizen(citizen);
        model.addAttribute("citizens", citizenDao.getCitizens());
        return "citizens";
    }

    @DeleteMapping("{citizen_id}")
    public String deleteCitizen(@PathVariable Integer citizen_id) {
        citizenDao.deleteCitizen(citizen_id);
        return "citizens";
    }


}
package Team23.FamilyDoctor.controller;

import Team23.FamilyDoctor.entity.Citizen;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("citizen")
public class CitizenController {

    private List<Citizen> citizens = new ArrayList<Citizen>();

    @PostConstruct
    public void setup() {
        Citizen res1= new Citizen( "James", "Bulk", "6912345678", "10101012345");
        Citizen res2= new Citizen( "John", "Doe", "6987654321","11111112345");
        Citizen res3= new Citizen("El Pedo", "Bailando", "6912341234","12121212345");
        citizens.add(res1);
        citizens.add(res2);
        citizens.add(res3);
    }

    @GetMapping("")
    public String showCitizens(Model model){
        model.addAttribute("citizens", citizens);
        return "citizens";
    }
    @GetMapping(    "/{id}")
    public String showCitizen(@PathVariable Integer id, Model model){
        Citizen citizen = citizens.get(id);
        model.addAttribute("citizens", citizen);
        return "citizens";
    }

    @GetMapping("/new")
    public String addCitizen(Model model){
        Citizen citizen = new Citizen();
        model.addAttribute("citizen", citizen);

        return "add_citizen";

    }

    @PostMapping("/new")
    public String saverCitizen(@ModelAttribute("citizen") Citizen citizen, Model model) {
        System.out.println(citizen);
        System.out.println(citizens);
        citizens.add(citizen);
        model.addAttribute("citizens", citizens);
        return "citizens";
    }
}

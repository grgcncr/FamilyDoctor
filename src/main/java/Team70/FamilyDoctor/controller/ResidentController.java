package Team70.FamilyDoctor.controller;

import Team70.FamilyDoctor.entity.Resident;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("resident")
public class ResidentController {

    private List<Resident> residents = new ArrayList<Resident>();

    @PostConstruct
    public void setup() {
        Resident res1= new Resident(1, "James", "Bulk", "james.bulk@hua.gr");
        Resident res2= new Resident(2, "John", "Doe", "john.doe@hua.gr");
        Resident res3= new Resident(2,"El Pedo", "Bailando", "pickle.head@hua.gr");
        residents.add(res1);
        residents.add(res2);
        residents.add(res3);
    }

    @GetMapping("")
    public String showResidents(Model model){
        model.addAttribute("residents", residents);
        return "residents";
    }
    @GetMapping("/{id}")
    public String showResident(@PathVariable Integer id, Model model){
        Resident resident = residents.get(id);
        model.addAttribute("residents", resident);
        return "residents";
    }

    @GetMapping("/new")
    public String addResident(Model model){
        Resident resident = new Resident();
        model.addAttribute("resident", resident);

        return "add_resident";

    }

    @PostMapping("/new")
    public String saverResident(@ModelAttribute("resident") Resident resident, Model model) {
        System.out.println(resident);
        System.out.println(residents);
        residents.add(resident);
        model.addAttribute("residents", residents);
        return "residents";
    }
}

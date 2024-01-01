package Team23.FamilyDoctor.controller;

import Team23.FamilyDoctor.entity.*;
import Team23.FamilyDoctor.dao.*;
import Team23.FamilyDoctor.service.*;
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

//    private static List<Citizen> citizens = new ArrayList<Citizen>();
//
//    @PostConstruct
//    public void setup() {
//        Citizen x1= new Citizen( "Nick", "Jones", "6912345678","10100100123");
//        Citizen x2= new Citizen( "Jack", "James", "6912121212", "10100100124");
//        Citizen x3= new Citizen("John", "Stone", "6923232323","10100100125");
//        citizens.add(x1);
//        citizens.add(x2);
//        citizens.add(x3);
//        saveCitizen(x1,Model);
//    }
//public static List<Citizen> getCitizens() {
//    return citizens;
//}

//    @PostConstruct
//    public void setup() {
//        Doctor doctor = doctorDao.getDoctor(1);
//        Citizen citizen = citizenDao.getCitizen(1);
//        Request request = new Request("12/12/2023","ACCEPTED",citizen,doctor);
//    }


    @Autowired
    RequestService requestService;
    @Autowired
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
    @GetMapping("{citizen_id}/delete")
    public String deleteCitizen(@PathVariable Integer citizen_id) {
        citizenDao.deleteCitizen(citizen_id);
        return "redirect:/citizen";
    }


// Handle Citizen request

    @PostMapping("{citizen_id}/request/new")
    public String saveCitizenRequest(@PathVariable int citizen_id, @ModelAttribute("request") Request request) {
        System.out.println("citizen_id: (reg)" + citizen_id);
        System.out.println("request: (reg)" + request.getReqDate());
        requestService.saveCitizenRequest(request, citizen_id);
        return "redirect:/citizen";
    }

    @GetMapping("{citizen_id}/request")
    public String showCitizenRequests(@PathVariable int citizen_id, Model model) {
        Citizen citizen = citizenDao.getCitizen(citizen_id);
        List<Request> requests = citizen.getRequests();
        if (!requests.isEmpty()) {
            System.out.println(requests);
        }
        model.addAttribute("citizen", citizen);
        model.addAttribute("request", requests);
        return "requests";
    }

    @GetMapping("{citizen_id}/request/{request_id}")
    public String editCitizenRequest(@PathVariable int citizen_id, @PathVariable int request_id, Model model) {
        Request request = requestService.getRequest(request_id);
        model.addAttribute("request", request);
        model.addAttribute("citizen_id", citizen_id);
        return "add_request";
    }

    @PostMapping("{citizen_id}/request/{request_id}")
    public String updateCitizenRequest(@PathVariable int citizen_id, @PathVariable int request_id) {
        System.out.println("citizen_id: (2 ids)" + citizen_id);
        Request request = requestService.getRequest(request_id);
//        requestService.deleteRequest(request_id);
//        requestService.saveCitizenRequest(request, citizen_id);
        return "redirect:/citizen" ;
    }

    @GetMapping("{citizen_id}/request/new")
    public String addCitizenRequest(@PathVariable int citizen_id, Model model) {
        Request request = new Request();
        model.addAttribute("request", request);
        model.addAttribute("citizen_id", citizen_id);
        return "add_request";
    }

    //deletes mapping
    @GetMapping("{citizen_id}/request/{request_id}/delete")
    public String deleteCitizenRequest(@PathVariable int citizen_id, @PathVariable int request_id, Model model) {
        requestService.deleteRequest(request_id);
        return "redirect:/citizen/{citizen_id}/request";
    }



}
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
        request.setStatus("PENDING");
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
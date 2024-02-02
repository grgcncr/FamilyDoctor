package Team23.FamilyDoctor.controller;

import Team23.FamilyDoctor.dao.CitizenDAO;
import Team23.FamilyDoctor.entity.Citizen;
import Team23.FamilyDoctor.entity.Request;
import Team23.FamilyDoctor.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/citizen")
public class CitizenRestController {

    @Autowired
    private CitizenDAO citizenDao;
    @Autowired
    RequestService requestService;

//
//    @Transactional
//    @GetMapping("")
//    public List<Citizen> fetchCitizens() {
//        System.out.println("Fetching citizens...");
//        List<Citizen> citizens = citizenDao.getCitizens();
//        System.out.println("Citizens fetched: " + citizens);
//        return citizens;
//    }

    @GetMapping("")
    public List<Citizen> showCitizens() {
        return citizenDao.getCitizens();
    }

//    @GetMapping("/new")
//    public String addCitizen(Model model) {
//        Citizen citizen = new Citizen();
//        model.addAttribute("citizen", citizen);
//        return "add_citizen";
//    }

//    @Secured("ROLE_ADMIN")
//    @GetMapping("{citizen_id}")
//    public String editCitizen(@PathVariable Integer citizen_id, Model model) {
//        Citizen citizen = citizenDao.getCitizen(citizen_id);
//        model.addAttribute("citizen", citizen);
//        return "add_citizen";
//    }

    @PostMapping("/new")
    public void saveCitizen(@ModelAttribute("citizen") Citizen citizen, Model model) {
        citizenDao.saveCitizen(citizen);
        for (Request request : citizen.getRequests()) {
            requestService.saveCitizenRequest(request, citizen.getId());
        }
        model.addAttribute("citizens", citizenDao.getCitizens());
    }

    //    @Secured("ROLE_ADMIN")
    @GetMapping("{citizen_id}/delete")
    public void deleteCitizen(@PathVariable Integer citizen_id) {
        Citizen citizen = citizenDao.getCitizen(citizen_id);
        for (Request request : citizen.getRequests()) {
            requestService.deleteRequest(request.getId());
        }
        citizenDao.deleteCitizen(citizen_id);
    }

// Handle Citizen request
//
//    @PostMapping("{citizen_id}/request/new")
//    public String saveCitizenRequest(@PathVariable int citizen_id, @ModelAttribute("request") Request request) {
//        System.out.println("citizen_id: (reg)" + citizen_id);
//        System.out.println("request: (reg)" + request.getReqDate());
//        requestService.saveCitizenRequest(request, citizen_id);
//        return "redirect:/citizen/{citizen_id}/request";
//    }
//
//    @GetMapping("{citizen_id}/request")
//    public String showCitizenRequests(@PathVariable int citizen_id, Model model) {
//        Citizen citizen = citizenDao.getCitizen(citizen_id);
//        List<Request> requests = citizen.getRequests();
//        if (!requests.isEmpty()) {
//            System.out.println(requests);
//        }
//        model.addAttribute("citizen", citizen);
//        model.addAttribute("request", requests);
//        return "requests";
//    }
//
//    @PostMapping("{citizen_id}/request/{request_id}")
//    public String updateCitizenRequest(@PathVariable int citizen_id, @PathVariable int request_id) {
//        System.out.println("citizen_id: (2 ids)" + citizen_id);
//        Request request = requestService.getRequest(request_id);
//        return "redirect:/citizen/{citizen_id}/request";
//    }
//
//    //Deletes Mapping
//    @Secured("ROLE_ADMIN")
//    @GetMapping("{citizen_id}/request/{request_id}/delete")
//    public String deleteCitizenRequest(@PathVariable int citizen_id, @PathVariable int request_id, Model model) {
//        requestService.deleteRequest(request_id);
//        return "redirect:/citizen/{citizen_id}/request";
//    }
//
//    //accepts request
//    @GetMapping("{citizen_id}/request/{request_id}/accept")
//    public String acceptRequest(@PathVariable int citizen_id, @PathVariable int request_id, Model model) {
//        requestService.acceptRequest(request_id);
//        return "redirect:/citizen/{citizen_id}/request";
//    }
//
//    //declines request
//    @GetMapping("{citizen_id}/request/{request_id}/decline")
//    public String declineRequest(@PathVariable int citizen_id, @PathVariable int request_id, Model model) {
//        requestService.declineRequest(request_id);
//        return "redirect:/citizen/{citizen_id}/request";
//    }


}

package Team23.FamilyDoctor.controller;

import Team23.FamilyDoctor.entity.*;
import Team23.FamilyDoctor.dao.*;
import Team23.FamilyDoctor.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("citizen")
public class RequestController {



    @Autowired
    RequestService requestService;

    @Autowired
    CitizenDAO citizenDAO;

    @Autowired
    DoctorDAO doctorDAO;


    @PostMapping("{citizen_id}/requests/new")
    public String saveCitizenRequest(@PathVariable int citizen_id, @ModelAttribute("request") Request request) {
        System.out.println("citizen_id: (reg)" + citizen_id);
        System.out.println("request: (reg)" + request.getReqDate());
        requestService.saveCitizenRequest(request, citizen_id);
        return "redirect:/citizen" + citizen_id;
    }

    @GetMapping("{citizen_id}/requests")
    public String showCitizenRequests(@PathVariable int citizen_id, Model model) {
        Citizen citizen = citizenDAO.getCitizen(citizen_id);
        List<Request> requests = citizen.getRequests();
        System.out.println(requests);
        model.addAttribute("citizen", citizen);
        model.addAttribute("requests", requests);
        return "requests";
    }

    @GetMapping("/citizen/{citizen_id}/requests/{request_id}")
    public String editCitizenRequest(@PathVariable int citizen_id, @PathVariable int request_id, Model model) {
        Request request = requestService.getRequest(request_id);
        model.addAttribute("request", request);
        model.addAttribute("citizen_id", citizen_id);
        return "add_request";
    }

    @PostMapping("/citizen/{citizen_id}/requests/{request_id}")
    public String updateCitizenRequest(@PathVariable int citizen_id, @PathVariable int request_id) {
        System.out.println("citizen_id: (2 ids)" + citizen_id);
        Request request = requestService.getRequest(request_id);
        requestService.saveCitizenRequest(request, citizen_id);
        return "redirect:/citizen" + citizen_id;
    }

    @GetMapping("/citizen/request/{citizen_id}/new")
    public String addCitizenRequest(@PathVariable int citizen_id, Model model) {
        Request request = new Request();
        model.addAttribute("request", request);
        model.addAttribute("citizen_id", citizen_id);
        return "add_request";
    }

    @DeleteMapping("/citizen/request/{citizen_id}/{request_id}")
    public String deleteCitizenRequest(@PathVariable int citizen_id, @PathVariable int request_id, Model model) {
        requestService.deleteRequest(request_id);
        return "redirect:/home";
    }

    // Doctor Requests

    @PostMapping("/doctor/{doctor_id}")
    public String saveDoctorRequest(@PathVariable int doctor_id, @ModelAttribute("request") Request request) {
        System.out.println("doctor_id: (reg)" + doctor_id);
        System.out.println("request: (reg)" + request.getReqDate());
        requestService.saveDoctorRequest(request, doctor_id);
        return "redirect:/request/doctor/" + doctor_id;
    }

    @GetMapping("/doctor/{doctor_id}")
    public String showDoctorRequests(@PathVariable int doctor_id, Model model) {
        Doctor doctor = doctorDAO.getDoctor(doctor_id);
        List<Request> requests = doctor.getRequests();
        System.out.println(requests);
        model.addAttribute("doctor", doctor);
        model.addAttribute("requests", requests);
        return "requests";
    }

    @GetMapping("/doctor/{doctor_id}/{request_id}")
    public String editDoctorRequest(@PathVariable int doctor_id, @PathVariable int request_id, Model model) {
        Request request = requestService.getRequest(request_id);
        model.addAttribute("request", request);
        model.addAttribute("doctor_id", doctor_id);
        return "add_request";
    }


    @PostMapping("/doctor/{doctor_id}/{request_id}")
    public String updateDoctorRequest(@PathVariable int doctor_id, @PathVariable int request_id) {
        System.out.println("doctor_id: (2 ids)" + doctor_id);
        Request request = requestService.getRequest(request_id);
        requestService.saveDoctorRequest(request, doctor_id);
        return "redirect:/request/doctor/" + doctor_id;
    }

    @GetMapping("/doctor/{doctor_id}/new")
    public String addDoctorRequest(@PathVariable int doctor_id, Model model) {
        Request request = new Request();
        model.addAttribute("request", request);
        model.addAttribute("doctor_id", doctor_id);
        return "add_request";
    }

    @DeleteMapping("/doctor/{doctor_id}/{request_id}")
    public String deleteDoctorRequest(@PathVariable int doctor_id, @PathVariable int request_id, Model model) {
        requestService.deleteRequest(request_id);
        return "redirect:/home";
    }

}

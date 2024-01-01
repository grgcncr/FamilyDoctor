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
@RequestMapping("doctor")
public class DoctorController {


    @Autowired
    RequestService requestService;
    @Autowired
    private DoctorDAO doctorDao;

    @GetMapping("")
    public String showDoctors(Model model) {
        model.addAttribute("doctors", doctorDao.getDoctors());
        return "doctors";
    }

    @GetMapping("/new")
    public String addDoctor(Model model) {
        Doctor doctor = new Doctor();
        model.addAttribute("doctor", doctor);
        return "add_doctor";
    }

    @GetMapping("{doctor_id}")
    public String editDoctor(@PathVariable Integer doctor_id, Model model) {
        Doctor doctor = doctorDao.getDoctor(doctor_id);
        model.addAttribute("doctor", doctor);
        return "add_doctor";
    }

    @PostMapping("/new")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor, Model model) {
        doctorDao.saveDoctor(doctor);
        model.addAttribute("doctors", doctorDao.getDoctors());
        return "doctors";
    }
    @GetMapping("{doctor_id}/delete")
    public String deleteDoctor(@PathVariable Integer doctor_id) {
        doctorDao.deleteDoctor(doctor_id);
        return "redirect:/doctor";
    }


// Handle Doctor request

    @PostMapping("{doctor_id}/request/new")
    public String saveDoctorRequest(@PathVariable int doctor_id, @ModelAttribute("request") Request request) {
        System.out.println("doctor_id: (reg)" + doctor_id);
        System.out.println("request: (reg)" + request.getReqDate());
        requestService.saveDoctorRequest(request, doctor_id);
        return "redirect:/doctor/{doctor_id}/request";
    }

    @GetMapping("{doctor_id}/request")
    public String showDoctorRequests(@PathVariable int doctor_id, Model model) {
        Doctor doctor = doctorDao.getDoctor(doctor_id);
        List<Request> requests = doctor.getRequests();
        if (!requests.isEmpty()) {
            System.out.println(requests);
        }
        model.addAttribute("doctor", doctor);
        model.addAttribute("request", requests);
        return "requests";
    }

    @GetMapping("{doctor_id}/request/{request_id}")
    public String editDoctorRequest(@PathVariable int doctor_id, @PathVariable int request_id, Model model) {
        Request request = requestService.getRequest(request_id);
        model.addAttribute("request", request);
        model.addAttribute("doctor_id", doctor_id);
        return "add_doctor_request";
    }

    @PostMapping("{doctor_id}/request/{request_id}")
    public String updateDoctorRequest(@PathVariable int doctor_id, @PathVariable int request_id) {
        System.out.println("doctor_id: (2 ids)" + doctor_id);
        Request request = requestService.getRequest(request_id);
//        requestService.deleteRequest(request_id);
//        requestService.saveDoctorRequest(request, doctor_id);
        return "redirect:/doctor/{doctor_id}/request" ;
    }

    @GetMapping("{doctor_id}/request/new")
    public String addDoctorRequest(@PathVariable int doctor_id, Model model) {
        Request request = new Request();
        model.addAttribute("request", request);
        model.addAttribute("doctor_id", doctor_id);
        return "add_doctor_request";
    }

    //deletes mapping
    @GetMapping("{doctor_id}/request/{request_id}/delete")
    public String deleteDoctorRequest(@PathVariable int doctor_id, @PathVariable int request_id, Model model) {
        requestService.deleteRequest(request_id);
        return "redirect:/doctor/{doctor_id}/request";
    }

    //accepts request
    @GetMapping("{doctor_id}/request/{request_id}/accept")
    public String acceptRequest(@PathVariable int doctor_id, @PathVariable int request_id, Model model) {
        requestService.acceptRequest(request_id);
//        requestService.getRequest(request_id).setStatus("ACCEPTED");
        return "redirect:/doctor/{doctor_id}/request";
    }

    //declines request
    @GetMapping("{doctor_id}/request/{request_id}/decline")
    public String declineRequest(@PathVariable int doctor_id, @PathVariable int request_id, Model model) {
        requestService.declineRequest(request_id);
//        requestService.getRequest(request_id).setStatus("DECLINED");
        return "redirect:/doctor/{doctor_id}/request";
    }


}
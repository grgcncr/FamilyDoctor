package Team23.FamilyDoctor.controller;

import Team23.FamilyDoctor.entity.*;
import Team23.FamilyDoctor.dao.*;
import Team23.FamilyDoctor.service.RequestService;
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
//    private static List<Doctor> doctors = new ArrayList<Doctor>();
//    @PostConstruct
//    public void setup() {
//        Doctor x1= new Doctor( "Jakdsyen", "Tist", "6912345670");
//        Doctor x2= new Doctor( "James", "Bulk", "6912121210");
//        Doctor x3= new Doctor("El Pedo", "Bailando", "6923232320");
//        doctors.add(x1);
//        doctors.add(x2);
//        doctors.add(x3);
//    }
//    public static List<Doctor> getDoctors() {
//        return doctors;
//    }
    @Autowired
    private DoctorDAO doctorDao;

    @Autowired
    RequestService requestService;

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

    @DeleteMapping("{doctor_id}")
    public String deleteDoctor(@PathVariable Integer doctor_id) {
        doctorDao.deleteDoctor(doctor_id);
        return "doctors";
    }


    // Handle Requests

    @PostMapping("{doctor_id}/request/new")
    public String saveDoctorRequest(@PathVariable int doctor_id, @ModelAttribute("request") Request request) {
        System.out.println("doctor_id: (reg)" + doctor_id);
        System.out.println("request: (reg)" + request.getReqDate());
        requestService.saveDoctorRequest(request, doctor_id);
        return "redirect:/doctor";
    }

    @GetMapping("{doctor_id}/request")
    public String showDoctorRequests(@PathVariable int doctor_id, Model model) {
        Doctor doctor = doctorDao.getDoctor(doctor_id);
        List<Request> request = doctor.getRequests();
        System.out.println(request);
        model.addAttribute("doctor", doctor);
        model.addAttribute("request", request);
        return "requests";
    }

    @GetMapping("{doctor_id}/request/{request_id}")
    public String editDoctorRequest(@PathVariable int doctor_id, @PathVariable int request_id, Model model) {
        Request request = requestService.getRequest(request_id);
        model.addAttribute("request", request);
        model.addAttribute("doctor_id", doctor_id);
        return "add_request";
    }

    @PostMapping("{doctor_id}/request/{request_id}")
    public String updateDoctorRequest(@PathVariable int doctor_id, @PathVariable int request_id) {
        System.out.println("doctor_id: (2 ids)" + doctor_id);
        Request request = requestService.getRequest(request_id);
        requestService.saveDoctorRequest(request, doctor_id);
        return "redirect:/doctor" ;
    }

    @GetMapping("{doctor_id}/request/new")
    public String addDoctorRequest(@PathVariable int doctor_id, Model model) {
        Request request = new Request();
        model.addAttribute("request", request);
        model.addAttribute("doctor_id", doctor_id);
        return "add_request";
    }

    @DeleteMapping("{doctor_id}/request/{request_id}")
    public String deleteDoctorRequest(@PathVariable int doctor_id, @PathVariable int request_id, Model model) {
        requestService.deleteRequest(request_id);
        return "redirect:/home";
    }


}
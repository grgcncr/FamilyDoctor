package Team23.FamilyDoctor.controller;

import Team23.FamilyDoctor.dao.DoctorDAO;
import Team23.FamilyDoctor.entity.Doctor;
import Team23.FamilyDoctor.entity.Request;
import Team23.FamilyDoctor.repository.DoctorRepository;
import Team23.FamilyDoctor.service.RequestService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/doctor")
public class DoctorRestController {

    @Autowired
    private DoctorDAO doctorDao;
    @Autowired
    RequestService requestService;

//
//    @Transactional
//    @GetMapping("")
//    public List<Doctor> fetchDoctors() {
//        System.out.println("Fetching doctors...");
//        List<Doctor> doctors = doctorDao.getDoctors();
//        System.out.println("Doctors fetched: " + doctors);
//        return doctors;
//    }

    @GetMapping("")
    public List<Doctor> showDoctors() {
        return doctorDao.getDoctors();
    }

//    @GetMapping("/new")
//    public String addDoctor(Model model) {
//        Doctor doctor = new Doctor();
//        model.addAttribute("doctor", doctor);
//        return "add_doctor";
//    }

//    @Secured("ROLE_ADMIN")
//    @GetMapping("{doctor_id}")
//    public String editDoctor(@PathVariable Integer doctor_id, Model model) {
//        Doctor doctor = doctorDao.getDoctor(doctor_id);
//        model.addAttribute("doctor", doctor);
//        return "add_doctor";
//    }

    @PostMapping("/new")
    public void saveDoctor(@ModelAttribute("doctor") Doctor doctor, Model model) {
        doctorDao.saveDoctor(doctor);
        for (Request request : doctor.getRequests()) {
            requestService.saveCitizenRequest(request, doctor.getId());
        }
        model.addAttribute("doctors", doctorDao.getDoctors());
    }

//    @Secured("ROLE_ADMIN")
    @GetMapping("{doctor_id}/delete")
    public void deleteDoctor(@PathVariable Integer doctor_id) {
        Doctor doctor = doctorDao.getDoctor(doctor_id);
        for (Request request : doctor.getRequests()) {
            requestService.deleteRequest(request.getId());
        }
        doctorDao.deleteDoctor(doctor_id);
    }

// Handle Doctor request
//
//    @PostMapping("{doctor_id}/request/new")
//    public String saveDoctorRequest(@PathVariable int doctor_id, @ModelAttribute("request") Request request) {
//        System.out.println("doctor_id: (reg)" + doctor_id);
//        System.out.println("request: (reg)" + request.getReqDate());
//        requestService.saveDoctorRequest(request, doctor_id);
//        return "redirect:/doctor/{doctor_id}/request";
//    }
//
//    @GetMapping("{doctor_id}/request")
//    public String showDoctorRequests(@PathVariable int doctor_id, Model model) {
//        Doctor doctor = doctorDao.getDoctor(doctor_id);
//        List<Request> requests = doctor.getRequests();
//        if (!requests.isEmpty()) {
//            System.out.println(requests);
//        }
//        model.addAttribute("doctor", doctor);
//        model.addAttribute("request", requests);
//        return "requests";
//    }
//
//    @PostMapping("{doctor_id}/request/{request_id}")
//    public String updateDoctorRequest(@PathVariable int doctor_id, @PathVariable int request_id) {
//        System.out.println("doctor_id: (2 ids)" + doctor_id);
//        Request request = requestService.getRequest(request_id);
//        return "redirect:/doctor/{doctor_id}/request";
//    }
//
//    //Deletes Mapping
//    @Secured("ROLE_ADMIN")
//    @GetMapping("{doctor_id}/request/{request_id}/delete")
//    public String deleteDoctorRequest(@PathVariable int doctor_id, @PathVariable int request_id, Model model) {
//        requestService.deleteRequest(request_id);
//        return "redirect:/doctor/{doctor_id}/request";
//    }
//
//    //accepts request
//    @GetMapping("{doctor_id}/request/{request_id}/accept")
//    public String acceptRequest(@PathVariable int doctor_id, @PathVariable int request_id, Model model) {
//        requestService.acceptRequest(request_id);
//        return "redirect:/doctor/{doctor_id}/request";
//    }
//
//    //declines request
//    @GetMapping("{doctor_id}/request/{request_id}/decline")
//    public String declineRequest(@PathVariable int doctor_id, @PathVariable int request_id, Model model) {
//        requestService.declineRequest(request_id);
//        return "redirect:/doctor/{doctor_id}/request";
//    }


}

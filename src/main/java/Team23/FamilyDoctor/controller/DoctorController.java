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
@RequestMapping("doctor")
public class DoctorController {

    @Autowired(required = false)
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

    @DeleteMapping("{doctor_id}")
    public String deleteDoctor(@PathVariable Integer doctor_id) {
        doctorDao.deleteDoctor(doctor_id);
        return "doctors";
    }


}
package Team23.FamilyDoctor.service;

import Team23.FamilyDoctor.entity.*;
import Team23.FamilyDoctor.dao.*;
import Team23.FamilyDoctor.repository.RequestRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//import static Team23.FamilyDoctor.controller.CitizenController.getCitizens;
//import static Team23.FamilyDoctor.controller.DoctorController.getDoctors;

@Service
public class RequestService {
//    private List<Request> requests = new ArrayList<Request>();
//    @PostConstruct
//    public void setup() {
//        Request x1= new Request("25/12/2023","ACCEPTED", getCitizens().get(1), getDoctors().get(1));
//        requests.add(x1);
//    }
//
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private CitizenDAO citizenDAO;
    @Autowired
    private DoctorDAO doctorDAO;

    @Transactional
    public void saveCitizenRequest(Request request, Integer citizen_id) {
        Citizen citizen = citizenDAO.getCitizen(citizen_id);
        request.setCitizen(citizen);
        citizen.getRequests().add(request);
        requestRepository.save(request);
    }

    @Transactional
    public void saveDoctorRequest(Request request, Integer doctor_id) {
        Doctor doctor = doctorDAO.getDoctor(doctor_id);
        request.setDoctor(doctor);
        requestRepository.save(request);
    }


    @Transactional
    public List<Request> getCitizenRequests(Integer citizen_id){
        Citizen citizen = citizenDAO.getCitizen(citizen_id);
        return citizen.getRequests();
    }

    @Transactional
    public List<Request> getDoctorRequests(Integer doctor_id){
        Doctor doctor = doctorDAO.getDoctor(doctor_id);
        return doctor.getRequests();
    }
    @Transactional
    public Request getRequest(int requestId) {
        return requestRepository.findById(requestId).get();
    }

    public void deleteRequest(int requestId) {
        requestRepository.deleteById(requestId);
    }
}
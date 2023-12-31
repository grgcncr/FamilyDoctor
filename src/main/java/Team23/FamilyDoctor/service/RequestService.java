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

@Service
public class RequestService {

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
        doctor.getRequests().add(request);
        requestRepository.save(request);
    }

    @Transactional
    public void saveRequest(Request request,Integer citizen_id, Integer doctor_id) {
        Citizen citizen = citizenDAO.getCitizen(citizen_id);
        request.setCitizen(citizen);
        citizen.getRequests().add(request);
        Doctor doctor = doctorDAO.getDoctor(doctor_id);
        request.setDoctor(doctor);
        doctor.getRequests().add(request);
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
    public void acceptRequest(Integer request_id) {
        requestRepository.findById(request_id).get().setStatus("ACCEPTED");
        requestRepository.save(requestRepository.findById(request_id).get());
    }

    @Transactional
    public void declineRequest(Integer request_id) {
        requestRepository.findById(request_id).get().setStatus("DECLINED");
        requestRepository.save(requestRepository.findById(request_id).get());
    }

    @Transactional
    public Request getRequest(int requestId) {
        return requestRepository.findById(requestId).get();
    }

    @Transactional
    public void deleteRequest(int requestId) {
        requestRepository.deleteById(requestId);
    }
}
package Team23.FamilyDoctor.service;

import Team23.FamilyDoctor.entity.*;
import Team23.FamilyDoctor.dao.*;
import Team23.FamilyDoctor.repository.RequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
    @Autowired(required = false)
    private RequestRepository requestRepository;
    @Autowired(required = false)
    private CitizenDAO citizenDAO;
    @Autowired(required = false)
    private DoctorDAO doctorDAO;

    @Transactional
    public void saveCitizenRequest(Request request, Integer citizen_id) {
        Citizen citizen = citizenDAO.getCitizen(citizen_id);
        request.setCitizen(citizen);
        requestRepository.save(request);
    }

    @Transactional
    public void saveDoctorRequest(Request request, Integer doctor_id) {
        Doctor doctor = doctorDAO.getDoctor(doctor_id);
        request.setDoctor(doctor);
        requestRepository.save(request);
    }

    @Transactional
    public Request getRequest(int requestId) {
        return requestRepository.findById(requestId).get();
    }

    public void deleteRequest(int requestId) {
        requestRepository.deleteById(requestId);
    }
}
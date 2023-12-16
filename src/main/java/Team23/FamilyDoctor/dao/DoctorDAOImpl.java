package Team23.FamilyDoctor.dao;

import Team23.FamilyDoctor.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

public class DoctorDAOImpl implements DoctorDAO {

    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Doctor> getDoctors() {
        TypedQuery query = entityManager.createQuery("from Doctor", Doctor.class);
        return query.getResultList();
    }

    @Override
    public Doctor getDoctor(Integer doctor_id) {
        return entityManager.find(Doctor.class, doctor_id);
    }

    @Override
    @Transactional
    public void saveDoctor(Doctor doctor) {
        System.out.println("doctor "+ doctor.getId());
        if (doctor.getId() == null) {
            entityManager.persist(doctor);
        } else {
            entityManager.merge(doctor);
        }
    }

    @Override
    @Transactional
    public void deleteDoctor(Integer doctor_id) {
        System.out.println("Deleting doctor with id: " + doctor_id);
        entityManager.remove(entityManager.find(Doctor.class, doctor_id));
    }

    @Override
    @Transactional
    public List<Request> getDoctorRequests(Integer doctor_id) {
        Doctor doctor = entityManager.find(Doctor.class, doctor_id);
        return doctor.getRequests();
    }
}

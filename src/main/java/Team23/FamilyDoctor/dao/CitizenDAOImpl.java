package Team23.FamilyDoctor.dao;

import Team23.FamilyDoctor.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

public class CitizenDAOImpl implements CitizenDAO {

    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Citizen> getCitizens() {
        TypedQuery query = entityManager.createQuery("from Citizen", Citizen.class);
        return query.getResultList();
    }

    @Override
    public Citizen getCitizen(Integer citizen_id) {
        return entityManager.find(Citizen.class, citizen_id);
    }

    @Override
    @Transactional
    public void saveCitizen(Citizen citizen) {
        System.out.println("citizen "+ citizen.getId());
        if (citizen.getId() == null) {
            entityManager.persist(citizen);
        } else {
            entityManager.merge(citizen);
        }
    }

    @Override
    @Transactional
    public void deleteCitizen(Integer citizen_id) {
        System.out.println("Deleting citizen with id: " + citizen_id);
        entityManager.remove(entityManager.find(Citizen.class, citizen_id));
    }

    @Override
    @Transactional
    public List<Request> getCitizenRequests(Integer citizen_id) {
        Citizen citizen = entityManager.find(Citizen.class, citizen_id);
        return citizen.getRequests();
    }
}

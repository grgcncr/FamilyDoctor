package Team23.FamilyDoctor.dao;

import Team23.FamilyDoctor.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CitizenDAOImpl implements CitizenDAO {
    @Autowired
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
        System.out.println("citizen " + citizen.getId());
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

    @Transactional
    public void cleanupDuplicateCitizens() {
        List<Citizen> allCitizens = getCitizens();
        Map<String, Citizen> uniqueCitizens = new HashMap<>();

        // Iterate through the list to identify duplicates based on your criteria
        for (Citizen citizen : allCitizens) {
            String key = generateKeyForCitizen(citizen); // Define a method to generate a unique key
            if (!uniqueCitizens.containsKey(key)) {
                uniqueCitizens.put(key, citizen);
            } else {
                // Delete duplicate citizen from database
                deleteCitizen(citizen.getId());
            }
        }
    }

    // Define a method to generate a unique key based on your criteria (e.g., firstName, lastName, phoneNumber)
    private String generateKeyForCitizen(Citizen citizen) {
        return citizen.getFirstName() + "_" + citizen.getLastName() + "_" + citizen.getPhoneNumber();
    }
}

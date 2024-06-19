package Team23.FamilyDoctor.service;

import Team23.FamilyDoctor.dao.CitizenDAO;
import Team23.FamilyDoctor.dao.DoctorDAO;
import Team23.FamilyDoctor.entity.Citizen;
import Team23.FamilyDoctor.entity.Doctor;
import Team23.FamilyDoctor.entity.Role;
import Team23.FamilyDoctor.entity.User;
import Team23.FamilyDoctor.repository.RequestRepository;
import Team23.FamilyDoctor.repository.RoleRepository;
import Team23.FamilyDoctor.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.stream.Stream;

/**
 * Service to populate database with initial data.
 */
@Service
public class InitialDataService {


    private final UserRepository userRepository;

    private final RoleRepository roleRepository;
    private final CitizenDAO citizenDAO;
    private final DoctorDAO doctorDAO;
    private final RequestRepository requestRepository;
    private final PasswordEncoder passwordEncoder;

    public InitialDataService(UserRepository userRepository,
                              RoleRepository roleRepository, CitizenDAO citizenDAO, DoctorDAO doctorDAO,
                              RequestRepository requestRepository,
                              PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.citizenDAO = citizenDAO;
        this.doctorDAO = doctorDAO;
        this.requestRepository = requestRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void createUsersAndRoles() {
        final List<String> rolesToCreate = List.of("ROLE_ADMIN", "ROLE_USER");
        for (final String roleName : rolesToCreate) {
            roleRepository.findByName(roleName).orElseGet(() -> {
                roleRepository.save(new Role(roleName));
                return null;
            });
        }

        this.userRepository.findByUsername("dbuser").orElseGet(() -> {
            User user = new User("dbuser", "user@hua.gr", this.passwordEncoder.encode("pass123"));
            Set<Role> roles = new HashSet<>();
            roles.add(this.roleRepository.findByName("ROLE_USER").orElseThrow());
            roles.add(this.roleRepository.findByName("ROLE_ADMIN").orElseThrow());
            user.setRoles(roles);
            userRepository.save(user);
            return null;
        });

        this.userRepository.findByUsername("admin").orElseGet(() -> {
            User user = new User("admin", "admin@hua.gr", this.passwordEncoder.encode("admin"));
            Set<Role> roles = new HashSet<>();
            roles.add(this.roleRepository.findByName("ROLE_USER").orElseThrow());
            roles.add(this.roleRepository.findByName("ROLE_ADMIN").orElseThrow());
            user.setRoles(roles);
            userRepository.save(user);
            return null;
        });
    }

    private void createCitizens() {
        for (int i=1; i<=3; i++) {
            final Faker faker = new Faker(new Random(i));
            final String firstName = faker.name().firstName();
            final String lastName = faker.name().lastName();
            final String phoneNumber = String.valueOf(faker.phoneNumber().cellPhone());
            final String socialSecurityNumber = String.valueOf(faker.number().randomNumber(9,true));
            Citizen citizen = new Citizen(firstName,lastName,phoneNumber,socialSecurityNumber);
            this.citizenDAO.saveCitizen(citizen);
            }
    }


    private void createDoctors() {
        for (int i=4; i<=6; i++) {
            final Faker faker = new Faker(new Random(i));
            final String firstName = faker.name().firstName();
            final String lastName = faker.name().lastName();
            final String phoneNumber = String.valueOf(faker.phoneNumber().cellPhone());
            Doctor doctor = new Doctor(firstName,lastName,phoneNumber);
            this.doctorDAO.saveDoctor(doctor);
        }    
    }

    //TODO maybe add createRequests() method

    @PostConstruct
    public void setup() {
        this.createUsersAndRoles();
        this.createCitizens();
        this.createDoctors();
        this.citizenDAO.cleanupDuplicateCitizens();
        this.doctorDAO.cleanupDuplicateDoctors();
    }
}
package Team23.FamilyDoctor;

import Team23.FamilyDoctor.dao.CitizenDAO;
import Team23.FamilyDoctor.dao.CitizenDAOImpl;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.BufferedReader;
@SpringBootApplication
public class FamilyDoctorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamilyDoctorApplication.class, args);
    }

}

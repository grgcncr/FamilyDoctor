package Team23.FamilyDoctor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FamilyDoctorApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamilyDoctorApplication.class, args);
    }

}

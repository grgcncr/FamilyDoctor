package Team23.FamilyDoctor.repository;

import Team23.FamilyDoctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

}

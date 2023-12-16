package Team23.FamilyDoctor.dao;

import Team23.FamilyDoctor.entity.*;

import java.util.List;

public interface DoctorDAO {

    public List<Doctor> getDoctors();

    public Doctor getDoctor(Integer doctor_id);

    public void saveDoctor(Doctor doctor);

    public void deleteDoctor(Integer doctor_id);

    public List<Request> getDoctorRequests(Integer doctor_id);
}

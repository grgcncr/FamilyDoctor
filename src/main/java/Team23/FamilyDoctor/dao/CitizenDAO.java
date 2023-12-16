package Team23.FamilyDoctor.dao;

import Team23.FamilyDoctor.entity.*;

import java.util.List;

public interface CitizenDAO {

    public List<Citizen> getCitizens();

    public Citizen getCitizen(Integer citizen_id);

    public void saveCitizen(Citizen citizen);

    public void deleteCitizen(Integer citizen_id);

    public List<Request> getCitizenRequests(Integer citizen_id);
}

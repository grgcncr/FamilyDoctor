package Team23.FamilyDoctor.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Request {



    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column
    private Integer Id;

//    @Column
//    private String citizenId;
//
//    @Column
//    private String doctorId;

    @Column
    private String date;

    @Column
    private String status;

    public Request() {
    }

    public Request(String date, String status, Citizen citizen, Doctor doctor) {
        this.date = date;
        this.status = status;
    }

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="citizen_id")
    private Citizen citizen;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="doctor_id")
    private Doctor doctor;


//    public String getCitizenId() {
//        return citizenId;
//    }
//
//    public void setCitizenId(String citizenId) {
//        this.citizenId = citizenId;
//    }
//
//    public String getDoctorId() {
//        return doctorId;
//    }
//
//    public void setDoctorId(String doctorId) {
//        this.doctorId = doctorId;
//    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {

        this.citizen = citizen;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {

        this.doctor = doctor;
    }


    @Override
    public String toString() {
        return "Request{" +
                "Id=" + Id +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

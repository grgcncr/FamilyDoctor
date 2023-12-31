package Team23.FamilyDoctor.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Integer Id;

    @Column
    private String reqDate;

    @Column
    private String status;

    public Request() {
        this.reqDate = LocalDate.now().toString();
        this.status =  "PENDING";
    }

    public Request(Citizen citizen, Doctor doctor) {
        this.reqDate = LocalDate.now().toString();
        this.status =  "PENDING";
    }

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "citizen_id")
    private Citizen citizen;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public String getReqDate() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Integer getId() {
        return Id;
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
        return "Request{" + "Id=" + Id + ", reqDate='" + reqDate + '\'' + ", status='" + status + '\'' + '}';
    }
}

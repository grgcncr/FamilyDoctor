package Team23.FamilyDoctor.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer Id;

    @Column
    private String fname;
    @Column
    private String lname;
    @Column
    private String phoneNumber;

    public Doctor() {
    }

    public Doctor(String fname, String lname, String phoneNumber) {
        this.fname = fname;
        this.lname = lname;
        this.phoneNumber = phoneNumber;
    }


    @OneToMany(mappedBy="doctor", cascade = CascadeType.ALL)
    @JoinColumn(name="request_id")
    private List<Request> requests;

    public List<Request> getRequests(){
        return requests;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Integer getId() {
        return Id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "Id=" + Id +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

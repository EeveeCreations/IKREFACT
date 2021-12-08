package nl.hsleiden.svdj8.models.tables;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long adminID;

    private String name;
    private String password; //???????????TODO: CHECK DIT NA

    public Admin() {
    }

    public Admin(Long adminID, String name, String password) {
        this.adminID = adminID;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getAdminID() {
        return this.adminID;
    }

    public void setAdminID(Long adminID) {
        this.adminID = adminID;
    }
}

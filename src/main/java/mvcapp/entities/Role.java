package mvcapp.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer role_id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "role")
    private String role;

    public Role() {
    }

    public Role(Integer user_id, String role){
        this.user_id = user_id;
        this.role = role;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}

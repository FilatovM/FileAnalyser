package mvcapp.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class Role {
    @Id
    @Column(name = "user_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer user_role_id;

    @Column(name = "username")
    private String username;

    @Column(name = "role")
    private String role;

    public Role() {
    }

    public Role(String username, String role){
        this.username = username;
        this.role = role;
    }

    public Integer getId_user_role() {
        return user_role_id;
    }

    public void setId_user_role(Integer user_role_id) {
        this.user_role_id = user_role_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

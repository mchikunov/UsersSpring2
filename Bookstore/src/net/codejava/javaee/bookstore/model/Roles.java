package net.codejava.javaee.bookstore.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles implements GrantedAuthority {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role", unique = true)
    private String roles;

   //private Set<User> users = new HashSet<>(0);

    public Roles(int id, String roles) {
        this.id = id;
        this.roles = roles;
    }

    public Roles() {}

 // @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles3")
//  private Set<User> users;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthority() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}

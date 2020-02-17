package net.codejava.javaee.bookstore.model;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


/**
 * User.java
 * This is a model class represents a book entity
 * @author www.codejava.net
 *
 */

@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;


	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    @Column(name = "FName", unique = true)
	private String FName;

    @Column(name = "SName")
	private String SName;

    @Column(name = "age")
	private float Age;


    @Column(name = "role")
    private String Role;

 // private Set<Roles> roles = new HashSet<Roles>(0);
    private String roles1;


	public User() {
	}

	public User(int id) {
		this.id = id;
	}



	public User(int id, String FName, String SName, float Age, Set<Roles> roles3) {
		this(FName, SName, Age, roles3);
		this.id = id;

	}
	
	public User(String FName, String SName, float Age, Set<Roles> roles3) {
		this.FName = FName;
		this.SName = SName;
		this.Age = Age;
        this.roles3 = roles3;

      //  roles3.add(new Roles(idRole, Role));


	}


	public User(String FName, String SName, float Age, String Role) {
		this.FName = FName;
		this.SName = SName;
		this.Age = Age;
		this.Role = Role;

		//  roles3.add(new Roles(idRole, Role));


	}

   /* @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "role_id")
    private Roles roles2;

    public Roles getRoles1() {
        return roles2;
    }*/

   @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.REFRESH})
    @JoinTable(name = "user_id__role_id", joinColumns = {
            @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Roles> roles3;

	public Set<GrantedAuthority> getAuthorities() {

		HashSet<GrantedAuthority>roles = new HashSet<>(roles3.size());

		for (Roles role : roles3)
			roles.add(new SimpleGrantedAuthority(role.getAuthority()));
		return roles;
	}






	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return FName;
	}

	public String getFName() {
		return FName;
	}

	public String getSName() {
		return SName;
	}
	public void setFName(String FName) {
		this.FName = FName;
	}

	public String getUsername() {
		return SName;
	}

	public void setSName(String SName) {
		this.SName = SName;
	}

	public float getAge() {
		return Age;
	}

	public void setAge(float age) {
		this.Age = age;
	}

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }


	public boolean isAccountNonExpired() {return true;}

	public boolean isAccountNonLocked()  {return true;}

	public boolean isCredentialsNonExpired()  {return true;}

	public boolean isEnabled()  {return true;}

}

package net.codejava.javaee.bookstore.model;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * User.java
 * This is a model class represents a book entity
 * @author www.codejava.net
 *
 */

@Entity
@Table(name = "users")
public class User implements Serializable {
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


	public User() {
	}

	public User(int id) {
		this.id = id;
	}

	public User(int id, String FName, String SName, float Age, String Role) {
		this(FName, SName, Age, Role);
		this.id = id;
	}
	
	public User(String FName, String SName, float Age, String Role) {
		this.FName = FName;
		this.SName = SName;
		this.Age = Age;
        this.Role = Role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFName() {
		return FName;
	}

	public void setFName(String FName) {
		this.FName = FName;
	}

	public String getSName() {
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

}

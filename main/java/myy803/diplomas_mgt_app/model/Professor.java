package myy803.diplomas_mgt_app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "professors")
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "specialty")
	private String specialty;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@OneToMany(mappedBy = "professor")
	private List<Subject> subject;

	@OneToMany(mappedBy = "professor")
	private List<Thesis> theses;

	public Professor() {

	}

	public Professor(User user) {
		this.user = user;
	}

	public Professor(String firstName, String lastName, String specialty, User user) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.specialty = specialty;
		this.user = user;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getId() {
		return id;
	}

	public String getSpecialty() {
		return specialty;
	}

	public User getUser() {
		return user;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setId(int id) {
		this.id = id;
	}

}

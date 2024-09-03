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
@Table(name = "students")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "year")
	private int year;

	@Column(name = "avg_grade")
	private float avgGrade;

	@Column(name = "num_remain_courses")
	private int numRemainCourses;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@OneToMany(mappedBy = "student")
	private List<Application> application;

	public Student() {
	}

	public Student(String firstName, String lastName, int year, float avgGrade, int numRemainCourses, User user) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.year = year;
		this.avgGrade = avgGrade;
		this.numRemainCourses = numRemainCourses;
		this.user = user;
	}

	public Student(User user) {
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

	public int getYear() {
		return year;
	}

	public float getAvgGrade() {
		return avgGrade;
	}

	public int getNumRemainCourses() {
		return numRemainCourses;
	}

	public User getUser() {
		return user;
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

	public void setYear(int year) {
		this.year = year;
	}

	public void setAvgGrade(float avgGrade) {
		this.avgGrade = avgGrade;
	}

	public void setNumRemainCourses(int numRemainCourses) {
		this.numRemainCourses = numRemainCourses;
	}

}

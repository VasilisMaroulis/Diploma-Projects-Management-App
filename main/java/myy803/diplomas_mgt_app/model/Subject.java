package myy803.diplomas_mgt_app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subjects")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "objectives")
	private String objectives;

	@ManyToOne
	@JoinColumn(name = "professor_id")
	private Professor professor;

	@OneToMany(mappedBy = "subject")
	private List<Application> application;

	@Column(name = "isTaken")
	private boolean taken;

	public Subject() {
	}

	public Subject(String title, String objectives, Professor professor) {
		this.title = title;
		this.objectives = objectives;
		this.professor = professor;
	}

	public Subject(Professor professor) {
		this.professor = professor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Application> getApplications() {
		return application;
	}

	public void setApplications(List<Application> theApplications) {
		this.application = theApplications;
	}

	public boolean isTaken() {
		return this.taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}
}

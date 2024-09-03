package myy803.diplomas_mgt_app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "theses")
public class Thesis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id", referencedColumnName = "id")
	private Student student;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subject_id", referencedColumnName = "id")
	private Subject subject;

	@ManyToOne
	@JoinColumn(name = "professor_id")
	private Professor professor;

	@Column(name = "impl_grade")
	private float implGrade;

	@Column(name = "report_grade")
	private float reportGrade;

	@Column(name = "pres_grade")
	private float presGrade;

	@Column(name = "total_grade")
	private float totalGrade;

	public Thesis() {
	}

	public Thesis(Student student, Subject subject, Professor professor, float implGrade, float reportGrade,
			float presGrade, float totalGrade) {
		this.student = student;
		this.subject = subject;
		this.professor = professor;
		this.implGrade = implGrade;
		this.reportGrade = reportGrade;
		this.presGrade = presGrade;
		this.totalGrade = totalGrade;
	}

	public Thesis(Student student, Subject subject) {
		this.student = student;
		this.subject = subject;
		this.professor = subject.getProfessor();
	}

	public Thesis(Subject subject) {
		this.subject = subject;
		this.professor = subject.getProfessor();
	}

	public Thesis(Student student) {
		this.student = student;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public float getImplGrade() {
		return implGrade;
	}

	public void setImplGrade(float implGrade) {
		this.implGrade = implGrade;
	}

	public float getReportGrade() {
		return reportGrade;
	}

	public void setReportGrade(float reportGrade) {
		this.reportGrade = reportGrade;
	}

	public float getPresGrade() {
		return presGrade;
	}

	public void setPresGrade(float presGrade) {
		this.presGrade = presGrade;
	}

	public float getTotalGrade() {
		return totalGrade;
	}

	public void setTotalGrade(float totalGrade) {
		this.totalGrade = totalGrade;
	}

	public void calculateTotalGrade() {
		float total = (float) (this.getImplGrade() * 0.7 + this.getReportGrade() * 0.15 + this.getPresGrade() * 0.15);
		this.setTotalGrade(total);
		return;
	}

}

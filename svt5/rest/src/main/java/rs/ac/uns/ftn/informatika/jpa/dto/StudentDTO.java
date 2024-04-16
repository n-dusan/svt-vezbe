package rs.ac.uns.ftn.informatika.jpa.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import rs.ac.uns.ftn.informatika.jpa.model.Student;

public class StudentDTO {
	private Integer id;
	@NotBlank
	private String index;
	
	@Length(min = 5, max = 20, message="Presli ste dozvoljenu duzinu imena!")
	private String firstName;
	
	private String lastName;

	public StudentDTO() {

	}

	public StudentDTO(Student student) {
		this(student.getId(), student.getIndex(), student.getFirstName(), student.getLastName());
	}

	public StudentDTO(Integer id, String index, String firstName, String lastName) {
		this.id = id;
		this.index = index;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public String getIndex() {
		return index;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}

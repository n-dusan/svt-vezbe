package rs.ac.uns.ftn.informatika.jpa.dto;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import rs.ac.uns.ftn.informatika.jpa.model.Course;

public class CourseDTO {
	
	@NotNull
	private Integer id;
	
	@NotBlank
	@Pattern(regexp = "[a-zA-Z0-9]{3,15}", message="Duzina imena mora biti izmedju 3 i 15 karaktera!")
	private String name;
	
	@Email(message="Mora biti email")
	
	private String email;
	

	public CourseDTO() {

	}

	public CourseDTO(Course course) {
		this(course.getId(), course.getName());
	}

	public CourseDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}

package net.javaguides.springmvc.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="customer")
public class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@NotBlank(message = "firstName is empty")
	@Column(name="first_name")
	private String firstName;

	@NotBlank(message = "lastName is empty")
	@Column(name="last_name")
	private String lastName;

	@NotBlank(message = "email is empty")
	@Column(name="email")
	private String email;

	@OneToMany(fetch = FetchType.EAGER,mappedBy="customer_id", cascade = CascadeType.REMOVE)
	private Set<Phone> phones;

	public Customer() {
		
	}

	public Set<Phone> getPhones() {
		return phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
}






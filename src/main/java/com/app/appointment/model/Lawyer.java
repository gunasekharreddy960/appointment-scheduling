package com.app.appointment.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Lawyer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String specialization;
	@OneToMany(mappedBy = "lawyer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Availability> availabilities;
	@OneToMany(mappedBy = "lawyer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> appointments;

	public Lawyer() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public List<Availability> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(List<Availability> availabilities) {
		this.availabilities = availabilities;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
}

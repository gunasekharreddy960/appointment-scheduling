package com.app.appointment.dto;

public class RescheduleDTO {
	private Long appointmentId;
	private Long availabilityId;

	public RescheduleDTO() {
	}

	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Long getAvailabilityId() {
		return availabilityId;
	}

	public void setAvailabilityId(Long availabilityId) {
		this.availabilityId = availabilityId;
	}
}

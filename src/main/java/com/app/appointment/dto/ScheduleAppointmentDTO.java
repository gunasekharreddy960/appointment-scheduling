package com.app.appointment.dto;

public class ScheduleAppointmentDTO {
	private Long lawyerId;
	private Long clientId;
	private Long availabilityId;

	public ScheduleAppointmentDTO() {
	}

	public Long getLawyerId() {
		return lawyerId;
	}

	public void setLawyerId(Long lawyerId) {
		this.lawyerId = lawyerId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getAvailabilityId() {
		return availabilityId;
	}

	public void setAvailabilityId(Long availabilityId) {
		this.availabilityId = availabilityId;
	}
}

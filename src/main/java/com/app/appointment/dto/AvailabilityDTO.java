package com.app.appointment.dto;

import java.time.LocalDateTime;

public class AvailabilityDTO {
	private Long id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private boolean booked;

	public AvailabilityDTO() {
	}

	public AvailabilityDTO(Long id, LocalDateTime startTime, LocalDateTime endTime, boolean booked) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.booked = booked;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public boolean isBooked() {
		return booked;
	}

	public void setBooked(boolean booked) {
		this.booked = booked;
	}
}

package com.app.appointment.dto;

import java.time.LocalDateTime;

public class AppointmentResponseDTO {
	private Long id;
	private Long lawyerId;
	private Long clientId;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private String status;

	public AppointmentResponseDTO() {
	}

	public AppointmentResponseDTO(Long id, Long lawyerId, Long clientId, LocalDateTime startTime, LocalDateTime endTime,
			String status) {
		this.id = id;
		this.lawyerId = lawyerId;
		this.clientId = clientId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

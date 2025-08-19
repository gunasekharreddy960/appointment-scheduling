package com.app.appointment.controller;

import com.app.appointment.dto.*;
import com.app.appointment.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AppointmentController {
	private final AppointmentService service;

	public AppointmentController(AppointmentService service) {
		this.service = service;
	}

	@GetMapping("/lawyers/{lawyerId}/availabilities")
	public ResponseEntity<List<AvailabilityDTO>> availabilities(@PathVariable Long lawyerId) {
		return ResponseEntity.ok(service.listAvailabilities(lawyerId));
	}

	@PostMapping("/appointments")
	public ResponseEntity<AppointmentResponseDTO> schedule(@RequestBody ScheduleAppointmentDTO dto) {
		return ResponseEntity.ok(service.schedule(dto));
	}

	@GetMapping("/lawyers/{lawyerId}/appointments")
	public ResponseEntity<List<AppointmentResponseDTO>> lawyerAppointments(@PathVariable Long lawyerId) {
		return ResponseEntity.ok(service.viewAppointmentsByLawyer(lawyerId));
	}

	@GetMapping("/clients/{clientId}/appointments")
	public ResponseEntity<List<AppointmentResponseDTO>> clientAppointments(@PathVariable Long clientId) {
		return ResponseEntity.ok(service.viewAppointmentsByClient(clientId));
	}

	@PostMapping("/appointments/{id}/cancel")
	public ResponseEntity<Void> cancel(@PathVariable Long id) {
		service.cancel(id);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/appointments/reschedule")
	public ResponseEntity<AppointmentResponseDTO> reschedule(@RequestBody RescheduleDTO dto) {
		return ResponseEntity.ok(service.reschedule(dto));
	}
}

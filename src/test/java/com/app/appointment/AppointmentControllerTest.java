package com.app.appointment;


import com.app.appointment.controller.AppointmentController;
import com.app.appointment.dto.*;
import com.app.appointment.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppointmentControllerTest {

    private AppointmentService service;
    private AppointmentController controller;

    @BeforeEach
    public void setup() {
        service = mock(AppointmentService.class);
        controller = new AppointmentController(service);
    }

    @Test
    public void testAvailabilities() {
        AvailabilityDTO avail1 = new AvailabilityDTO(1L, LocalDateTime.now(), LocalDateTime.now().plusHours(1), false);
        AvailabilityDTO avail2 = new AvailabilityDTO(2L, LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(3), false);
        List<AvailabilityDTO> availabilities = Arrays.asList(avail1, avail2);

        when(service.listAvailabilities(1L)).thenReturn(availabilities);

        ResponseEntity<List<AvailabilityDTO>> response = controller.availabilities(1L);

        assertNotNull(response);
        assertEquals(2, response.getBody().size());
        verify(service, times(1)).listAvailabilities(1L);
    }


    @Test
    public void testLawyerAppointments() {
        AppointmentResponseDTO appt1 = new AppointmentResponseDTO(1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now().plusHours(1), "SCHEDULED");
        List<AppointmentResponseDTO> appointments = Arrays.asList(appt1);

        when(service.viewAppointmentsByLawyer(1L)).thenReturn(appointments);

        ResponseEntity<List<AppointmentResponseDTO>> response = controller.lawyerAppointments(1L);

        assertNotNull(response);
        assertEquals(1, response.getBody().size());
        verify(service, times(1)).viewAppointmentsByLawyer(1L);
    }

    @Test
    public void testClientAppointments() {
        AppointmentResponseDTO appt1 = new AppointmentResponseDTO(1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now().plusHours(1), "SCHEDULED");
        List<AppointmentResponseDTO> appointments = Arrays.asList(appt1);

        when(service.viewAppointmentsByClient(1L)).thenReturn(appointments);

        ResponseEntity<List<AppointmentResponseDTO>> response = controller.clientAppointments(1L);

        assertNotNull(response);
        assertEquals(1, response.getBody().size());
        verify(service, times(1)).viewAppointmentsByClient(1L);
    }

    @Test
    public void testCancelAppointment() {
        doNothing().when(service).cancel(1L);

        ResponseEntity<Void> response = controller.cancel(1L);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        verify(service, times(1)).cancel(1L);
    }

    @Test
    public void testScheduleAppointment() {
        // Create DTO and set fields
        ScheduleAppointmentDTO dto = new ScheduleAppointmentDTO();
        dto.setLawyerId(1L);
        dto.setClientId(1L);
        dto.setAvailabilityId(1L);

        // Create response object
        AppointmentResponseDTO responseDTO = new AppointmentResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setLawyerId(1L);
        responseDTO.setClientId(1L);
        responseDTO.setStartTime(LocalDateTime.now());
        responseDTO.setEndTime(LocalDateTime.now().plusHours(1));
        responseDTO.setStatus("SCHEDULED");

        when(service.schedule(dto)).thenReturn(responseDTO);

        ResponseEntity<AppointmentResponseDTO> response = controller.schedule(dto);

        assertNotNull(response);
        assertEquals("SCHEDULED", response.getBody().getStatus());
        verify(service, times(1)).schedule(dto);
    }

    @Test
    public void testRescheduleAppointment() {
        // Create DTO and set fields
        RescheduleDTO dto = new RescheduleDTO();
        dto.setAppointmentId(1L);
        dto.setAvailabilityId(2L);

        // Create response object
        AppointmentResponseDTO responseDTO = new AppointmentResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setLawyerId(1L);
        responseDTO.setClientId(1L);
        responseDTO.setStartTime(LocalDateTime.now().plusHours(1));
        responseDTO.setEndTime(LocalDateTime.now().plusHours(2));
        responseDTO.setStatus("SCHEDULED");

        when(service.reschedule(dto)).thenReturn(responseDTO);

        ResponseEntity<AppointmentResponseDTO> response = controller.reschedule(dto);

        assertNotNull(response);
        assertEquals("SCHEDULED", response.getBody().getStatus());
        verify(service, times(1)).reschedule(dto);
    }

}

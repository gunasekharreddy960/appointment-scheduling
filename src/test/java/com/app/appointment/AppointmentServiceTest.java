package com.app.appointment;

import com.app.appointment.dto.*;
import com.app.appointment.exception.BadRequestException;
import com.app.appointment.exception.ResourceNotFoundException;
import com.app.appointment.model.*;
import com.app.appointment.repository.*;
import com.app.appointment.service.AppointmentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppointmentServiceTest {

    @InjectMocks
    private AppointmentService service;

    @Mock
    private LawyerRepository lawyerRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private AvailabilityRepository availabilityRepository;

    @Mock
    private AppointmentRepository appointmentRepository;

    private Lawyer lawyer;
    private Client client;
    private Availability availability;
    private Appointment appointment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        lawyer = new Lawyer();
        lawyer.setId(1L);
        lawyer.setName("John Doe");
        lawyer.setSpecialization("Corporate");

        client = new Client();
        client.setId(1L);
        client.setName("Alice");

        availability = new Availability();
        availability.setId(1L);
        availability.setLawyer(lawyer);
        availability.setStartTime(LocalDateTime.now());
        availability.setEndTime(LocalDateTime.now().plusHours(1));
        availability.setBooked(false);

        appointment = new Appointment();
        appointment.setId(1L);
        appointment.setLawyer(lawyer);
        appointment.setClient(client);
        appointment.setStartTime(availability.getStartTime());
        appointment.setEndTime(availability.getEndTime());
        appointment.setStatus("SCHEDULED");
    }

    @Test
    public void testListAvailabilities() {
        when(lawyerRepository.existsById(1L)).thenReturn(true);
        when(availabilityRepository.findByLawyerIdAndBookedFalse(1L)).thenReturn(Collections.singletonList(availability));

        List<AvailabilityDTO> result = service.listAvailabilities(1L);

        assertEquals(1, result.size());
        assertEquals(availability.getId(), result.get(0).getId());
        verify(availabilityRepository, times(1)).findByLawyerIdAndBookedFalse(1L);
    }

    @Test
    public void testScheduleAppointment() {
        ScheduleAppointmentDTO dto = new ScheduleAppointmentDTO();
        dto.setLawyerId(1L);
        dto.setClientId(1L);
        dto.setAvailabilityId(1L);

        when(lawyerRepository.findById(1L)).thenReturn(Optional.of(lawyer));
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(availabilityRepository.findById(1L)).thenReturn(Optional.of(availability));
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        AppointmentResponseDTO response = service.schedule(dto);

        assertNotNull(response);
        assertEquals("SCHEDULED", response.getStatus());
        assertTrue(availability.isBooked());
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
    }

    @Test
    public void testScheduleAppointmentAlreadyBooked() {
        availability.setBooked(true);
        ScheduleAppointmentDTO dto = new ScheduleAppointmentDTO();
        dto.setLawyerId(1L);
        dto.setClientId(1L);
        dto.setAvailabilityId(1L);

        when(lawyerRepository.findById(1L)).thenReturn(Optional.of(lawyer));
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(availabilityRepository.findById(1L)).thenReturn(Optional.of(availability));

        assertThrows(BadRequestException.class, () -> service.schedule(dto));
    }

    @Test
    public void testViewAppointmentsByLawyer() {
        when(lawyerRepository.existsById(1L)).thenReturn(true);
        when(appointmentRepository.findByLawyerId(1L)).thenReturn(Collections.singletonList(appointment));

        List<AppointmentResponseDTO> result = service.viewAppointmentsByLawyer(1L);

        assertEquals(1, result.size());
        assertEquals("SCHEDULED", result.get(0).getStatus());
    }

    @Test
    public void testViewAppointmentsByClient() {
        when(clientRepository.existsById(1L)).thenReturn(true);
        when(appointmentRepository.findByClientId(1L)).thenReturn(Collections.singletonList(appointment));

        List<AppointmentResponseDTO> result = service.viewAppointmentsByClient(1L);

        assertEquals(1, result.size());
        assertEquals("SCHEDULED", result.get(0).getStatus());
    }

    @Test
    public void testCancelAppointment() {
        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));

        service.cancel(1L);

        assertEquals("CANCELLED", appointment.getStatus());
        verify(appointmentRepository, times(1)).save(appointment);
    }


    @Test
    public void testRescheduleAlreadyBooked() {
        RescheduleDTO dto = new RescheduleDTO();
        dto.setAppointmentId(1L);
        dto.setAvailabilityId(1L);
        availability.setBooked(true);

        when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));
        when(availabilityRepository.findById(1L)).thenReturn(Optional.of(availability));

        assertThrows(BadRequestException.class, () -> service.reschedule(dto));
    }
}

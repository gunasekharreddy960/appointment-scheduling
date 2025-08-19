package com.app.appointment.service;
import com.app.appointment.dto.*;
import com.app.appointment.exception.BadRequestException;
import com.app.appointment.exception.ResourceNotFoundException;
import com.app.appointment.model.*;
import com.app.appointment.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class AppointmentService {
    private final LawyerRepository lawyerRepository;
    private final ClientRepository clientRepository;
    private final AvailabilityRepository availabilityRepository;
    private final AppointmentRepository appointmentRepository;
    public AppointmentService(LawyerRepository lawyerRepository, ClientRepository clientRepository,
                              AvailabilityRepository availabilityRepository, AppointmentRepository appointmentRepository) {
        this.lawyerRepository = lawyerRepository;
        this.clientRepository = clientRepository;
        this.availabilityRepository = availabilityRepository;
        this.appointmentRepository = appointmentRepository;
    }
    public List<AvailabilityDTO> listAvailabilities(Long lawyerId) {
        if (!lawyerRepository.existsById(lawyerId)) {
            throw new ResourceNotFoundException("Lawyer not found");
        }
        List<Availability> availabilities = availabilityRepository.findByLawyerIdAndBookedFalse(lawyerId);
        List<AvailabilityDTO> result = new ArrayList<AvailabilityDTO>();
        for (Availability a : availabilities) {
            AvailabilityDTO dto = new AvailabilityDTO(a.getId(), a.getStartTime(), a.getEndTime(), a.isBooked());
            result.add(dto);
        }
        return result;
    }
    public AppointmentResponseDTO schedule(ScheduleAppointmentDTO dto) {
        Lawyer lawyer = lawyerRepository.findById(dto.getLawyerId()).orElseThrow(() -> new ResourceNotFoundException("Lawyer not found"));
        Client client = clientRepository.findById(dto.getClientId()).orElseThrow(() -> new ResourceNotFoundException("Client not found"));
        Availability availability = availabilityRepository.findById(dto.getAvailabilityId()).orElseThrow(() -> new ResourceNotFoundException("Availability not found"));
        if (availability.isBooked()) { throw new BadRequestException("Availability already booked"); }
        availability.setBooked(true);
        availabilityRepository.save(availability);
        Appointment appt = new Appointment();
        appt.setLawyer(lawyer); appt.setClient(client); appt.setStartTime(availability.getStartTime()); appt.setEndTime(availability.getEndTime()); appt.setStatus("SCHEDULED");
        appt = appointmentRepository.save(appt);
        return map(appt);
    }
    public List<AppointmentResponseDTO> viewAppointmentsByLawyer(Long lawyerId) {
        if (!lawyerRepository.existsById(lawyerId)) { throw new ResourceNotFoundException("Lawyer not found"); }
        List<Appointment> appts = appointmentRepository.findByLawyerId(lawyerId);
        List<AppointmentResponseDTO> res = new ArrayList<AppointmentResponseDTO>();
        for (Appointment a : appts) { res.add(map(a)); }
        return res;
    }
    public List<AppointmentResponseDTO> viewAppointmentsByClient(Long clientId) {
        if (!clientRepository.existsById(clientId)) { throw new ResourceNotFoundException("Client not found"); }
        List<Appointment> appts = appointmentRepository.findByClientId(clientId);
        List<AppointmentResponseDTO> res = new ArrayList<AppointmentResponseDTO>();
        for (Appointment a : appts) { res.add(map(a)); }
        return res;
    }
    public void cancel(Long appointmentId) {
        Appointment appt = appointmentRepository.findById(appointmentId).orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        appt.setStatus("CANCELLED");
        appointmentRepository.save(appt);
    }
    public AppointmentResponseDTO reschedule(RescheduleDTO dto) {
        Appointment appt = appointmentRepository.findById(dto.getAppointmentId()).orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
        Availability newAvail = availabilityRepository.findById(dto.getAvailabilityId()).orElseThrow(() -> new ResourceNotFoundException("Availability not found"));
        if (newAvail.isBooked()) { throw new BadRequestException("Availability already booked"); }
        List<Availability> all = availabilityRepository.findAll();
        for (Availability a : all) {
            if (a.getStartTime().equals(appt.getStartTime()) && a.getLawyer().getId().equals(appt.getLawyer().getId())) {
                a.setBooked(false);
                availabilityRepository.save(a);
                break;
            }
        }
        newAvail.setBooked(true);
        availabilityRepository.save(newAvail);
        appt.setStartTime(newAvail.getStartTime()); appt.setEndTime(newAvail.getEndTime()); appt.setStatus("SCHEDULED");
        appointmentRepository.save(appt);
        return map(appt);
    }
    private AppointmentResponseDTO map(Appointment a) {
        return new AppointmentResponseDTO(a.getId(), a.getLawyer().getId(), a.getClient().getId(), a.getStartTime(), a.getEndTime(), a.getStatus());
    }
}

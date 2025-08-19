package com.app.appointment.repository;
import com.app.appointment.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByLawyerId(Long lawyerId);
    List<Appointment> findByClientId(Long clientId);
}

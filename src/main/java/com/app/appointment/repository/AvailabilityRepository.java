package com.app.appointment.repository;
import com.app.appointment.model.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByLawyerIdAndBookedFalse(Long lawyerId);
}

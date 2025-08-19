package com.app.appointment.repository;
import com.app.appointment.model.Lawyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, Long> {
	
	
}

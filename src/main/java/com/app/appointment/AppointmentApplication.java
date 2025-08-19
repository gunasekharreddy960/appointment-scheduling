package com.app.appointment;
import com.app.appointment.model.*;
import com.app.appointment.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.time.LocalDateTime;
@SpringBootApplication
public class AppointmentApplication {
    public static void main(String[] args) { SpringApplication.run(AppointmentApplication.class, args); }
    @Bean
    public CommandLineRunner seedData(LawyerRepository lawyerRepo, ClientRepository clientRepo, AvailabilityRepository availRepo) {
        return args -> {
            if (lawyerRepo.count() == 0) {
                Lawyer l = new Lawyer();
                l.setName("John Doe");
                l.setSpecialization("Civil");
                lawyerRepo.save(l);
                Client c = new Client();
                c.setName("Alice");
                c.setEmail("alice@example.com");
                c.setPhone("1234567890");
                clientRepo.save(c);
                Availability a = new Availability();
                a.setLawyer(l);
                a.setStartTime(LocalDateTime.now().plusDays(1).withHour(10).withMinute(0).withSecond(0).withNano(0));
                a.setEndTime(a.getStartTime().plusHours(1));
                a.setBooked(false);
                availRepo.save(a);
            }
        };
    }
}

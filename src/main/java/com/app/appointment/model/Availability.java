package com.app.appointment.model;
import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
public class Availability {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean booked;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lawyer_id")
    private Lawyer lawyer;
    public Availability() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public boolean isBooked() { return booked; }
    public void setBooked(boolean booked) { this.booked = booked; }
    public Lawyer getLawyer() { return lawyer; }
    public void setLawyer(Lawyer lawyer) { this.lawyer = lawyer; }
}

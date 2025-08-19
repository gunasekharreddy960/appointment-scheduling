package com.app.appointment.model;
import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "lawyer_id") private Lawyer lawyer;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "client_id") private Client client;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    public Appointment() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Lawyer getLawyer() { return lawyer; }
    public void setLawyer(Lawyer lawyer) { this.lawyer = lawyer; }
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

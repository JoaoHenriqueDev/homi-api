package app.Homi.HomiApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "notifications")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class notificationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "bill_id")
    private UUID billId;
    @Column(name = "installment_id")
    private UUID installmentId;
    @Column(name = "notification_type")
    private String notificationType;
    @Column(name = "title")
    private String title;
    @Column(name = "message")
    private String message;
    @Column(name = "read")
    private Boolean read;
    @Column(name = "created_at")
    private LocalDate created_at;
}

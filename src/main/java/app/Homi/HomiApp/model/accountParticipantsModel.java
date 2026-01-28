package app.Homi.HomiApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "account_participants")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class accountParticipantsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "account_id")
    private UUID accountId;
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "total_amount")
    private Double total;
    @Column(name = "installment_amount")
    private Double installmentTotal;
    @Column(name = "created_at")
    private LocalDate created_at;
}

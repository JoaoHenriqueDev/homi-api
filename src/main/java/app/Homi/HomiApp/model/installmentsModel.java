package app.Homi.HomiApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "installments")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class installmentsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "account_id")
    private UUID accountId;
    @Column(name = "installment_number")
    private Integer installmentNumber;
    @Column(name = "total_amount")
    private Double total;
    @Column(name = "due_date")
    private LocalDate dueDate;
    @Column(name = "status")
    private String status;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "updated_at")
    private LocalDate updateAt;
}

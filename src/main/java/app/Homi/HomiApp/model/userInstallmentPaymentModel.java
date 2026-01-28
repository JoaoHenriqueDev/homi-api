package app.Homi.HomiApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "user_installment_payments")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class userInstallmentPaymentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "installment_id")
    private UUID installmentId;
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "paid")
    private Boolean paid;
    @Column(name = "paid_at")
    private LocalDate paidAt;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "proof_image_url")
    private String proofImageUrl;
    @Column(name = "validated")
    private Boolean validado;
    @Column(name = "validated_by")
    private UUID validatedBy;
    @Column(name = "validated_at")
    private LocalDate validatedAt;
    @Column(name = "validation_notes")
    private String validationNotes;
    @Column(name = "notes")
    private String notes;
    @Column(name = "created_at")
    private LocalDate created_at;
    @Column(name = "updated_at")
    private LocalDate updated_at;
}

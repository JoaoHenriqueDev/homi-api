package app.Homi.HomiApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "payment_proofs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class paymentProofsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "bill_id")
    private UUID billId;
    @Column(name = "paid_by")
    private UUID paidUserId;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "payment_date")
    private LocalDate paymentDate;
    @Column(name = "payment_amount")
    private Double paymentTotal;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "notes")
    private String notes;
    @Column(name = "verified")
    private Boolean verified;
    @Column(name = "verified_by")
    private UUID verifiedByUserId;
    @Column(name = "verified_at")
    private LocalDate verifiedAt;
}

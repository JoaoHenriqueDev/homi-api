package app.Homi.HomiApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "accounts")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class accountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "group_id")
    private UUID groupId;
    @Column(name = "category_id")
    private UUID categoryId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "total_amount")
    private Double total;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "installments_count")
    private Integer installmentCount;
    @Column(name = "installment_amount")
    private Double installmentTCada;
    @Column(name = "first_due_date")
    private LocalDate dataInicio;
    @Column(name = "pix_key")
    private String pixKey;
    @Column(name = "pix_key_type")
    private String pixKeyType;
    @Column(name = "pix_receiver_name")
    private String pixNameReceiver;
    @Column(name = "created_by")
    private UUID createdPor;
    @Column(name = "purchase_date")
    private LocalDate dataCompra;
    @Column(name = "status")
    private String status;
    @Column(name = "created_at")
    private LocalDate created_at;
    @Column(name = "update_at")
    private LocalDate update_at;
}

package app.Homi.HomiApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "bills")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class billsModel {
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
    @Column(name = "amount")
    private Double valorTotal;
    @Column(name = "due_date")
    private LocalDate dataVencimento;
    @Column(name = "barcode_data")
    private String codigoCodBarraQrCode;
    @Column(name = "barcode_type")
    private String tipoQrCodeCodBarra;
    @Column(name = "registered_by")
    private UUID criador;
    @Column(name = "assigned_to")
    private UUID pagante;
    @Column(name = "status")
    private String status;
}

package app.Homi.HomiApp.model;

import app.Homi.HomiApp.Enum.menberFamilyEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "family_members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class familyMemberModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "family_id", columnDefinition = "BINARY(16)")
    private UUID idFamily;
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID idUser;
    @Column(name = "role")
    private String roleUser;
    @Column(name = "is_owner")
    private Boolean criador;
    @Column(name = "joined_at")
    private LocalDateTime dataEntrada;
}

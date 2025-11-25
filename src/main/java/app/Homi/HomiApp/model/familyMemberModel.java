package app.Homi.HomiApp.model;

import app.Homi.HomiApp.Enum.menberFamilyEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "family_members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class familyMemberModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @Column(name = "family_id")
    private BigInteger idFamily;
    @Column(name = "user_id")
    private BigInteger idUser;
    @Column(name = "role")
    private menberFamilyEnum roleUser;
    @Column(name = "is_owner")
    private Boolean criador;
    @Column(name = "joined_at")
    private LocalDateTime dataEntrada;
}

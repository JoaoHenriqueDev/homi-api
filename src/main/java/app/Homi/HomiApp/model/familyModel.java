package app.Homi.HomiApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "families")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class familyModel {
    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "owner_user_id")
    private UUID idUserAdmin;
    @Column(name = "invite_link", columnDefinition = "BINARY(16)")
    private UUID convite;
    @Column(name = "created_at")
    private LocalDateTime dataCriacao = LocalDateTime.now();
}

package app.Homi.HomiApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "group_members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class groupMemberModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "group_id")
    private UUID idGroup;
    @Column(name = "user_id")
    private UUID idUser;
    @Column(name = "role")
    private String roleUser;
    @Column(name = "is_owner")
    private Boolean criador;
    @Column(name = "joined_at")
    private LocalDateTime dataEntrada;
}

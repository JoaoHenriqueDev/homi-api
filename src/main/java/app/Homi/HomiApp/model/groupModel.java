package app.Homi.HomiApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class groupModel {
    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "created_by")
    private UUID idUserAdmin;
    @Column(name = "invite_link")
    private UUID convite;
    @Column(name = "created_at")
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @Column(name = "fundo_url")
    private String fotoUrl;
}

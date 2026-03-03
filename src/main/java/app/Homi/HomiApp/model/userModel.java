package app.Homi.HomiApp.model;

import app.Homi.HomiApp.Enum.userEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table( name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class userModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "foto")
    private String fotoURL;
    @Column(name = "celular", nullable = false)
    private String celular;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime created = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private userEnum role;
    @Column(name = "cpf", nullable = false)
    private String cpf;
}

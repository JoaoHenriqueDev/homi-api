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
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name = "password_hash")
    private String password;
    @Column(name = "avatar_url")
    private String fotoURL;
    @Column(name = "phone")
    private String celular;
    @Column(name = "created_at")
    private LocalDateTime created = LocalDateTime.now();
}

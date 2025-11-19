package app.Homi.HomiApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table( name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class userModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
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
    private LocalDateTime created;
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
}

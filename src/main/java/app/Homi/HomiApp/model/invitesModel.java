package app.Homi.HomiApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "invites")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class invitesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    @Column(name = "family_id")
    private BigInteger idFamily;
    @Column(name = "token")
    private String token;
    @Column(name = "creator_user_id")
    private BigInteger creatorUserId;
    @Column(name = "expires_at")
    private LocalDateTime dateExpires;
    @Column(name = "max_uses")
    private Integer maxUses;
    @Column(name = "uses")
    private Integer uses;
    @Column(name = "created_at")
    private LocalDateTime created;
}

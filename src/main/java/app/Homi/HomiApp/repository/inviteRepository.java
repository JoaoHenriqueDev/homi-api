package app.Homi.HomiApp.repository;

import app.Homi.HomiApp.model.invitesModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface inviteRepository extends JpaRepository<invitesModel, BigInteger> {
}

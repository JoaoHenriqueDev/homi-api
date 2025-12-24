package app.Homi.HomiApp.repository;

import app.Homi.HomiApp.model.userModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface userRepository extends JpaRepository<userModel, UUID> {
    Optional<userModel> findByEmail(String email);
}

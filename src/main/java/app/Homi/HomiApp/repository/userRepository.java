package app.Homi.HomiApp.repository;

import app.Homi.HomiApp.model.userModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface userRepository extends JpaRepository<userModel, BigInteger> {
}

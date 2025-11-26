package app.Homi.HomiApp.repository;

import app.Homi.HomiApp.model.familyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface familyRepository extends JpaRepository<familyModel, BigInteger> {
}

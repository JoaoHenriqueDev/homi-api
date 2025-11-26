package app.Homi.HomiApp.repository;

import app.Homi.HomiApp.model.familyMemberModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface familyMemberRepository extends JpaRepository<familyMemberModel, BigInteger> {
}

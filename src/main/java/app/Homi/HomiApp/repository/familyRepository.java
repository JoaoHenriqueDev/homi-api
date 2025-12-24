package app.Homi.HomiApp.repository;

import app.Homi.HomiApp.model.familyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface familyRepository extends JpaRepository<familyModel, UUID> {

    Optional<familyModel> findByConvite(UUID convite);

    Optional<familyModel> findByName(String name);


}

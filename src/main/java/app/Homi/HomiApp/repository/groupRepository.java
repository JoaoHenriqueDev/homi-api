package app.Homi.HomiApp.repository;

import app.Homi.HomiApp.model.groupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface groupRepository extends JpaRepository<groupModel, UUID> {

    Optional<groupModel> findByConvite(UUID convite);

    List<groupModel> findByName(String name);

    Optional<groupModel> findById(UUID uuid);
}

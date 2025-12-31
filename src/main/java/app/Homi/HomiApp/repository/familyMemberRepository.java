package app.Homi.HomiApp.repository;

import app.Homi.HomiApp.model.familyMemberModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface familyMemberRepository extends JpaRepository<familyMemberModel, UUID> {

    List<familyMemberModel> findByIdFamily(UUID idFamily);

    Optional<familyMemberModel>
    findByIdFamilyAndIdUser(UUID idFamily, UUID idUser);

    @Modifying
    @Transactional
    void deleteByIdFamilyAndIdUser(UUID idFamily, UUID idUser);

    List<familyMemberModel> findByIdUser(UUID idUser);
}

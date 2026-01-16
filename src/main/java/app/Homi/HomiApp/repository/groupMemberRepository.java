package app.Homi.HomiApp.repository;

import app.Homi.HomiApp.model.groupMemberModel;
import app.Homi.HomiApp.model.groupModel;
import app.Homi.HomiApp.model.userModel;
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
public interface groupMemberRepository extends JpaRepository<groupMemberModel, UUID> {

    List<groupMemberModel> findByIdGroup(UUID idgroup);

    Optional<groupMemberModel>
    findByIdGroupAndIdUser(UUID idgroup, UUID idUser);

    @Modifying
    @Transactional
    void deleteByIdGroupAndIdUser(UUID idgroup, UUID idUser);

    @Query("""
    SELECT f
    FROM groupModel f
    JOIN groupMemberModel fm
        ON fm.idGroup = f.id
    WHERE fm.idUser = :userId
""")
    List<groupModel> findFamiliesByUserId(@Param("userId") UUID userId);

    List<userModel> findUsersByidGroup(UUID groupId);

    List<groupMemberModel> findByIdUser(UUID idUser);

    Boolean existsByIdGroupAndIdUser(UUID idGroup, UUID idUser);
}

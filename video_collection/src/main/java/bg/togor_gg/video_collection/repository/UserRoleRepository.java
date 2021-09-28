package bg.togor_gg.video_collection.repository;

import bg.togor_gg.video_collection.model.entity.UserRoleEntity;
import bg.togor_gg.video_collection.model.entity.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    Optional<UserRoleEntity> findByRole(UserRole role);
}

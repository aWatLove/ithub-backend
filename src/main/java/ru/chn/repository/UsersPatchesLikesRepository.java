package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chn.model.UsersPatchesLikes;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersPatchesLikesRepository extends JpaRepository<UsersPatchesLikes, Long> {
    List<UsersPatchesLikes> findAllByPatchId(Long patchId);

    boolean existsByUserIdAndPatchId(Long userId, Long patchId);
    Optional<UsersPatchesLikes> findByPatchIdAndUserId(Long patchId, Long userId);
    void deleteAllByPatchId(Long patchId);

}

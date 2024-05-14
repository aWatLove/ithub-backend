package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chn.model.UserProjectFolows;
import ru.chn.model.UserProjectLikes;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProjectLikesRepository extends JpaRepository<UserProjectLikes, Long> {
    boolean existsByUserIdAndProjectId(Long userId, Long projectId);
    List<UserProjectLikes> findAllByProjectId(Long projectId);
    Optional<UserProjectLikes> findByProjectIdAndUserId(Long projectId, Long userId);
    void deleteByProjectId(Long projectId);
}

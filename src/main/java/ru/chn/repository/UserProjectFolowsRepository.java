package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chn.model.UserProjectFolows;
import ru.chn.model.UserProjectLikes;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserProjectFolowsRepository extends JpaRepository<UserProjectFolows, Long> {
    boolean existsByUserIdAndProjectId(Long userId, Long projectId);
    List<UserProjectFolows> findAllByProjectId(Long projectId);
    Optional<UserProjectFolows> findByProjectIdAndUserId(Long projectId, Long userId);
    void deleteByProjectId(Long projectId);
}

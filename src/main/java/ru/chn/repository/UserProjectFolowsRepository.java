package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chn.model.UserProjectFolows;

public interface UserProjectFolowsRepository extends JpaRepository<UserProjectFolows, Long> {
    boolean existsByUserIdAndProjectId(Long userId, Long projectId);
}

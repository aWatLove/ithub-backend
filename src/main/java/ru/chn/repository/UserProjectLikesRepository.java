package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chn.model.UserProjectFolows;
import ru.chn.model.UserProjectLikes;

import java.util.List;

public interface UserProjectLikesRepository extends JpaRepository<UserProjectLikes, Long> {
    boolean existsByUserIdAndProjectId(Long userId, Long projectId);
    List<UserProjectLikes> findAllByProjectId(Long projectId);
}

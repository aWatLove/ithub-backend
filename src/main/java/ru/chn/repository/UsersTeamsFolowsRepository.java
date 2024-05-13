package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chn.model.UsersTeamsFolows;

public interface UsersTeamsFolowsRepository extends JpaRepository<UsersTeamsFolows, Long> {
    boolean existsByUserIdAndTeamId(Long userId, Long teamId);
    Long countByTeamId(Long teamId);
}

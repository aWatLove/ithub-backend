package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chn.model.User;
import ru.chn.model.UsersTeam;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersTeamsRepository extends JpaRepository<UsersTeam, Long> {
    boolean existsUsersTeamByUserIdAndTeamId(Long userId, Long teamId);
    List<UsersTeam> findUsersTeamsByUserId(Long userId);
    List<UsersTeam> findUsersTeamsByTeamId(Long teamId);
    Optional<UsersTeam> findUsersTeamByUserIdAndTeamId(Long userId, Long teamId);
}

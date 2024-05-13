package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chn.model.User;
import ru.chn.model.UsersTeam;

import java.util.List;

public interface UsersTeamsRepository extends JpaRepository<UsersTeam, Long> {
    List<UsersTeam> findUsersTeamsByUserId(Long userId);
}

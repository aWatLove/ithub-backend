package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chn.model.UsersTeamsFolows;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersTeamsFolowsRepository extends JpaRepository<UsersTeamsFolows, Long> {
    boolean existsByUserIdAndTeamId(Long userId, Long teamId);
    Long countByTeamId(Long teamId);

    List<UsersTeamsFolows> findAllByTeamId(Long teamId);

    Optional<UsersTeamsFolows> findByUserIdAndTeamId(Long userId, Long teamId);

}

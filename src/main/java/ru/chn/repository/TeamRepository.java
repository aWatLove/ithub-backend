package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chn.model.Team;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findTeamById(Long id);
    Optional<Team> findTeamByOwnerIdAndName(Long ownerId, String name);
    boolean existsByOwnerIdAndName(Long ownerId, String name);
    boolean existsByOwnerIdAndId(Long ownerId, Long id);
}

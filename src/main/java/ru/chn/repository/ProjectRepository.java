package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.chn.model.Project;
import ru.chn.model.Resume;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findProjectsByTeamId(Long teamId);

    boolean existsByTeamIdAndTitle(Long teamId, String title);

    boolean existsByOwnerIdAndId(Long ownerId, Long id);

    @Query("SELECT p.id FROM Project p")
    List<Long> findAllProjectIds();
}

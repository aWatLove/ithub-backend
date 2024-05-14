package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chn.model.Project;
import ru.chn.model.Resume;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findProjectsByTeamId(Long teamId);
    boolean existsByTeamIdAndTitle(Long teamId, String title);
}

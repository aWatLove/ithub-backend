package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chn.model.ProjectsTags;

import java.util.List;

public interface ProjectTagRepository extends JpaRepository<ProjectsTags, Long> {
    List<ProjectsTags> findAllByProjectId(Long projectId);
    boolean existsByProjectIdAndTagId(Long projectId, Long tagId);
}

package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chn.model.Patch;

import java.util.List;

@Repository
public interface PatchRepository extends JpaRepository<Patch, Long> {
    List<Patch> findAllByProjectId(Long projectId);
    void deleteAllByProjectId(Long projectId);
}

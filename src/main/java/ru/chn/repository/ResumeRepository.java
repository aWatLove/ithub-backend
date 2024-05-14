package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chn.model.Resume;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long>{
    List<Resume> findResumesByUserId(Long userId);
    boolean existsResumeByUserIdAndId(Long userId, Long resumeId);
}

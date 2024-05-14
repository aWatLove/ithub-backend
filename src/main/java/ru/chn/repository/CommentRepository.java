package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chn.model.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByProjectId(Long projectId);
    boolean existsByUserIdAndIdAndProjectId(Long userId, Long Id, Long projectId);
}

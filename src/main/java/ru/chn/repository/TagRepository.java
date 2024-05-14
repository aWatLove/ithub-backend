package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chn.model.Resume;
import ru.chn.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}

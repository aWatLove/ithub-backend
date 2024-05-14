package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chn.model.Patch;

public interface PatchRepository extends JpaRepository<Patch, Long> {
}

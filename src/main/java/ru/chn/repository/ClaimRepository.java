package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chn.model.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
}

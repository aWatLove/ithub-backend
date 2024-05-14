package ru.chn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chn.model.Claim;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
    boolean existsClaimByTeamIdAndResumeId(Long teamId, Long resumeId);
    List<Claim> findClaimsByResumeId(Long resumeId);
    List<Claim> findClaimsByTeamId(Long teamId);
}

package ru.chn.service;

import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chn.dto.request.ClaimPostRequest;
import ru.chn.dto.response.ClaimPostResponse;
import ru.chn.dto.response.ResumeInClaimResponse;
import ru.chn.dto.response.TeamInClaimResponse;
import ru.chn.model.Claim;
import ru.chn.model.Resume;
import ru.chn.model.Team;
import ru.chn.repository.ClaimRepository;
import ru.chn.repository.ResumeRepository;
import ru.chn.repository.TeamRepository;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ClaimService {
    private final ClaimRepository repo;
    private final TeamRepository teamRepository;
    private final ResumeRepository resumeRepository;

    //post create claim
    public ClaimPostResponse createClaimByTeamIdAndUserId(ClaimPostRequest request, Long id) {
        if(repo.existsClaimByTeamIdAndResumeId(request.getTeamId(), request.getResumeId())){
            throw new DuplicateRequestException("Claim already exist");
        }
        if(!resumeRepository.existsResumeByUserIdAndId(id, request.getResumeId())){
            throw new IllegalArgumentException();
        }
        Claim claim = new Claim(request.getTeamId(), request.getResumeId(), LocalDateTime.now(), LocalDateTime.now(), null);
        ClaimPostResponse claimPostResponse = new ClaimPostResponse();
        Team team = teamRepository.findTeamById(request.getTeamId()).orElse(null);
        if (team == null) {
            throw new EntityNotFoundException();
        }
        TeamInClaimResponse teamInClaimResponse = new TeamInClaimResponse(team.getId(), team.getName(), team.getAvatar());
        Resume resume = resumeRepository.findById(request.getResumeId()).orElse(null);
        if (resume == null) {
            throw new EntityNotFoundException();
        }
        ResumeInClaimResponse resumeInClaimResponse = new ResumeInClaimResponse(resume.getId(), resume.getTitle());
        repo.saveAndFlush(claim);
        claimPostResponse.setId(claim.getId());
        claimPostResponse.setTeam(teamInClaimResponse);
        claimPostResponse.setResume(resumeInClaimResponse);
        claimPostResponse.setCreatedAt(claim.getCreatedAt());
        claimPostResponse.setUpdatedAt(claim.getUpdatedAt());
        claimPostResponse.setAccepted(null);


        return claimPostResponse;
    }
}

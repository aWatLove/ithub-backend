package ru.chn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chn.dto.request.ClaimPostRequest;
import ru.chn.dto.response.ClaimPostResponse;
import ru.chn.dto.response.ResumeInClaimResponse;
import ru.chn.dto.response.TeamInClaimResponse;
import ru.chn.model.Resume;
import ru.chn.model.Team;
import ru.chn.repository.ClaimRepository;
import ru.chn.repository.ResumeRepository;
import ru.chn.repository.TeamRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClaimService {
    private final ClaimRepository repo;
    private final TeamRepository teamRepository;
    private final ResumeRepository resumeRepository;

    //post create claim
    public Optional<ClaimPostResponse> createClaimByTeamIdAndUserId(ClaimPostRequest request, Long id){
        ClaimPostResponse claimPostResponse = new ClaimPostResponse();
        Team team = teamRepository.findTeamById(request.getTeamId()).orElse(null);
        TeamInClaimResponse teamInClaimResponse = new TeamInClaimResponse(team.getId(), team.getName(), team.getAvatar());
        Resume resume = resumeRepository.findById(request.getResumeId()).orElse(null);
        ResumeInClaimResponse resumeInClaimResponse = new ResumeInClaimResponse(resume.getId(), resume.getTitle());
        claimPostResponse.setTeam(teamInClaimResponse);
        claimPostResponse.setResume(resumeInClaimResponse);
        //claimPostResponse = repo.save(claimPostResponse);
        return Optional.ofNullable(claimPostResponse);
    }
}

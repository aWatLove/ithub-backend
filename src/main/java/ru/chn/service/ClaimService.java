package ru.chn.service;

import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chn.dto.payment.request.claim.ClaimPostRequest;
import ru.chn.dto.payment.response.claim.ClaimGetAllResponse;
import ru.chn.dto.payment.response.claim.ClaimPostResponse;
import ru.chn.dto.payment.response.resume.ResumeInClaimResponse;
import ru.chn.dto.payment.response.team.TeamInClaimResponse;
import ru.chn.model.*;
import ru.chn.repository.*;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClaimService {
    private final ClaimRepository repo;
    private final TeamRepository teamRepository;
    private final ResumeRepository resumeRepository;
    private final UsersTeamsRepository usersTeamsRepository;
    private final UsersTeamsFolowsRepository usersTeamsFolowsRepository;

    //post create claim
    public ClaimPostResponse createClaimByTeamIdAndUserId(ClaimPostRequest request, Long id) {
        if (usersTeamsRepository.existsUsersTeamByUserIdAndTeamId(id, request.getTeamId())) {
            throw new IllegalArgumentException();
        }
        if (repo.existsClaimByTeamIdAndResumeId(request.getTeamId(), request.getResumeId())) {
            throw new DuplicateRequestException("Claim already exist");
        }
        if (!resumeRepository.existsResumeByUserIdAndId(id, request.getResumeId())) {
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

    //get claims by userId
    public ClaimGetAllResponse getAllUsersClaims(Long id) {
        /*1)Взять лист всех resume по User id
         * 2)Взять по resume id Лист всех Claim
         * 3) Из клэймов формировать лист ClaimPostResponse и вернуть*/
        ClaimGetAllResponse claimGetAllResponse = new ClaimGetAllResponse();
        List<Resume> resumes = resumeRepository.findResumesByUserId(id);
        List<Claim> claims = new ArrayList<>();
        for (Resume r : resumes) {
            claims.addAll(repo.findClaimsByResumeId(r.getId()));
        }
        List<ClaimPostResponse> claimPostResponses = new ArrayList<>();
        for (Claim c : claims) {
            claimPostResponses.add(convertToCPRFromClaim(c));
        }

        claimGetAllResponse.setClaims(claimPostResponses);
        return claimGetAllResponse;
    }

    private ClaimPostResponse convertToCPRFromClaim(Claim claim) {
        ClaimPostResponse claimPostResponse = new ClaimPostResponse();
        Team team = teamRepository.findTeamById(claim.getTeamId()).orElse(null);
        if (team == null) {
            throw new EntityNotFoundException();
        }
        TeamInClaimResponse teamInClaimResponse = new TeamInClaimResponse(team.getId(), team.getName(), team.getAvatar());
        Resume resume = resumeRepository.findById(claim.getResumeId()).orElse(null);
        if (resume == null) {
            throw new EntityNotFoundException();
        }
        ResumeInClaimResponse resumeInClaimResponse = new ResumeInClaimResponse(resume.getId(), resume.getTitle());
        claimPostResponse.setId(claim.getId());
        claimPostResponse.setTeam(teamInClaimResponse);
        claimPostResponse.setResume(resumeInClaimResponse);
        claimPostResponse.setCreatedAt(claim.getCreatedAt());
        claimPostResponse.setUpdatedAt(claim.getUpdatedAt());
        claimPostResponse.setAccepted(claim.getAccepted());

        return claimPostResponse;
    }

    //get all Claims by team id
    public ClaimGetAllResponse getAllTeamsClaims(Long id) {
        /*1)Взять лист всех claims по team id
         * 2)Из клэймов формировать лист ClaimPostResponse и вернуть*/
        ClaimGetAllResponse claimGetAllResponse = new ClaimGetAllResponse();
        List<Claim> claims = repo.findClaimsByTeamId(id);
        List<ClaimPostResponse> claimPostResponses = new ArrayList<>();
        for (Claim c : claims) {
            claimPostResponses.add(convertToCPRFromClaim(c));
        }

        claimGetAllResponse.setClaims(claimPostResponses);
        return claimGetAllResponse;
    }

    //put confrim claims
    public void confrimClaim(Long claimId, Boolean accept, String role, Long userId) {
        /*1)Взять claim, который мы accept Меняем
          2)Изменить Claim accepted на true или false
        * 3)Внести запись в UserTeam если true accept
        * 4)Внести запись в UserTeamsFollows, если её там нет если true accept*/

        Claim claim = repo.findById(claimId).orElse(null);
        if (claim == null) {
            throw new EntityNotFoundException();
        }
        Team team = teamRepository.findTeamById(claim.getTeamId()).orElse(null);
        if (team == null) {
            throw new EntityNotFoundException();
        }
        if (!Objects.equals(team.getOwnerId(), userId)) {
            throw new IllegalArgumentException();
        }

        claim.setAccepted(accept);
        claim = repo.saveAndFlush(claim);
        if (claim.getAccepted()) {
            Resume resume = resumeRepository.findById(claim.getResumeId()).orElse(null);
            if (resume == null) {
                throw new EntityNotFoundException();
            }
            UsersTeam usersTeam = new UsersTeam();
            usersTeam.setUserId(resume.getUserId());
            usersTeam.setTeamId(claim.getTeamId());
            usersTeam.setRole(role);
            usersTeam = usersTeamsRepository.saveAndFlush(usersTeam);

            if (!usersTeamsFolowsRepository.existsByUserIdAndTeamId(userId, usersTeam.getTeamId())) {
                team.setFolowersCount(team.getFolowersCount() + 1);
                UsersTeamsFolows usersTeamsFolows = new UsersTeamsFolows();
                usersTeamsFolows.setUserId(resume.getUserId());
                usersTeamsFolows.setTeamId(claim.getTeamId());
                usersTeamsFolowsRepository.saveAndFlush(usersTeamsFolows);
            }
        }

    }

    //delete claim by claim id
    public void deleteClaimByClaimId(Long claimId, Long userId) {
        Claim claim = repo.findById(claimId).orElse(null);
        if (claim == null) {
            throw new EntityNotFoundException();
        }
        Resume resume = resumeRepository.findById(claim.getResumeId()).orElse(null);
        if (resume == null) {
            throw new EntityNotFoundException();
        }
        if (!Objects.equals(userId, resume.getUserId())) {
            throw new IllegalArgumentException();
        }
        repo.deleteById(claimId);
    }
}

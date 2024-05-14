package ru.chn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chn.dto.other.project.ProjectPreviewDTO;
import ru.chn.dto.other.team.TeamMemberDTO;
import ru.chn.dto.other.team.TeamPreviewDTO;
import ru.chn.dto.other.user.UserPreviewDTO;
import ru.chn.dto.payment.request.team.RoleMemberUpdateRequest;
import ru.chn.dto.payment.request.team.TeamSaveRequest;
import ru.chn.dto.payment.response.team.TeamDetailsResponse;
import ru.chn.dto.payment.response.team.TeamFolowersResponse;
import ru.chn.dto.payment.response.team.TeamPreviewResponse;
import ru.chn.model.*;
import ru.chn.repository.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository repo;
    private final ProjectRepository projectRepo;
    private final UsersTeamsRepository usersTeamsRepo;
    private final UsersTeamsFolowsRepository utFolowRepo;
    private final UserRepository userRepo;

    public TeamDetailsResponse getTeamById(Long id) {
        Team team = repo.findById(id).orElse(null);
        if (team == null) {
            throw new EntityNotFoundException();
        }

        TeamDetailsResponse tdr = new TeamDetailsResponse(team.getId(), team.getName(), team.getDescription(), team.getFolowersCount(), team.getAvatar(), team.getOwnerId());
        List<UsersTeam> usersTeams = usersTeamsRepo.findUsersTeamsByTeamId(id);
        for (UsersTeam ut : usersTeams) {
            User user = userRepo.findById(ut.getUserId()).orElse(null);
            if (user == null) {
                continue;
            }
            TeamMemberDTO tmb = new TeamMemberDTO(user.getId(), user.getUsername(), user.getAvatar(), ut.getRole());
            tdr.getMembers().add(tmb);
        }
        List<Project> projects = projectRepo.findProjectsByTeamId(id);
        for (Project p : projects) {
            ProjectPreviewDTO ppd = new ProjectPreviewDTO(p.getId(), p.getTitle(), p.getDescription(), p.getCreatedAt(), p.getFolowersCount(), p.getPatchCount());
            tdr.getProjects().add(ppd);
        }
        return tdr;
    }

    public TeamDetailsResponse getTeamById(Long id, Long userId) {
        TeamDetailsResponse tdr = getTeamById(id);
        if (utFolowRepo.existsByUserIdAndTeamId(userId, id)) {
            tdr.setFolow(true);
        }
        return tdr;
    }

    public TeamPreviewResponse getAllTeams(Long userId) {
        List<Team> teams = repo.findAll();
        TeamPreviewResponse tpr = new TeamPreviewResponse();
        tpr.setTeams(new ArrayList<>());
        for (Team t : teams) {
            Long fCount = utFolowRepo.countByTeamId(t.getId());
            TeamPreviewDTO tpd = new TeamPreviewDTO(t.getId(), t.getName(), t.getAvatar(), fCount);
            if (userId != null) {
                if (utFolowRepo.existsByUserIdAndTeamId(userId, t.getId())) {
                    tpd.setFolow(true);
                }
            }
            tpr.getTeams().add(tpd);
        }
        return tpr;
    }

    public TeamFolowersResponse getTeamFolowers(Long teamId) {
        List<UsersTeamsFolows> utfs = utFolowRepo.findAllByTeamId(teamId);
        if (utfs.isEmpty()) {
            return new TeamFolowersResponse();
        }
        TeamFolowersResponse tfr = new TeamFolowersResponse();
        for (UsersTeamsFolows ut : utfs) {
            User user = userRepo.findUserById(ut.getUserId()).orElse(null);
            if (user == null) {
                continue;
            }
            UserPreviewDTO upd = new UserPreviewDTO();
            upd.setId(user.getId());
            upd.setUsername(user.getUsername());
            upd.setAvatar(user.getAvatar());
            tfr.getFolowers().add(upd);
        }
        return tfr;
    }

    public TeamDetailsResponse createTeam(Long userId, TeamSaveRequest request) {
        Team team = new Team();
        if (request.getName() == null) {
            throw new IllegalArgumentException("team name cant be null");
        }
        if (repo.existsByOwnerIdAndName(userId, request.getName())) {
            throw new EntityExistsException();
        }
        team.setName(request.getName());
        team.setAvatar(request.getAvatar());
        team.setDescription(request.getDescription());
        team.setOwnerId(userId);
        team.setFolowersCount(0L);
        repo.saveAndFlush(team);
        Team newTeam = repo.findTeamByOwnerIdAndName(userId, team.getName()).orElse(null);
        if (newTeam == null) throw new RuntimeException("team not created");
        UsersTeamsFolows utf = new UsersTeamsFolows();
        utf.setTeamId(newTeam.getId());
        utf.setUserId(userId);
        utFolowRepo.saveAndFlush(utf);
        UsersTeam usersTeam = new UsersTeam();
        usersTeam.setUserId(userId);
        usersTeam.setTeamId(newTeam.getId());
        usersTeam.setRole("Создатель");
        usersTeamsRepo.saveAndFlush(usersTeam);
        return getTeamById(newTeam.getId());
    }

    public TeamDetailsResponse updateTeam(Long userId, Long teamId, TeamSaveRequest request) {
        Team team = repo.findTeamById(teamId).orElse(null);
        if (team == null)
            throw new EntityNotFoundException();
        if (!Objects.equals(team.getOwnerId(), userId))
            throw new IllegalArgumentException();
        if (request.getName() != null) {
            if (repo.existsByOwnerIdAndName(userId, request.getName())) throw new IllegalArgumentException();
            team.setName(request.getName());
        }
        if (request.getDescription() != null) {
            team.setDescription(request.getDescription());
        }
        if (request.getAvatar() != null) {
            team.setAvatar(request.getAvatar());
        }
        repo.saveAndFlush(team);
        return getTeamById(teamId, userId);
    }

    public void updateTeamMemberRole(Long teamId, Long userId, Long memberId, RoleMemberUpdateRequest request) {
        if (request.getRole().isEmpty()) throw new IllegalArgumentException();
        Team team = repo.findTeamById(teamId).orElse(null);
        if (team == null)
            throw new EntityNotFoundException();
        if (!Objects.equals(team.getOwnerId(), userId))
            throw new IllegalArgumentException();

        UsersTeam ut = usersTeamsRepo.findUsersTeamByUserIdAndTeamId(memberId, teamId).orElse(null);
        if (ut == null) throw new EntityNotFoundException();
        ut.setRole(request.getRole());
        usersTeamsRepo.saveAndFlush(ut);
    }

    public void folowTeam(Long teamId, Long userId) {
        UsersTeamsFolows utf = utFolowRepo.findByUserIdAndTeamId(userId, teamId).orElse(null);
        Team team = repo.findTeamById(teamId).orElse(null);
        if (team == null) throw new EntityNotFoundException();
        if (utf != null) {
            utFolowRepo.deleteById(utf.getId());
            team.setFolowersCount(team.getFolowersCount() - 1);
            repo.saveAndFlush(team);
            return;
        }
        utf = new UsersTeamsFolows();
        team.setFolowersCount(team.getFolowersCount() + 1);
        utf.setTeamId(teamId);
        utf.setUserId(userId);
        repo.saveAndFlush(team);
        utFolowRepo.saveAndFlush(utf);
    }

    public void deleteMember(Long teamId, Long memberId, Long ownerId) {
        if (Objects.equals(memberId, ownerId)) throw new IllegalArgumentException();
        UsersTeam ut = usersTeamsRepo.findUsersTeamByUserIdAndTeamId(memberId, teamId).orElse(null);
        if (ut == null) throw new EntityNotFoundException();
        Team team = repo.findTeamById(teamId).orElse(null);
        if (team == null) throw new EntityNotFoundException();
        if (!Objects.equals(team.getOwnerId(), ownerId)) throw new IllegalArgumentException();
        usersTeamsRepo.deleteById(ut.getId());
    }
}

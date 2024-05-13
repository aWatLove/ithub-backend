package ru.chn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.chn.dto.ProjectPreviewDTO;
import ru.chn.dto.TeamMemberDTO;
import ru.chn.dto.TeamPreviewDTO;
import ru.chn.dto.UserPreviewDTO;
import ru.chn.dto.response.TeamDetailsResponse;
import ru.chn.dto.response.TeamFolowersResponse;
import ru.chn.dto.response.TeamPreviewResponse;
import ru.chn.model.*;
import ru.chn.repository.*;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        TeamDetailsResponse tdr = new TeamDetailsResponse(team.getId(), team.getName(), team.getDescription(), team.getFolowers_count(), team.getAvatar(), team.getOwner_id());
        List<UsersTeam> usersTeams = usersTeamsRepo.findUsersTeamsByTeamId(id);
        for (UsersTeam ut: usersTeams) {
            User user = userRepo.findById(ut.getUserId()).orElse(null);
            if (user == null) {
                continue;
            }
            TeamMemberDTO tmb = new TeamMemberDTO(user.getId(), user.getUsername(), user.getAvatar(), ut.getRole());
            tdr.getMembers().add(tmb);
        }
        List<Project> projects = projectRepo.findProjectsByTeamId(id);
        for (Project p: projects) {
            ProjectPreviewDTO ppd = new ProjectPreviewDTO(p.getId(), p.getTitle(), p.getDescription(), p.getCreatedAt(),p.getFolowersCount(), p.getPatchCount());
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
        for (Team t: teams) {
            Long fCount = utFolowRepo.countByTeamId(t.getId());
            TeamPreviewDTO tpd = new TeamPreviewDTO(t.getId(),t.getName(), t.getAvatar(), fCount);
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
        for (UsersTeamsFolows ut: utfs) {
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
}

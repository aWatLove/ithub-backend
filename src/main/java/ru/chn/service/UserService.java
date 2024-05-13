package ru.chn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chn.dto.TeamPreviewDTO;
import ru.chn.dto.UserPreviewDTO;
import ru.chn.dto.response.UserDetailResponse;
import ru.chn.model.Team;
import ru.chn.model.User;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import ru.chn.model.UsersTeam;
import ru.chn.repository.TeamRepository;
import ru.chn.repository.UserRepository;
import ru.chn.repository.UsersTeamsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;
    private final UsersTeamsRepository usersTeamsRepo;
    private final TeamRepository teamRepo;

    // get all
    public List<UserPreviewDTO> getAllUsers() {
        List<User> users = repo.findAll();

        return users.stream()
                .map(this::convertToUserPreviewResponse)
                .collect(Collectors.toList());
    }

    // get by id
    public UserDetailResponse getUserDetailById(Long user_id) {
        Optional<User> optUser = repo.findById(user_id);
        if (optUser.isEmpty()) {
            return null;
        }
        User user = optUser.get();
        UserDetailResponse userResp = new UserDetailResponse(user.getId(), user.getUsername(), user.getAvatar(), user.getFirstname(), user.getLastname(), user.getBioInfo(), user.getEmail(), user.getTelegram(), user.getLink());
        userResp.setTeams(new ArrayList<>());


        List<UsersTeam> usersTeams = usersTeamsRepo.findUsersTeamsByUserId(user_id);
        for (UsersTeam ut: usersTeams) {
            Team team = teamRepo.findTeamById(ut.getTeamId()).orElse(null);
            if (team == null) {
                continue;
            }
            TeamPreviewDTO tp = new TeamPreviewDTO();
            tp.setId(team.getId());
            tp.setName(team.getName());
            tp.setAvatar(team.getAvatar());
            tp.setRole(ut.getRole());
            userResp.getTeams().add(tp);
        }

        return userResp;
    }

    private UserPreviewDTO convertToUserPreviewResponse(User user) {
        UserPreviewDTO response = new UserPreviewDTO();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setAvatar(user.getAvatar());
        return response;
    }
}

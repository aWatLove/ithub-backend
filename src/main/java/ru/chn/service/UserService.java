package ru.chn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import ru.chn.dto.other.user.UsersTeamPreviewDTO;
import ru.chn.dto.other.user.UserPreviewDTO;
import ru.chn.dto.payment.request.user.UserUpdateRequest;
import ru.chn.dto.payment.response.user.UserDetailResponse;
import ru.chn.model.Team;
import ru.chn.model.User;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import ru.chn.model.UsersTeam;
import ru.chn.repository.TeamRepository;
import ru.chn.repository.UserRepository;
import ru.chn.repository.UsersTeamsRepository;

import javax.persistence.EntityNotFoundException;
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
        for (UsersTeam ut : usersTeams) {
            Team team = teamRepo.findTeamById(ut.getTeamId()).orElse(null);
            if (team == null) {
                continue;
            }
            UsersTeamPreviewDTO tp = new UsersTeamPreviewDTO();
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


    public UserDetailResponse updateUserInfo(Long userId, UserUpdateRequest request) {
        User user = repo.findById(userId).orElse(null);
        if (user == null) {
            throw new EntityNotFoundException();
        }

        if (request.getUsername() != null) {
            if (repo.existsByUsername(request.getUsername())) {
                throw new DuplicateKeyException("Username already exists");
            }
            user.setUsername(request.getUsername());
        }
        if (request.getAvatar() != null) {
            user.setAvatar(request.getAvatar());
        }
        if (request.getFirstname() != null) {
            user.setFirstname(request.getFirstname());
        }
        if (request.getLastname() != null) {
            user.setLastname(request.getLastname());
        }
        if (request.getBioInfo() != null) {
            user.setBioInfo(request.getBioInfo());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
        if (request.getTelegram() != null) {
            user.setTelegram(request.getTelegram());
        }
        if (request.getLink() != null) {
            user.setLink(request.getLink());
        }
        repo.saveAndFlush(user);
        return getUserDetailById(userId);
    }
}

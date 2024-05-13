package ru.chn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.chn.dto.UserPreviewDTO;
import ru.chn.dto.response.UserDetailResponse;
import ru.chn.model.User;

import java.util.Optional;
import java.util.stream.Collectors;
import ru.chn.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repo;

    // get all
    public List<UserPreviewDTO> getAllUsers() {
        List<User> users = repo.findAll();

        return users.stream()
                .map(this::convertToUserPreviewResponse)
                .collect(Collectors.toList());
    }

    // get by id
    public UserDetailResponse getUserDetailById(Long user_id) {
        if (repo.findById(user_id).isEmpty()) {
            return null;
        }

        return null;
    }

    private UserPreviewDTO convertToUserPreviewResponse(User user) {
        UserPreviewDTO response = new UserPreviewDTO();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setAvatar(user.getAvatar());
        return response;
    }
}
